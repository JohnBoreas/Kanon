package com.kanon.charlotte.service.charlotte.impl;

import com.kanon.charlotte.dao.SpiderSourceDao;
import com.kanon.charlotte.entity.SpiderSourceDto;
import com.kanon.charlotte.param.SpiderParam;
import com.kanon.charlotte.service.charlotte.SpiderSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service("spiderSourceService")
public class SpiderSourceServiceImpl implements SpiderSourceService {

    @Autowired
    private SpiderSourceDao spiderSourceDao;


    @Override
    public SpiderSourceDto selectBySpiderSource(SpiderParam param) {
        return spiderSourceDao.selectBySource(param.getSpiderSource());
    }
}
