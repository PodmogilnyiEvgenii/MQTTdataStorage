package com.carwash.options;
import java.io.*;
import java.util.LinkedHashMap;

public class OptionsRead {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(OptionsRead.class);

    public static LinkedHashMap<String, String> read(String filePath) {
        LinkedHashMap<String, String> optionsMap = new LinkedHashMap<>();
        try {
            File optionsFile = new File(filePath);
            FileReader fr = new FileReader(optionsFile);
            BufferedReader bufferedReader = new BufferedReader(fr);

            String buffer = "";
            do {
                buffer = bufferedReader.readLine();
                if (buffer != null) {
                    optionsMap.put(buffer.split("=")[0], buffer.split("=")[1]);
                }
            } while (buffer != null);
            logger.debug("Options file readed.");
            fr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return optionsMap;
    }
}
