package com.kanon.common.http.httpclient;

import org.springframework.http.HttpHeaders;

import java.util.Arrays;

/**
 *
 */
public class SpiderDefaultRequestUtils {

    public static HttpHeaders defaultHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.USER_AGENT, Arrays.asList("ozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36"));
        return headers;
    }
}
