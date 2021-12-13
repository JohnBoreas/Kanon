package com.kanon.common.http.httpurlconnection;

import javax.net.ssl.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BaseHttpConnection {

    public void doGet(String url) {
        String content = null;
        HttpURLConnection connection = null;
        try {
            URI uri = new URI(url);
            CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(cookieManager);
            connection = createConnection(uri);
            connection.setDoOutput(true);// 设置输出可用
            connection.setRequestMethod("");// 设置请求方式
            connection.setUseCaches(false);// 设置缓存不可用
            connection.setConnectTimeout(5000);// 设置连接超时
            connection.setReadTimeout(5000);// 设置读取超时
            connection.setInstanceFollowRedirects(true);
            Map<String, String> headerMap = new HashMap<>();
            if (headerMap != null && !headerMap.isEmpty()) {
                for (String key : headerMap.keySet()) {
                    connection.setRequestProperty(key, headerMap.get(key));
                }
            }
            connection.connect();
            OutputStream outputStream = null;
            Map<String, String> params = new HashMap<>();
            // 只有当POST请求时才会执行此代码段
            if (params != null) {
                // 获取输出流,connection.getOutputStream已经包含了connect方法的调用
                outputStream = connection.getOutputStream();
                StringBuilder sb = new StringBuilder();
                Set<Map.Entry<String, String>> sets = params.entrySet();
                // 将Hashmap转换为string
                for (Map.Entry<String, String> entry : sets) {
                    sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                }
                String param = sb.substring(0, sb.length() - 1);
                // 使用输出流将string类型的参数写到服务器
                outputStream.write(param.getBytes());
                outputStream.flush();
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = connection.getInputStream();
                String result = inputStream2String(inputStream);

            } else {

            }
        } catch (IOException e) {

        } catch (Exception e) {

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * 字节流转换成字符串
     *
     * @param inputStream
     * @return
     */
    private static String inputStream2String(InputStream inputStream) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        try {
            while ((len = inputStream.read(bytes)) != -1) {
                baos.write(bytes, 0, len);
            }
            return new String(baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static HttpURLConnection createConnection(URI uri) throws IOException {
        URL url = uri.toURL();
        URLConnection connection;
        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
        } catch (Exception e) {
            throw new IOException(e);
        }
        if ("192.168.0.1" != null && 11 > 0) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("config.getProxyHost()", 21));
            connection = url.openConnection(proxy);
        } else {
            connection = url.openConnection();
        }
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) connection;
        httpsURLConnection.setSSLSocketFactory(ctx.getSocketFactory());
        httpsURLConnection.setHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;// 默认都认证通过
            }
        });
        return httpsURLConnection;
    }


    public void doPost() {

    }

    private static class DefaultTrustManager extends X509ExtendedTrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s, Socket socket) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s, Socket socket) throws CertificateException {

        }

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s, SSLEngine sslEngine) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s, SSLEngine sslEngine) throws CertificateException {

        }
    }
}
