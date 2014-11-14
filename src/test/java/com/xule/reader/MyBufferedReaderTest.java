package com.xule.reader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: lxu
 * Date: 11/14/14
 * Time: 3:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyBufferedReaderTest {
    private File file = null;
    private FileInputStream fis = null;
    private InputStreamReader ir = null;
    private MyBufferedReader reader = null;
    private BufferedReader normalReader = null;

    private static Logger logger = Logger.getLogger(MyBufferedReader.class.getName());
    @Before
    public void init() {
        try {
            file = new File("C:\\Users\\lxu\\IdeaProjects\\local\\log-ana\\target\\hydra.log");
            fis = new FileInputStream(file);
            ir = new InputStreamReader(fis);
            reader = new MyBufferedReader(ir);
            file = new File("C:\\Users\\lxu\\IdeaProjects\\local\\log-ana\\target\\hydra.log");
            fis = new FileInputStream(file);
            ir = new InputStreamReader(fis);
            normalReader = new BufferedReader(ir);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Can not get the InputStreamReader");
        }
    }

    @Test
    public void testReadLine() {
        try {
            int num = 0;
            String line = reader.readLine(true);
            while (line != null) {
                num ++;
                line = reader.readLine(true);
            }

            int num_normal = 0;
            line = normalReader.readLine();
            while (line != null) {
                num_normal ++;
                line = normalReader.readLine();
            }
            assertTrue(num != num_normal);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @After
    public void finishProcess() {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
