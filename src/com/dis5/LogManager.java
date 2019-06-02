package com.dis5;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogManager {
    public static String LOG_FILE_PATH = "logs.json";

    private Gson gson;
    private ArrayList<Log> logs = new ArrayList<>();

    public LogManager() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            File f = new File(LOG_FILE_PATH);

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

    public List<Log> getLogs(int transactionId) {
        ArrayList<Log> result = new ArrayList<>();
        for (Log log: logs) {
            if (log.getTransactionId() == transactionId) {
                result.add(log);
            }
        }
        return result;
    }

    public int getLastLogId() {
        return logs.get(logs.size() - 1).getId();
    }

    public void updateLog(Log updatedLog) {

        for (int i =0; i < logs.size(); i++) {
            Log log = logs.get(i);
            if (log.getId() == updatedLog.getId()) {
                logs.set(i, updatedLog);
                break;
            }
        }

        saveLogs();
    }

    public void appendLog(Log log) {
        logs.add(log);
        saveLogs();
    }

    private void saveLogs() {
        try {
            Writer w = new FileWriter(LOG_FILE_PATH);
            gson.toJson(logs, w);
            w.flush();
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
