package com.kanon.charlotte.service.charlotte;

import com.kanon.charlotte.entity.SpiderSourceDto;
import com.kanon.charlotte.param.SpiderParam;

public interface SpiderSourceService {

    SpiderSourceDto selectBySpiderSource(SpiderParam param);
}
