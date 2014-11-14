package com.xule.tools;

import com.xule.model.LogInfo;
import com.xule.reader.MyBufferedReader;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created with IntelliJ IDEA.
 * User: lxu
 * Date: 11/14/14
 * Time: 12:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Processer {
    private static Logger logger = Logger.getLogger(Processer.class.getName());
    private String fileName;
    private File file = null;
    private InputStream fis = null;
    private InputStreamReader ir = null;
    private MyBufferedReader reader = null;
    private Set<String> classTypes = null;
    private Set<String> description = null;

    //Single log line
    public Processer(String fileName) {
        this.fileName = fileName;
        initProcesser();
    }

    /**
     * Process the log file make them into the general model with num, squareInfo, logLevel ,classType and description.
     * use \r\n to seperate each single log,because we need to use \n to seperate xml format in one kind of description.
     */
    public void process(){
        readProcess();
        finalizeProcesser();
    }

    private void buildLineInfo(String line) {
        LogInfo logInfo = RegexUtil.assembleLogInfo(line);
        if (logInfo != null)
        classTypes.add(logInfo.getClassType());
        if (logInfo != null)
         description.add(logInfo.getDescription());

    }

    private void readProcess() {
        try {
            String singleLog = reader.readLine(true);
            while(singleLog != null) {
                buildLineInfo(singleLog);
                singleLog = reader.readLine(true);
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    private void initProcesser() {
        try {
            file = new File(fileName);
            fis = new FileInputStream(file);
            ir = new InputStreamReader(fis);
            reader = new MyBufferedReader(ir);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Can not get the InputStreamReader");
        }
        classTypes = new HashSet<String>();
        description = new HashSet<String>();
    }

    private void finalizeProcesser() {
        try {
            if (reader != null)
                reader.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Can not finish the InputStreamReader");
        }
    }

    public void showClassTypes() {
        Iterator<String> iterator = classTypes.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public void showDescription() {
        Iterator<String> iterator = description.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
