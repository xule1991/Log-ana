package com.xule.reader;

import com.xule.utils.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lxu
 * Date: 11/17/14
 * Time: 9:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class LogDirectory {
    private String path = null;
    private List<BufferedReader> readers = new ArrayList<BufferedReader>();
    private int currentNo;

    public LogDirectory (String path) {
        this.path = path;
        initReaders();
    }

    private void initReaders() {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            for (File file2 : files) {
                readers.add(FileUtils.createReader(file2.getAbsolutePath()));
            }
        }
        currentNo = readers.size();
    }

    public BufferedReader getReader() {
        if (currentNo > 0)
            return readers.get(--currentNo);
        return null;
    }

    public void destroyReaders() {
        Iterator<BufferedReader> iterator = readers.iterator();
        try {
            while (iterator.hasNext())
                iterator.next().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
