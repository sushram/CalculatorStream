package com.example.calculatorstream.configuration;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Calculate Logger will maintain latest 10 calculations
 */
@Service
public class CalcLogger {

    Map<Integer,String> latestCals;
    int key;

    public CalcLogger() {
        int key=-1;
        this.latestCals = new LinkedHashMap() {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return this.size() > 10;
            }
        };
    }

    public synchronized String[] getAllEntries() {
        return latestCals.values().toArray(new String[latestCals.size()]);
    }

    public synchronized  void addCalculation(String calculation) {
        key++;
        latestCals.put(key,calculation);
    }
}
