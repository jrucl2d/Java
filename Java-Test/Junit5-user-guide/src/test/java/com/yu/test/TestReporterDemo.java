package com.yu.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

import java.util.HashMap;
import java.util.Map;

public class TestReporterDemo {
    @Test
    void reportSingleValue(TestReporter testReporter){
        testReporter.publishEntry("상태 메시지");
    }

    @Test
    void reportKeyValuePair(TestReporter testReporter){
        testReporter.publishEntry("키", "값");
    }

    @Test
    void reportMultipleKeyValuePairs(TestReporter testReporter){
        Map<String, String> values = new HashMap<>();
        values.put("user name", "aa");
        values.put("award year", "1234");

        testReporter.publishEntry(values);
    }
}
