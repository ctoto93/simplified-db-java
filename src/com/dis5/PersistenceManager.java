package com.dis5;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

public class PersistenceManager {
    private static String PAGE_FOLDER_NAME = "pages";
    private static PersistenceManager instance;
    private static int lastTransactionId = 0;

    private Hashtable<Integer,Page> buffer = new Hashtable<>();
    private LogManager logManager = new LogManager();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private PersistenceManager() {
        this.logManager = new LogManager();
        for (Log log: logManager.getRedoLogs()) {
            Page p = getPageFromPersistence(log.getPageId());
            if (log.getId() > p.getLsnId()) {
                commit(log.getTransactionId());
            }
        }
    }

    public static synchronized PersistenceManager getInstance() {
        if (instance == null) {
            instance = new PersistenceManager();
        }
        return instance;
    }

    public synchronized int beginTransaction() {
        return lastTransactionId++;
    }

    public synchronized void commit(int transactionId) {

        for (Log log: logManager.getLogs(transactionId)) {
            Page p = getPageById(log.getPageId());
            p.setData(log.getData());
            p.setLsnId(log.getId());
            persistPage(p);
            log.setRedo(false);
            logManager.updateLog(log);
        }

    }

    public synchronized void write(int transactionId, int pageId, String data) {

        Log log = new Log(logManager.getLastLogId() + 1);
        log.setTransactionId(transactionId);
        log.setPageId(pageId);
        log.setData(data);
        logManager.appendLog(log);
        Page p = getPageById(pageId);
        p.setData(data);
        buffer.put(log.getPageId(), p);
    }


    public Page getPageById(int id) {
        if (buffer.containsKey(id)) {
            return buffer.get(id);
        }

        Page p = getPageFromPersistence(id);
        buffer.put(id, p);
        return p;
    }

    private boolean shouldProcessCommit() {
        return buffer.size() > 5;
    }

    private void persistPage(Page p) {
        try {
            Writer w = new FileWriter(getStringPagePath(p.getId()));
            gson.toJson(p, w);
            w.flush();
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Page getPageFromPersistence(int id) {
        File f = new File(getStringPagePath(id));

        if (f.exists()) {
            try {
                return gson.fromJson(new FileReader(f), Page.class);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return new Page(id, 0, "");
    }

    private String getStringPagePath(int id) {
        return String.format("%s/page_%s.json", PAGE_FOLDER_NAME, Integer.toString(id));
    }


}
