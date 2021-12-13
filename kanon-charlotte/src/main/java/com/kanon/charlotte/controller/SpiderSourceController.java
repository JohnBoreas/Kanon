package com.kanon.charlotte.controller;

import com.kanon.charlotte.param.SpiderParam;
import com.kanon.charlotte.result.Result;
import com.kanon.charlotte.service.charlotte.SpiderSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/spiderSource")
public class SpiderSourceController {

    private final SpiderSourceService spiderSourceService;

    @Autowired
    public SpiderSourceController(@Qualifier("spiderSourceService") SpiderSourceService spiderSourceService) {
        this.spiderSourceService = spiderSourceService;
    }

    @GetMapping(value = "/create")
    public void createSource() {

    }

    @GetMapping(value = "/update")
    public void updateSource() {

    }

    @GetMapping(value = "/delete")
    public Result deleteSource(@Validated SpiderParam param) {
        return Result.success(spiderSourceService.selectBySpiderSource(param));
    }


}
