package com.kanon.charlotte.controller;

import com.kanon.charlotte.result.Result;
import com.kanon.charlotte.service.deal.DataDealService;
import com.kanon.charlotte.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuhua.jiang
 * @date 2021/12/24 13:09
 */
@Slf4j
@RestController
@RequestMapping(value = "/deal")
public class DataDealController {

    private DataDealService dataDealService;

    @Autowired
    public DataDealController(@Qualifier("dataDealService") DataDealService dataDealService) {
        this.dataDealService = dataDealService;
    }

    @GetMapping(value = "/updateACode")
    public Result updateACode() {
        String currentDate = DateUtils.currentDate();
        int count = dataDealService.updateACode(currentDate);
        return Result.success(count);
    }
}
