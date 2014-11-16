package com.xule.tools;


import com.xule.model.AccessLogInfo;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: lxu
 * Date: 11/14/14
 * Time: 3:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegexUtilTest {
    @Test
    public void assembleLogInfoTest() {
        String line = "??? 10.88.48.165 GET /lb-ui/cart/purchase HTTP/1.1 200 1415ms [SEQID=10.88.48.165:1416067203689] [Accept=text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8, X-Forwarded-For=-]";
        String patternStr = "^(.+)\\s+(\\d{2,3}\\.\\d{2,3}\\.\\d{2,3}\\.\\d{2,3})\\s+(GET|POST)\\s+(.+)\\s(HTTP/\\d.\\d\\s\\d{3})\\s+(\\d+ms)\\s(.+)$";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(line);
        System.out.println(matcher.find());
        System.out.println(matcher.group(1));
        System.out.println(matcher.group(2));
        System.out.println(matcher.group(3));
        System.out.println(matcher.group(4));
        System.out.println(matcher.group(5));
        System.out.println(matcher.group(6));
        System.out.println(matcher.group(7));
    }
}
