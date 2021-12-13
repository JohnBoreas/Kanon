package com.kanon.framework.config;

import com.kanon.common.cache.HttpConstantsCache;
import com.kanon.common.constant.HttpConstants;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class HttpClientConfig {

    @Bean
    public RestTemplate restTemplate(@Qualifier("httpClient") CloseableHttpClient httpClient) {
        RestTemplate restTemplate = new RestTemplate();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        restTemplate.setRequestFactory(requestFactory);
        // 使用httpClient
        return restTemplate;
    }

    @Bean
    public RestTemplate proxyRestTemplate(@Qualifier("proxyHttpClient") CloseableHttpClient httpClient) {
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        restTemplate.setRequestFactory(requestFactory);
        // 使用httpClient
        return restTemplate;
    }

    @Bean("httpClient")
    @Qualifier("httpClient")
    public CloseableHttpClient httpClient(@Qualifier("requestConfig") RequestConfig requestConfig,
                                          @Qualifier("connectionManager") HttpClientConnectionManager connectionManager) {
        return HttpClientBuilder.create()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

    /**
     * 使用随机代理
     * @param requestConfig
     * @param connectionManager
     * @return
     */
    @Bean("proxyHttpClient")
    @Qualifier("proxyHttpClient")
    public CloseableHttpClient proxyHttpClient(@Qualifier("requestConfig") RequestConfig requestConfig,
                                          @Qualifier("connectionManager") HttpClientConnectionManager connectionManager) {
        return HttpClientBuilder.create()
                .setRoutePlanner(new DefaultRoutePlanner(null) {
                    @Override
                    public HttpHost determineProxy(HttpHost target, HttpRequest request, HttpContext context) {
                        return HttpConstantsCache.PROXY
                                .get(ThreadLocalRandom.current().nextInt(0, HttpConstantsCache.PROXY.size()));
                    }
                })
//                .setKeepAliveStrategy(connectionKeepAliveStrategy)
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
//                .disableAutomaticRetries()
                .build();
    }

    @Bean("requestConfig")
    @Qualifier("requestConfig")
    public RequestConfig requestConfig() {
        // 配置信息
        return RequestConfig.custom()
                // 设置连接超时时间(单位毫秒)
                .setConnectTimeout(HttpConstants.CONNECTION_TIMEOUT)
                // 设置请求超时时间(单位毫秒)
                .setConnectionRequestTimeout(HttpConstants.CONNECTION_REQUEST_TIMEOUT)
                // socket读写超时时间(单位毫秒)
                .setSocketTimeout(HttpConstants.SOCKET_TIMEOUT)
                // 设置是否允许重定向(默认为true)
                .setRedirectsEnabled(true)
                .build();
    }

    @Bean("connectionManager")
    @Qualifier("connectionManager")
    public PoolingHttpClientConnectionManager poolingConnectionManager(@Qualifier("connectionConfig") ConnectionConfig connectionConfig) {
        // scheme
        Registry<ConnectionSocketFactory> registry = RegistryBuilder
                .<ConnectionSocketFactory>create()
                .register(HttpConstants.HTTP, new PlainConnectionSocketFactory())
                .register(HttpConstants.HTTPS, SSLConnectionSocketFactory.getSystemSocketFactory())
                .build();
        // connectionManager
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        connectionManager.setMaxTotal(HttpConstants.MAX_TOTAL);
        connectionManager.setDefaultMaxPerRoute(HttpConstants.DEFAULT_MAX_PER_ROUTE);
        connectionManager.setDefaultConnectionConfig(connectionConfig);
        // 每过一段时间检测永久连接是否失效, 单位毫秒
//      connectionManager.setValidateAfterInactivity(997);
        return connectionManager;
    }

    /**
     *
     * @return
     */
    @Bean("connectionConfig")
    @Qualifier("connectionConfig")
    public ConnectionConfig connectionConfig() {
        return ConnectionConfig.custom()
                .setCharset(Charset.defaultCharset())
                .build();
    }
}
