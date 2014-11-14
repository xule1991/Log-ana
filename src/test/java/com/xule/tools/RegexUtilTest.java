package com.xule.tools;


import com.xule.model.LogInfo;
import org.junit.Test;

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
         String line = "16066251 [main] INFO  org.springframework.web.context.support.XmlWebApplicationContext - Closing WebApplicationContext for namespace 'lockbox-servlet': startup date [Thu Nov 13 20:24:37 GMT 2014]; parent: Root WebApplicationContext";
        LogInfo logInfo = RegexUtil.assembleLogInfo(line);

    }
}
