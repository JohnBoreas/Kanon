package com.kanon.charlotte.param;

import lombok.Data;

import java.util.Map;

@Data
public class SpiderParam {

    protected String spiderSource;

    protected String requestParams;

    protected Integer pageNo;

    protected Map<String, String> paramMap;

    protected String reqUrl;

    protected String reqMethod;

    protected String reqParam;

    protected Boolean needProxy;

    protected String dataType;

}
