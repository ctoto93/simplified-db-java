package com.dis5;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogManager {
    public static String logFilePath = "";

    private Gson gson;
    private List<Log> logs;
    public LogManager() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            logs = Arrays.asList(gson.fromJson(new FileReader(logFilePath), Log[].class));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void appendLog(Log log) {
        try {
            gson.toJson(logs, new FileWriter(logFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        logs.add(log);
    }
}
