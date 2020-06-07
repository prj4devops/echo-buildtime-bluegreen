package com.prj4devops;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

class ResultEntity {
    private String version;
    private String buildTime;
    private String src;
    private String dest;
    private String hostname;

    public ResultEntity(String buildTime, String src, String dest, String hostname, String version) {
        this.buildTime = buildTime;
        this.src = src;
        this.dest = dest;
        this.hostname = hostname;
        this.version = version;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

@RestController
public class SampleController {

    private String timestamp;
    private String version;

    public SampleController(@Value("${build.timestamp}") String timestamp, @Value("${build.version}") String version) {
        this.timestamp = timestamp;
        this.version = version;

    }

    @RequestMapping("/")
    public ResultEntity hello(HttpServletRequest request) throws UnknownHostException {
        String hostname= InetAddress.getLocalHost().getHostName();
        return new ResultEntity(timestamp, request.getRemoteAddr(), request.getServerName(), hostname, version);
    }
}
