package com.kanon.charlotte.service.spider;

import com.kanon.charlotte.param.SpiderParam;

public interface SpiderDataService {

    String originalContent(SpiderParam param);

    Object explainContent(SpiderParam param);
}
