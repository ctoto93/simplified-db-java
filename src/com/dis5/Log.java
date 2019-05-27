package com.dis5;

public class Log {
    private int id;
    private int transactionId;
    private int pageId;
    private String data;
    private boolean redo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean shouldRedo() {
        return redo;
    }

    public void setRedo(boolean redo) {
        this.redo = redo;
    }
}
