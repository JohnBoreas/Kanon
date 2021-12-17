package com.kanon.charlotte.controller;

import com.kanon.charlotte.param.SchedulerParam;
import com.kanon.charlotte.result.Result;
import com.kanon.charlotte.spider.FetchOrgReportSpider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author boreas
 * @create 2021-09-17 16:35
 */
@Slf4j
@RestController
@RequestMapping(value = "/specialSource")
public class SpiderSpecialSourceController {

    private FetchOrgReportSpider fetchOrgReportSpider;

    @Autowired
    public SpiderSpecialSourceController(@Qualifier("fetchOrgReportSpider") FetchOrgReportSpider fetchOrgReportSpider) {
        this.fetchOrgReportSpider = fetchOrgReportSpider;
    }

    /**
     * 抓取机构
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/org")
    public Result addJob(SchedulerParam param) throws Exception {
        fetchOrgReportSpider.fetch();
        return Result.success(1);
    }
}
