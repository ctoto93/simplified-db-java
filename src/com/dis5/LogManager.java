package com.dis5;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogManager {
    public static String logFilePath = "logs.json";

    private Gson gson;
    private ArrayList<Log> logs = new ArrayList<>();

    public LogManager() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            File f = new File(logFilePath);

            if (!f.exists()) {
                Writer w = new FileWriter(f);
                gson.toJson(logs, w);
                w.flush();
                w.close();
                return;
            }

            logs = new ArrayList<>(Arrays.asList(gson.fromJson(new FileReader(f), Log[].class)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void appendLog(Log log) {
        logs.add(log);
        try {
            Writer w = new FileWriter(logFilePath);
            gson.toJson(logs, w);
            w.flush();
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
