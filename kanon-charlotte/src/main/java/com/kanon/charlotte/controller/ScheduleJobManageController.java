package com.kanon.charlotte.controller;

import com.kanon.charlotte.param.SchedulerJobParam;
import com.kanon.charlotte.result.Result;
import com.kanon.charlotte.service.spider.ScheduleJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuhua.jiang
 * @date 2021/6/8 14:49
 */
@Slf4j
@RestController
@RequestMapping(value = "/job")
public class ScheduleJobManageController {

    private final ScheduleJobService scheduleJobService;

    @Autowired
    public ScheduleJobManageController(@Qualifier("scheduleJobService") ScheduleJobService scheduleJobService) {
        this.scheduleJobService = scheduleJobService;
    }
    /**
     * 新增任务
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/add")
    public Result addJob(SchedulerJobParam param) throws Exception {
        Boolean isAdd = scheduleJobService.addJob(param);
        return Result.success(isAdd);
    }

    /**
     * 获取任务列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/list")
    public Result list() {

        return null;
    }

    /**
     * 立即执行
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/restart")
    public Result restart(SchedulerJobParam param) throws Exception {

        return null;
    }

    /**
     * 暂停任务
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/pauseschedule")
    public Result pause(SchedulerJobParam param) throws Exception {

        return null;
    }

    /**
     * 从暂停中恢复过来
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/resume")
    public Result resume(SchedulerJobParam param) throws Exception {

        return null;
    }

    /**
     * 删除任务
     * @param param
     * @return
     */
    @RequestMapping(value = "/remove")
    public Result remove(SchedulerJobParam param) throws Exception {
        return null;
    }
}
