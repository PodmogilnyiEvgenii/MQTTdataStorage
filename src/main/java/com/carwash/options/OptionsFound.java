package com.carwash.options;
import java.io.*;

public class OptionsFound {
    public static boolean optionsFileFound(String filePath) {
        return new File(filePath).exists();
    }
}
