package com.xule.utils;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtils {
    private static Logger logger = Logger.getLogger(FileUtils.class.getName());

    public static BufferedReader createReader(String fileName) {
        BufferedReader reader = null;
        try {
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader ir = new InputStreamReader(fis);
            reader = new BufferedReader(ir);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Can not get the InputStreamReader");
        }
        return reader;
    }

    public static BufferedWriter createWriter(String fileName) {
        BufferedWriter writer = null;
        try {
            File file = new File(fileName);
            if (file == null) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter ir = new OutputStreamWriter(fos);
            writer = new BufferedWriter(ir);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Can not get the OutputStreamWriter");
        }
        return writer;
    }
}
