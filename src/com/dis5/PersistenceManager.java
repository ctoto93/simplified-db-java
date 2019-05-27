package com.dis5;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.HashMap;

public class PersistenceManager {
    private static String PAGE_FOLDER_NAME = "pages";
    private static PersistenceManager instance;
    private static int lastTransactionId = 0;

    private HashMap<Integer,Page> buffer = new HashMap<>();
    private LogManager logManager = new LogManager();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private PersistenceManager() {
        //TODO: read the LSN logfile
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

    public void commit(int trasactionId) {
        //TODO: persist the changes on disk
        //TODO: update the buffer
        //TODO: change the redo for that transaction id to false
    }

    public void write(int transactionId, int pageId, String data) {
        //TODO: write the transaction id changes to logfile
    }

    public Page getPageById(int id) {
        if (buffer.containsKey(id)) {
            return buffer.get(id);
        }

        Page p = getPageFromPersistence(id);
        buffer.put(id, p);
        return p;
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

        return null;
    }

    private String getStringPagePath(int id) {
        return String.format("%s/page_%s.json", PAGE_FOLDER_NAME, Integer.toString(id));
    }


}
