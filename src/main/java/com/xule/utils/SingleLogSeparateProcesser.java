package com.xule.utils;

import com.xule.mapper.AccessLogMapper;
import com.xule.model.AccessLogInfo;
import com.xule.writer.AccessLogInfoExcelWriter;

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
    private Map<String, String> context;
    private String fileName;
    private BufferedReader reader = null;
    private BufferedWriter writer = null;
    private BufferedWriter errorLogWriter = null;
    private Set<String> uniqueFirstLine = null;
    private Set<String> uniquelog = null;
    private Set<String> uniqueUri = null;
    private Set<AccessLogInfo> uniqueAccessLogInfo= null;

    private  String accessLogPatternStr;
    private  Pattern accessLogPattern;


    public SingleLogSeparateProcesser(Map<String, String> context) {
        this.context = context;
        initProcesser();
    }

    private void initProcesser() {
        reader = FileUtils.createReader(context.get("logFileName"));
        accessLogPatternStr = context.get("logPattern");
        accessLogPattern = Pattern.compile(accessLogPatternStr);
        writer = FileUtils.createWriter(context.get("outputFile"));
        errorLogWriter = FileUtils.createWriter(context.get("errorFile"));
        uniqueFirstLine = new HashSet<String>();
        uniquelog = new HashSet<String>();
        uniqueUri = new HashSet<String>();
        uniqueAccessLogInfo = new TreeSet<AccessLogInfo>();
    }

    public void process() {
        extractUniqueLogs();
        writeDownLogs();
        finalizeProcesser();
    }

    private void writeDownLogs() {
        boolean firstLine = true;
        for (String uri : uniqueUri) {
            try {
                if (!firstLine)
                    writer.write("\r\n");
                firstLine = false;
                writer.write(uri + "\r\n");
                for (String log : uniquelog) {
                    if (log.contains(uri))
                        writer.write(log + "\r\n");
                }
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        //export to excel
        AccessLogInfoExcelWriter accessLogInfoExcelWriter = new AccessLogInfoExcelWriter();
        accessLogInfoExcelWriter.writeDownAccessLogInfo(uniqueAccessLogInfo);
    }


    private void extractUniqueLogs() {
        Matcher accessLogMatcher;
        try {
            String line = reader.readLine();
            while (line != null) {
                accessLogMatcher = accessLogPattern.matcher(line);
                if(isLogLine(accessLogMatcher)){
                    //line is a single log with the specified pattern
                    AccessLogInfo loginfo = assembleLogInfo(accessLogMatcher, new AccessLogMapper());
                    if(isFirstLineOfTheRequestUnique(loginfo.getFirstLineOfRequest())) {
                        uniquelog.add(line);
                        uniqueAccessLogInfo.add(loginfo);
                    }
                } else {
                    //if there are some line do not match the pattern we need to record it
                    errorLogWriter.write(line + "\r\n");
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            logger.severe("Reader error");
        }
    }

    private boolean isFirstLineOfTheRequestUnique(String firstLineOfRequest) {
        String compressedFirstLine = compressFirstLine(firstLineOfRequest);
        if (uniqueFirstLine.contains(compressedFirstLine))
            return false;
        uniqueFirstLine.add(compressedFirstLine);
        return true;
    }

    private String compressFirstLine(String firstLineOfRequest) {
        //separate uri and params
        if (firstLineOfRequest.contains("?")) {
            String uriStr = firstLineOfRequest.substring(0, firstLineOfRequest.indexOf("?"));
            uniqueUri.add(uriStr);
            String paramsString = firstLineOfRequest.substring(firstLineOfRequest.indexOf("?") + 1);
            //separate params
            List<String> params = separateParams(paramsString);
            StringBuilder compressedString = new StringBuilder();
            compressedString.append(uriStr);
            for (String paramName : params) {
                compressedString.append(paramName);
            }
            return compressedString.toString();
        }
        uniqueUri.add(firstLineOfRequest);
        return firstLineOfRequest;
    }

    private List<String> separateParams(String paramsString) {
        //for params like v=201411152348
        if (!paramsString.contains("&")) {
            List paramsName = new ArrayList();
            paramsName.add(paramsString.substring(0, paramsString.indexOf("=")));
            return paramsName;
        } else {
            //for params like gameId=sims4&code=AC1cDwqRzoGCBewfFPcGcs&country=US&locale=en_US&currency=USD&cartname=vltqaxfyth&cip=159.153.4.52
            List<String> params = Arrays.asList(paramsString.split("&"));
            List paramNames = new ArrayList();
            for (String param : params) {
                paramNames.add(param.substring(0, param.indexOf("=")));
            }
           return paramNames;
        }
    }

    private AccessLogInfo assembleLogInfo(Matcher accessLogMatcher, AccessLogMapper accessLogMapper) {
        return accessLogMapper.map(accessLogMatcher);

    }

    private boolean isLogLine(Matcher majorMatcher) {
        return majorMatcher.find();
    }

    private void finalizeProcesser() {
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
