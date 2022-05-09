package com.carwash.options;
import java.io.*;
import java.util.Map;
import java.util.LinkedHashMap;

public class OptionsCreate {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(OptionsCreate.class);

    public static void create(String filePath, LinkedHashMap<String, String> optionsMap) {
        try {
            FileWriter mqttOptionsFileWriter = new FileWriter(filePath);
            for (Map.Entry<String, String> entry : optionsMap.entrySet()) {
                mqttOptionsFileWriter.write(entry.getKey() + "=" + entry.getValue() + "\n");
            }
            mqttOptionsFileWriter.flush();
            logger.debug("Options file created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
