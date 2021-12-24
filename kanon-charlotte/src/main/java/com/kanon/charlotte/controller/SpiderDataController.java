package com.kanon.charlotte.controller;

import com.kanon.charlotte.param.SpiderParam;
import com.kanon.charlotte.result.Result;
import com.kanon.charlotte.service.spider.SpiderDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/spiderData")
public class SpiderDataController {

    private SpiderDataService spiderDataService;

    @Autowired
    public SpiderDataController(@Qualifier("spiderDataService") SpiderDataService spiderDataService) {
        this.spiderDataService = spiderDataService;
    }

    @GetMapping(value = "/original")
    public Result originalContent(@Validated SpiderParam param) {
        return Result.success(spiderDataService.originalContent(param));
    }

    @GetMapping(value = "/explain")
    public Result explainContent(@Validated SpiderParam param) {
        return Result.success(spiderDataService.explainContent(param));
    }

}
