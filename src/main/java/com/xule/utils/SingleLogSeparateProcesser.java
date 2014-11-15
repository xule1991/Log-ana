package com.xule.utils;

import com.xule.model.LogInfo;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created with IntelliJ IDEA.
 * User: lxu
 * Date: 11/14/14
 * Time: 12:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class SingleLogSeparateProcesser {
    private static Logger logger = Logger.getLogger(SingleLogSeparateProcesser.class.getName());
    private static Set<String> excludedClasses = null;
    private String fileName;
    private BufferedReader reader = null;
    private BufferedWriter writer = null;
    private Set<String> singleLog = null;
    private Set<String> classTypes = null;

    private static String majorString = "^([0-9]+)\\s+(\\[.+\\])\\s+(\\w+)\\s+([\\w\\.]+)\\s+-\\s+([\\s\\S]*?)$";
    private static Pattern majorPattern = Pattern.compile(majorString);
    private List<LogInfo> logInfos ;

    static {
        excludedClasses = new HashSet<String>();
        excludedClasses.add("session: org.apache.catalina.session.StandardSessionFacade");
        excludedClasses.add("org.springframework.beans.factory.support.DefaultListableBeanFactory");
    }

    public SingleLogSeparateProcesser(String fileName) {
        this.fileName = fileName;
        initProcesser();
    }

    private void initProcesser() {
        reader = FileUtils.createReader(fileName);
        writer = FileUtils.createWriter("errorLine.html");
        try {
            writer.write("<html>");
            writer.write("<body>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        singleLog = new HashSet<String>();
        classTypes = new HashSet<String>();
        logInfos = new ArrayList<LogInfo>();
    }

    public void process() {
        //First time go through the file to separate it into single logs and store the logs into a list for further analyze
        extractSingleLogs();
        //showDescription();
        //showGroupDescription();
        showDescriptionsOfUniqueClasses();
        finalizeProcesser();
    }

    private void showGroupDescription() {

    }

    private void showDescription() {
        Iterator<String> iterator = singleLog.iterator();
        while (iterator.hasNext()) {
            try {
                writer.write(iterator.next());
                writer.write("\n\r");
                writer.write("\n\r");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void extractSingleLogs() {
        Matcher majorMatcher = null;
        try {
            String singleLog = "";
            String line = reader.readLine();
            majorMatcher = majorPattern.matcher(line);
            if(isMajorLine(majorMatcher))
                singleLog = line;
            while (line != null) {
                majorMatcher = majorPattern.matcher(line);
                if (isMajorLine(majorMatcher) && !singleLog.equals("")) {
                    dealWithSingleLog(singleLog);
                    this.singleLog.add(singleLog);
                    singleLog = line;
                } else {
                    singleLog = singleLog + "\n" + line;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            logger.severe("Reader error");
        }
    }

    /**
     * Deal with a single log
     * @param singleLog
     */
    private void dealWithSingleLog(String singleLog) {
        Matcher singleLogMatcher = majorPattern.matcher(singleLog);
        if (singleLogMatcher.find()) {
            createLogInfo(singleLogMatcher);
            findUniqueClassTypes(singleLogMatcher);
        }
    }

    private void showDescriptionsOfUniqueClasses() {
        Iterator<String> iterator = classTypes.iterator();
        while (iterator.hasNext()) {
            try {
                String className = iterator.next();
                writer.write("<font color=\"red\">ClassName:</font>\n\r");
                writer.write("<font color=\"red\">" + className + "</font>" + "\n\r");
                processDescription(className);
                writer.write("\n\r");
                writer.write("\n\r");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void processDescription(String className) {
        Iterator<LogInfo> iterator = logInfos.iterator();
        List<String> uniqueDescriptions = new ArrayList<String>();
        List<String> specificDescription = new ArrayList<String>();
        specificDescription.add("session: org.apache.catalina.session.StandardSessionFacade");
        while (iterator.hasNext()) {
            LogInfo current = iterator.next();
            if (current.getClassType().equals(className)
                    //&& !specificDescription.contains(className)
                    //&& isUniqueDescription(current.getDescription(), uniqueDescriptions)) {
                    ) {
                try {
                    writer.write(current.getDescription() + "\n\r\n\r");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean isUniqueDescription(String description, List<String> uniqueDescriptions) {
        //remove all variables pattern like[a=b],then compare.
        boolean isUnique = false;
        String descriptionCopy = description.replaceAll("\\[.*\\]", "");;
        if (!uniqueDescriptions.contains(descriptionCopy)) {
            uniqueDescriptions.add(descriptionCopy);
            isUnique = true;
        }
        //if the first 50 char is the same,we thought it is from the same logger
        if (descriptionCopy.length() >= 30 && !uniqueDescriptions.contains(descriptionCopy.substring(0, 30))) {
            uniqueDescriptions.add(descriptionCopy.substring(0, 30));
            isUnique = true;
        }
        return isUnique;
    }

    private void writeDescription() {
    }

    private void findUniqueClassTypes(Matcher singleLogMatcher) {
        Iterator<String> iterator = excludedClasses.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(singleLogMatcher.group(4)))
                return;
        }
        classTypes.add(singleLogMatcher.group(4));
    }

    private void createLogInfo(Matcher singleLogMatcher) {
        LogInfo logInfo = new LogInfo();
        logInfo.setNum(singleLogMatcher.group(1));
        logInfo.setSquareInfo(singleLogMatcher.group(2));
        logInfo.setLogLevel(singleLogMatcher.group(3).trim());
        logInfo.setClassType(singleLogMatcher.group(4).trim());
        logInfo.setDescription(singleLogMatcher.group(5).trim());
        logInfos.add(logInfo);
    }

    private boolean isMajorLine(Matcher majorMatcher) {
        return majorMatcher.find();
    }

    private void finalizeProcesser() {
        try {
            writer.write("</body>");
            writer.write("</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (reader != null)
                reader.close();
            if (writer != null)
                writer.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Can not finish the stream");
        }
    }
}
