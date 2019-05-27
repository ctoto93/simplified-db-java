package com.dis5;

public class Main {

    public static void main(String[] args) {
//        for (int i = 0; i < 4; i++) {
//            Client c = new Client(Integer.toString(i));
//            c.start();
//        }
        LogManager lm = new LogManager();
        lm.appendLog( new Log());
    }
}
