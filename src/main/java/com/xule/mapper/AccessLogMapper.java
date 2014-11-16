package com.xule.mapper;

import com.xule.model.AccessLogInfo;

import java.util.regex.Matcher;

/**
 * Created with IntelliJ IDEA.
 * User: lxu
 * Date: 11/16/14
 * Time: 3:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccessLogMapper extends Mapper{

    @Override
    public AccessLogInfo map(Matcher matcher) {
        AccessLogInfo logInfo = new AccessLogInfo();
        logInfo.setTime(matcher.group(1));
        logInfo.setIp(matcher.group(2));
        logInfo.setHttpMethod(matcher.group(3));
        logInfo.setFirtLineOfRequest(matcher.group(4));
        logInfo.setHttpStatus(matcher.group(5));
        logInfo.setRequestTime(matcher.group(6));
        return logInfo;
    }
}
