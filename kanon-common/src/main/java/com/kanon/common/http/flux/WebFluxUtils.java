package com.kanon.common.http.flux;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author xuhua.jiang
 * @date 2021/7/1 17:29
 */
public class WebFluxUtils {

    private static Gson gson;
    private static WebClient webClient;
    private static WebClient proxyWebClient;

    @Autowired
    public WebFluxUtils(Gson gson,
                          @Qualifier("webClient") WebClient webClient,
                          @Qualifier("proxyWebClient") WebClient proxyWebClient) {
        WebFluxUtils.gson = gson;
        WebFluxUtils.webClient = webClient;
        WebFluxUtils.proxyWebClient = proxyWebClient;
    }

    /**
     * 判断是否需要走代理
     *
     * @return WebClient
     */
    private static WebClient getClient(boolean userProxy) {
        if (userProxy) {
            return proxyWebClient;
        }
        return webClient;
    }
}
