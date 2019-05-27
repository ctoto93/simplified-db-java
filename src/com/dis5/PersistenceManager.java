package com.dis5;

public class PersistenceManager {
    private static PersistenceManager instance;

    private PersistenceManager() {
        //TODO: read the LSN logfile
    }

    private static PersistenceManager getInstance() {
        if (instance == null) {
            instance = new PersistenceManager();
        }
        return instance;
    }

    public int beginTransaction() {
        //TODO: generate taid
        return 1;
    }

    public void commit(int trasactionId) {

    }

    public void write(int transactionId, int pageId, String data) {

    }


}
