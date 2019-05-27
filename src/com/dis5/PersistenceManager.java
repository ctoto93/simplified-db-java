package com.dis5;

public class PersistenceManager {
    private static PersistenceManager instance;
    private static int lastTransactionId = 0;

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

    }

    public void write(int transactionId, int pageId, String data) {

    }


}
