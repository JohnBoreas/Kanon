package com.kanon.common.http;

import java.util.Map;

/**
 * HttpRequest
 */
public class HttpRequest {

    private Map<String, String> requestHeaders;

    private String url;

    private String postData;

    private String params;

    private Map<String, String> parameters;

    private ProxyIp proxyIp;

    private Integer retry;

    private String charset;

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPostData() {
        return postData;
    }

    public void setPostData(String postData) {
        this.postData = postData;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public ProxyIp getProxyIp() {
        return proxyIp;
    }

    public void setProxyIp(ProxyIp proxyIp) {
        this.proxyIp = proxyIp;
    }

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
