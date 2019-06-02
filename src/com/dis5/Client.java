package com.dis5;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import java.util.Random;

public class Client extends Thread {
    public static int MAX_WRITE_OP = 5;
    public static int N_LOREM_WORDS = 10;
    private Thread t;
    private String name;
    private int[] allowedPages;

    public Client(String name, int[] allowedPages) {
        this.name = name;
        this.t = new Thread(this, name);
        this.allowedPages = allowedPages;
    }

    @Override
    public synchronized void start() {
        System.out.println("Starting Thread: " + this.name);
        t.start();
    }

    @Override
    public void run() {
        PersistenceManager pm = PersistenceManager.getInstance();
        while (true) {
            int taid = pm.beginTransaction();
            int nWrite = new Random().nextInt(MAX_WRITE_OP);
            for (int i = 0; i < nWrite; i++) {
                int pageId = allowedPages[new Random().nextInt(allowedPages.length)];
                String data = LoremIpsum.getInstance().getWords(N_LOREM_WORDS); // Generate random 10 lorem words
                pm.write(taid, pageId, data);
            }
            pm.commit(taid);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
