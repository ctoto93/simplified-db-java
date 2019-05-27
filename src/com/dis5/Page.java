package com.dis5;

public class Page {
    private int id;
    private int lsnId;
    private String data;

    public Page(int id, int lsnId, String data) {
        this.id = id;
        this.lsnId = lsnId;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLsnId() {
        return lsnId;
    }

    public void setLsnId(int lsnId) {
        this.lsnId = lsnId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
