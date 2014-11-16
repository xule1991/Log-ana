package com.xule.model;

/**
 * Created with IntelliJ IDEA.
 * User: lxu
 * Date: 11/14/14
 * Time: 1:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccessLogInfo{
    private String time;
    private String ip;
    private String httpMethod;
    private String firtLineOfRequest;
    private String httpStatus;
    private String requestTime;
    private AdditionalInfo additionalInfo;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getFirstLineOfRequest() {
        return firtLineOfRequest;
    }

    public void setFirtLineOfRequest(String firtLineOfRequest) {
        this.firtLineOfRequest = firtLineOfRequest;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
