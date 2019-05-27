package com.dis5;

import java.util.HashMap;

public class PersistenceManager {
    private static PersistenceManager instance;
    private static int lastTransactionId = 0;

    private HashMap<Integer,String> buffer = new HashMap<>();
    private LogManager logManager = new LogManager();

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

    public String getPageById(int id) {
        String data = "";
        if (!buffer.containsKey(id)) {
            // TODO: retrieve data from disk

            buffer.put(id, data);
            return data;
        }

        data = buffer.get(id);
        return data;
    }


}
