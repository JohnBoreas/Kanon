package com.kanon.common.http;

/**
 * 公用常量类。
 * 
 */
public abstract class Constants {

	/** HTTP method **/
	public static final String HTTP_METHOD_POST = "POST";
	public static final String HTTP_METHOD_GET = "GET";
	
	public static final int HTTP_CONNECT_TIMEOUT = 30 * 1000;
	public static final int HTTP_READ_TIMEOUT = 60 * 1000;
	public static final int HTTP_RETRYCOUNT = 0;
	public static final int HTTP_RETRYINTERVAL = 300;
	
	
	/** 默认时间格式 **/
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/** TOP Date默认时区 **/
	public static final String DATE_TIMEZONE = "GMT+8";

	/** UTF-8字符集 **/
	public static final String CHARSET_UTF8 = "UTF-8";

	/** GBK字符集 **/
	public static final String CHARSET_GBK = "GBK";
	
	/** ISO-8859-1字符集 **/
	public static final String CHARSET_ISO = "ISO-8859-1";


	public static final String FORMAT_JSON = "json";
	public static final String FORMAT_XML = "xml";
	

	/** MD5签名方式 */
	public static final String SIGN_METHOD_MD5 = "md5";
	/** HMAC签名方式 */
	public static final String SIGN_METHOD_HMAC = "hmac";
	

}
