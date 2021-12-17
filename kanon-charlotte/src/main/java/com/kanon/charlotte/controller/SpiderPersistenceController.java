package com.kanon.charlotte.controller;

import java.util.List;

import com.kanon.charlotte.entity.SpiderPersistence;
import com.kanon.charlotte.service.spider.SpiderPersistenceService;
import com.kanon.common.annotation.Log;
import com.kanon.common.core.controller.BaseController;
import com.kanon.common.core.entity.AjaxResult;
import com.kanon.common.core.page.PageResult;
import com.kanon.common.enums.BusinessType;
import com.kanon.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xuhua.jiang
 * @date 2021/12/15 17:24
 */
@Controller
@RequestMapping("/spider/persistence")
public class SpiderPersistenceController extends BaseController {

    private String prefix = "spider/persistence";

    private SpiderPersistenceService spiderPersistenceService;

    @Autowired
    public SpiderPersistenceController(SpiderPersistenceService spiderPersistenceService) {
        this.spiderPersistenceService = spiderPersistenceService;
    }

    @RequiresPermissions("spider:persistence:view")
    @GetMapping()
    public String config() {
        return prefix + "/persistence";
    }

    /**
     * 查询持久化配置列表
     */
    @RequiresPermissions("spider:persistence:list")
    @PostMapping("/list")
    @ResponseBody
    public PageResult list(SpiderPersistence spiderPersistence) {
        startPage();
        List<SpiderPersistence> list = spiderPersistenceService.selectSpiderPersistenceList(spiderPersistence);
        return getDataTable(list);
    }

    /**
     * 导出持久化配置列表
     */
    @RequiresPermissions("spider:persistence:export")
    @Log(title = "持久化配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SpiderPersistence spiderPersistence) {
        List<SpiderPersistence> list = spiderPersistenceService.selectSpiderPersistenceList(spiderPersistence);
        ExcelUtil<SpiderPersistence> util = new ExcelUtil<SpiderPersistence>(SpiderPersistence.class);
        return util.exportExcel(list, "config");
    }

    /**
     * 新增持久化配置
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存持久化配置
     */
    @RequiresPermissions("spider:persistence:add")
    @Log(title = "持久化配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SpiderPersistence spiderPersistence) {
        return toAjax(spiderPersistenceService.insertSpiderPersistence(spiderPersistence));
    }

    /**
     * 修改持久化配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        SpiderPersistence spiderPersistence = spiderPersistenceService.selectSpiderPersistenceById(id);
        mmap.put("spiderPersistence", spiderPersistence);
        return prefix + "/edit";
    }

    /**
     * 修改保存持久化配置
     */
    @RequiresPermissions("spider:persistence:edit")
    @Log(title = "持久化配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SpiderPersistence spiderPersistence) {
        return toAjax(spiderPersistenceService.updateSpiderPersistence(spiderPersistence));
    }

    /**
     * 删除持久化配置
     */
    @RequiresPermissions("spider:persistence:remove")
    @Log(title = "持久化配置", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(spiderPersistenceService.deleteSpiderPersistenceByIds(ids));
    }
}