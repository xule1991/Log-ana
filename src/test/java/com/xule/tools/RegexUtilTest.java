package com.xule.tools;


import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: lxu
 * Date: 11/14/14
 * Time: 3:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegexUtilTest {
    //"([0-9]+)\\s+(\\[.+\\])\\s+(\\w)\\s+(.+)}"
    @Test
    public void assembleLogInfoTest() {
         String line = "371542 [http-80-4] DEBUG com.ea.eadp.cp.infra.rest.client.RESTClient";

        RegexUtil.assembleLogInfo(line);
    }
}
