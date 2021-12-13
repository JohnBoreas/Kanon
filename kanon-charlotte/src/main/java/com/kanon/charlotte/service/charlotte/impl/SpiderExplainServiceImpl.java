package com.kanon.charlotte.service.charlotte.impl;

import com.kanon.charlotte.dao.SpiderExplainDao;
import com.kanon.charlotte.entity.SpiderExplainStringDto;
import com.kanon.charlotte.param.SpiderParam;
import com.kanon.charlotte.service.charlotte.SpiderExplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("spiderExplainService")
public class SpiderExplainServiceImpl implements SpiderExplainService {

    @Autowired
    private SpiderExplainDao spiderExplainDao;

    @Override
    public List<SpiderExplainStringDto> selectBySpiderSource(SpiderParam param) {
        return spiderExplainDao.selectBySource(param.getSpiderSource());
    }
}
