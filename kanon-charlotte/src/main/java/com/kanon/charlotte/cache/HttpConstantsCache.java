package com.kanon.charlotte.cache;

import org.apache.http.HttpHost;

import java.util.ArrayList;
import java.util.List;

public class HttpConstantsCache {

    public static volatile List<HttpHost> PROXY = new ArrayList<>();

    public static volatile List<String> PC_CONTENT_TYPE = new ArrayList<>();

    public static volatile List<String> APP_CONTENT_TYPE = new ArrayList<>();

    static {
        PC_CONTENT_TYPE.add("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36");
        PC_CONTENT_TYPE.add("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36");
        PC_CONTENT_TYPE.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.26 Safari/537.36");

        APP_CONTENT_TYPE.add("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Mobile Safari/537.36");
        APP_CONTENT_TYPE.add("Mozilla/5.0 (iPhone; CPU iPhone OS 12_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 MicroMessenger/7.0.3(0x17000321) NetType/WIFI Language/zh_CN");
    }
}
