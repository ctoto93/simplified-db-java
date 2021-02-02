package com.dis5;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

       for (int i = 1; i <= 4; i++) {
           int base = i * 10;
           Client c = new Client(Integer.toString(i), new int[]{base + 1, base + 2, base + 3, base + 4});
           c.start();
       }

       PersistenceManager pm = PersistenceManager.getInstance();
       int taid = pm.beginTransaction();
       pm.write(taid, 1, "hello");
       pm.commit(taid);
    }
}
