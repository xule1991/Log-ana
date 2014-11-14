package com.xule.tools;

import com.xule.model.LogInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: lxu
 * Date: 11/14/14
 * Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegexUtil {
    private static String regexHydra = "^[.\\n\\s*\\r]+$";

    public static LogInfo assembleLogInfo(String source) {
        Pattern p = Pattern.compile(regexHydra);
        Matcher m = p.matcher(source);
        LogInfo logInfo = null;
        if (m.find()) {
            logInfo = new LogInfo();
            logInfo.setNum(m.group(1));
            logInfo.setSquareInfo(m.group(2));
            logInfo.setLogLevel(m.group(3));
            logInfo.setClassType(m.group(4));
            logInfo.setDescription(m.group(5));
            return logInfo;
        }
        return null;
      /*  System.out.println(m.find());
        System.out.println(m.groupCount());
        System.out.println(m.group(0));
        System.out.println(m.group(1));
        System.out.println(m.group(2));
        System.out.println(m.group(3));
        System.out.println(m.group(4));
        System.out.println(m.group(5));*/
    }
}
