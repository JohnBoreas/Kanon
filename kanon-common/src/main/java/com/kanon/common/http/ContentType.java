package com.kanon.common.http;

import java.util.HashMap;
import java.util.Map;

public enum ContentType {
    HTML,
    XML,
    JSON,
    PLAIN,
    JS,
    SOAP,
    
    JPG,
    GIF,
    PNG,
    BMP,
    ICO,
    TIF,
    UNKNOWN;
    
    private static final Map<String, ContentType> typeMap = new HashMap<>();
    
    static {
    	typeMap.put("text/html", ContentType.HTML);
    	typeMap.put("text/htm", ContentType.HTML);
    	typeMap.put("text/xml", ContentType.XML);
    	typeMap.put("text/json", ContentType.JSON);
    	typeMap.put("text/plain", ContentType.PLAIN);
    	typeMap.put("text/javascript", ContentType.JS);
    	typeMap.put("text/x-javascript", ContentType.JS);
    	typeMap.put("text/soap+xml", ContentType.SOAP);
    	
    	typeMap.put("application/html", ContentType.HTML);
    	typeMap.put("application/htm", ContentType.HTML);
    	typeMap.put("application/xml", ContentType.XML);
    	typeMap.put("application/json", ContentType.JSON);
    	typeMap.put("application/x-javascript", ContentType.JS);
    	typeMap.put("application/javascript", ContentType.JS);
    	typeMap.put("application/soap+xml", ContentType.SOAP);
    	
    	typeMap.put("image/jpeg", ContentType.JPG);
    	typeMap.put("image/jpg", ContentType.JPG);
    	typeMap.put("image/gif", ContentType.GIF);
    	typeMap.put("image/png", ContentType.PNG);
    	typeMap.put("image/bmp", ContentType.BMP);
    	typeMap.put("image/x-icon", ContentType.ICO);
    	typeMap.put("image/x-ico", ContentType.ICO);
    	typeMap.put("image/tiff", ContentType.TIF);
    	typeMap.put("image/tif", ContentType.TIF);
    	
    }

    public static ContentType getType(String type) {
//    	if(StringUtils.isEmpty(type)) {
//    		return UNKNOWN;
//		}
    	type = type.toLowerCase();
        if (typeMap.containsKey(type))
            return typeMap.get(type);
        else
            return UNKNOWN;
    }
	
}
