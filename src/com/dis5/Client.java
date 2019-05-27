package com.dis5;

public class Client extends Thread {
    private Thread t;
    private String name;

    public Client(String name) {
        this.name = name;
        this.t = new Thread(this, name);
    }

    @Override
    public synchronized void start() {
        System.out.println("Starting Thread: " + this.name);
        t.start();
    }

    @Override
    public void run() {
        PersistenceManager pm = PersistenceManager.getInstance();
        for (int i =0; i < 10; i++) {
            System.out.println("Message from thread" + this.name + ": " + pm.beginTransaction());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
