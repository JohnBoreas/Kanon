package com.kanon.common.http.httpurlconnection;

public class HttpConfig {

    /**
     * This variable is set by the {@code setDoInput} method. Its
     * value is returned by the {@code getDoInput} method.
     */
    protected boolean doInput = true;

    /**
     * This variable is set by the {@code setDoOutput} method. Its
     * value is returned by the {@code getDoOutput} method.
     */
    protected boolean doOutput = false;

    private static boolean defaultAllowUserInteraction = false;
    /**
     *
     */
    private boolean allowUserInteraction = defaultAllowUserInteraction;

    private static boolean defaultUseCaches = true;
    /**
     *
     */
    private boolean useCaches = defaultUseCaches;

    /**
     * Some protocols support skipping the fetching of the object unless
     * the object has been modified more recently than a certain time.
     */
    private long ifModifiedSince = 0;
    /**
     *
     */
    private boolean connected = false;
    /**
     * @since 1.5
     */
    private int connectTimeout;

    private int readTimeout;

    /**
     * @since 1.2.2
     */
    private static boolean fileNameMapLoaded = false;

    public boolean isDoInput() {
        return doInput;
    }

    public void setDoInput(boolean doInput) {
        this.doInput = doInput;
    }

    public boolean isDoOutput() {
        return doOutput;
    }

    public void setDoOutput(boolean doOutput) {
        this.doOutput = doOutput;
    }

    public boolean isAllowUserInteraction() {
        return allowUserInteraction;
    }

    public void setAllowUserInteraction(boolean allowUserInteraction) {
        this.allowUserInteraction = allowUserInteraction;
    }

    public static boolean isDefaultUseCaches() {
        return defaultUseCaches;
    }

    public boolean isUseCaches() {
        return useCaches;
    }

    public void setUseCaches(boolean useCaches) {
        this.useCaches = useCaches;
    }

    public long getIfModifiedSince() {
        return ifModifiedSince;
    }

    public void setIfModifiedSince(long ifModifiedSince) {
        this.ifModifiedSince = ifModifiedSince;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public static boolean isFileNameMapLoaded() {
        return fileNameMapLoaded;
    }
}
