package com.kanon.common.http.httpclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

@Component
public class HttpClientUtils {

    private static RestTemplate restTemplate;

    private static RestTemplate proxyRestTemplate;

    /**
     * 使用 RestTemplate 无法注入，采用 RestTemplateBuilder
     * Consider defining a bean of type 'org.springframework.web.client.RestTemplate' in your configuration.
     * @param restTemplate
     */
    @Autowired
    public HttpClientUtils(RestTemplate restTemplate, RestTemplate proxyRestTemplate) {
        HttpClientUtils.restTemplate = restTemplate;
        HttpClientUtils.proxyRestTemplate = proxyRestTemplate;
    }

    /**
     * http返回内容
     * @param baseUrl 链接
     * @param params 参数
     * @param body
     * @param headersParams 头信息
     * @param clazz 实体类
     * @return
     */
    public static <T> T get(String baseUrl, Map<String, String> params, Object body, Map<String, String> headersParams, Class<T> clazz, Boolean useProxy) {

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            if (baseUrl.contains(key)) {
                baseUrl = baseUrl.replace(key, entry.getValue());
            }
        }
        // header
        HttpHeaders headers = new HttpHeaders();
        if (!CollectionUtils.isEmpty(headersParams)) {
            for (String header : headersParams.keySet()) {
                String value = headersParams.get(header);
                // 替换referer里的参数
                if (HttpHeaders.REFERER.equals(header)) {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        String key = entry.getKey();
                        if (value.contains(key)) {
                            value = value.replace(key, entry.getValue());
                        }
                    }
                }
                headers.put(header, Arrays.asList(value));
            }
        } else {
            headers = SpiderDefaultRequestUtils.defaultHttpHeaders();
        }
        if (useProxy) {
            return proxyRestTemplate.exchange(baseUrl, HttpMethod.GET, new HttpEntity<>(body, headers), clazz).getBody();
        }
        return restTemplate.exchange(baseUrl, HttpMethod.GET, new HttpEntity<>(body, headers), clazz).getBody();
    }
}
