package com.xule.tools;

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
    private static String regex = "([0-9]+)\\s+(\\[.+\\])\\s+(\\w)\\s+([a-zA-Z.]+)";

    public static void assembleLogInfo(String source) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(source);
        System.out.println(m.find());
     /*   System.out.println(m.groupCount());
        System.out.println(m.group(0));
        System.out.println(m.group(1));
        System.out.println(m.group(2));*/
    }
}
