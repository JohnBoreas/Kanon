package com.kanon.charlotte.service.charlotte.impl;

import com.kanon.charlotte.dao.SpiderRequestDao;
import com.kanon.charlotte.entity.SpiderRequestDto;
import com.kanon.charlotte.param.SpiderParam;
import com.kanon.charlotte.service.charlotte.SpiderRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service("spiderRequestService")
public class SpiderRequestServiceImpl implements SpiderRequestService {

    @Autowired
    private SpiderRequestDao spiderRequestDao;


    @Override
    public List<SpiderRequestDto> selectBySpiderSource(SpiderParam param) {
        return spiderRequestDao.selectBySource(param.getSpiderSource());
    }
}
