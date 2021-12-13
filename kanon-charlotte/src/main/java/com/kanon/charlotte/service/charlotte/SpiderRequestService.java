package com.kanon.charlotte.service.charlotte;

import com.kanon.charlotte.entity.SpiderRequestDto;
import com.kanon.charlotte.param.SpiderParam;

import java.util.List;

public interface SpiderRequestService {

    List<SpiderRequestDto> selectBySpiderSource(SpiderParam param);
}
