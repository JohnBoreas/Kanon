package com.kanon.charlotte.controller;

import com.github.pagehelper.Page;
import com.kanon.charlotte.dao.ScheduleJobDao;
import com.kanon.charlotte.entity.SchedulerManage;
import com.kanon.charlotte.mapper.SchedulerManageMapper;
import com.kanon.charlotte.param.SchedulerParam;
import com.kanon.charlotte.service.spider.SchedulerManageService;
import com.kanon.common.annotation.Log;
import com.kanon.common.core.controller.BaseController;
import com.kanon.common.core.entity.AjaxResult;
import com.kanon.common.core.page.PageResult;
import com.kanon.common.enums.BusinessType;
import com.kanon.common.utils.poi.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xuhua.jiang
 * @date 2021/6/8 14:49
 */
@Slf4j
@Controller
@RequestMapping("/scheduler/manage")
public class SchedulerManageController extends BaseController {

    private String prefix = "scheduler/manage";

    private SchedulerManageService schedulerManageService;

    @Autowired
    public SchedulerManageController(@Qualifier("schedulerManageService") SchedulerManageService schedulerManageService) {
        this.schedulerManageService = schedulerManageService;
    }

    @RequiresPermissions("scheduler:manage:view")
    @GetMapping()
    public String job() {
        return prefix + "/manage";
    }

    /**
     * 查询爬虫任务配置列表
     */
    @RequiresPermissions("scheduler:manage:list")
    @PostMapping("/list")
    @ResponseBody
    public PageResult list(SchedulerManage schedulerManage) {
        startPage();
        List<SchedulerManage> list = schedulerManageService.selectSchedulerManageList(schedulerManage);
        return getDataTable(list);
    }

    /**
     * 新增爬虫任务配置
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存爬虫任务配置
     */
    @RequiresPermissions("scheduler:manage:add")
    @Log(title = "爬虫任务配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SchedulerManage schedulerManage) {
        int isAdd = schedulerManageService.insertSchedulerManage(schedulerManage);
        return toAjax(isAdd);
    }

    /**
     * 修改爬虫任务配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        SchedulerManage SchedulerManage = schedulerManageService.selectSchedulerManageById(id);
        mmap.put("schedulerManage", SchedulerManage);
        return prefix + "/edit";
    }

    /**
     * 修改保存爬虫任务配置
     */
    @RequiresPermissions("scheduler:manage:edit")
    @Log(title = "爬虫任务配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SchedulerManage schedulerManage) {
        return toAjax(schedulerManageService.updateSchedulerManage(schedulerManage));
    }

    /**
     * 删除爬虫任务配置
     */
    @RequiresPermissions("scheduler:manage:remove")
    @Log(title = "爬虫任务配置", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(schedulerManageService.deleteSchedulerManageByIds(ids));
    }
}