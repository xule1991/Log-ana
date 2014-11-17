package com.xule.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {
    public Map<String, String> createProperties(String propFileName) {
        Properties prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> context = new HashMap<String, String>();
        for (Object key : prop.keySet()) {
            Object value = prop.get(key);
            context.put((String) key, (String) value);
        }
        return context;
    }
}
