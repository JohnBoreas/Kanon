package com.kanon.charlotte.controller;

import com.kanon.charlotte.entity.SpiderSource;
import com.kanon.charlotte.service.charlotte.SpiderSourceService;
import com.kanon.common.annotation.Log;
import com.kanon.common.core.controller.BaseController;
import com.kanon.common.core.entity.AjaxResult;
import com.kanon.common.core.page.PageResult;
import com.kanon.common.enums.BusinessType;
import com.kanon.common.utils.poi.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/spider/source")
public class SpiderSourceController extends BaseController {

    private final SpiderSourceService spiderSourceService;

    private String prefix = "spider/source";

    @Autowired
    public SpiderSourceController(@Qualifier("spiderSourceService") SpiderSourceService spiderSourceService) {
        this.spiderSourceService = spiderSourceService;
    }

    @RequiresPermissions("system:source:view")
    @GetMapping()
    public String source() {
        return prefix + "/source";
    }

    /**
     * 查询请求来源配置列表
     */
    @RequiresPermissions("system:source:list")
    @PostMapping("/list")
    @ResponseBody
    public PageResult list(SpiderSource spiderSource) {
        startPage();
        List<SpiderSource> list = spiderSourceService.selectSpiderSourceList(spiderSource);
        return getDataTable(list);
    }

    /**
     * 导出请求来源配置列表
     */
    @RequiresPermissions("system:source:export")
    @Log(title = "请求来源配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SpiderSource tbSpiderSource) {
        List<SpiderSource> list = spiderSourceService.selectSpiderSourceList(tbSpiderSource);
        ExcelUtil<SpiderSource> util = new ExcelUtil<>(SpiderSource.class);
        return util.exportExcel(list, "source");
    }

    /**
     * 新增请求来源配置
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存请求来源配置
     */
    @RequiresPermissions("system:source:add")
    @Log(title = "请求来源配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SpiderSource tbSpiderSource) {
        return toAjax(spiderSourceService.insertSpiderSource(tbSpiderSource));
    }

    /**
     * 修改请求来源配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        SpiderSource source = spiderSourceService.selectSpiderSourceById(id);
        mmap.put("source", source);
        return prefix + "/edit";
    }

    /**
     * 修改保存请求来源配置
     */
    @RequiresPermissions("system:source:edit")
    @Log(title = "请求来源配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SpiderSource tbSpiderSource) {
        return toAjax(spiderSourceService.updateSpiderSource(tbSpiderSource));
    }

    /**
     * 删除请求来源配置
     */
    @RequiresPermissions("system:source:remove")
    @Log(title = "请求来源配置", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(spiderSourceService.deleteSpiderSourceByIds(ids));
    }
}
