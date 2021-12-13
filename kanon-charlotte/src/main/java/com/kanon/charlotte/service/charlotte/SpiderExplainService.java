package com.kanon.charlotte.service.charlotte;

import com.kanon.charlotte.entity.SpiderExplainStringDto;
import com.kanon.charlotte.param.SpiderParam;

import java.util.List;

public interface SpiderExplainService {

    List<SpiderExplainStringDto> selectBySpiderSource(SpiderParam param);
}
