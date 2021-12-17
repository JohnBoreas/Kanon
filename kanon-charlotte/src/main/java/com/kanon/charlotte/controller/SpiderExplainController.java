package com.kanon.charlotte.controller;

import com.kanon.charlotte.entity.SpiderExplain;
import com.kanon.charlotte.service.spider.SpiderExplainService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xuhua.jiang
 * @date 2021/12/15 17:42
 */
@Controller
@RequestMapping("/spider/explain")
public class SpiderExplainController extends BaseController {
    private String prefix = "spider/explain";

    @Autowired
    private SpiderExplainService spiderExplainService;

    public SpiderExplainController(SpiderExplainService spiderExplainService) {
        this.spiderExplainService = spiderExplainService;
    }

    @RequiresPermissions("spider:explain:view")
    @GetMapping()
    public String string() {
        return prefix + "/explain";
    }

    /**
     * 查询返回内容解析规则配置列表
     */
    @RequiresPermissions("spider:explain:list")
    @PostMapping("/list")
    @ResponseBody
    public PageResult list(SpiderExplain spiderExplain) {
        startPage();
        List<SpiderExplain> list = spiderExplainService.selectSpiderExplainList(spiderExplain);
        return getDataTable(list);
    }

    /**
     * 导出返回内容解析规则配置列表
     */
    @RequiresPermissions("spider:explain:export")
    @Log(title = "返回内容解析规则配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SpiderExplain spiderExplain) {
        List<SpiderExplain> list = spiderExplainService.selectSpiderExplainList(spiderExplain);
        ExcelUtil<SpiderExplain> util = new ExcelUtil<SpiderExplain>(SpiderExplain.class);
        return util.exportExcel(list, "string");
    }

    /**
     * 新增返回内容解析规则配置
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存返回内容解析规则配置
     */
    @RequiresPermissions("spider:explain:add")
    @Log(title = "返回内容解析规则配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SpiderExplain spiderExplain) {
        return toAjax(spiderExplainService.insertSpiderExplain(spiderExplain));
    }

    /**
     * 修改返回内容解析规则配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        SpiderExplain spiderExplain = spiderExplainService.selectSpiderExplainById(id);
        mmap.put("spiderExplain", spiderExplain);
        return prefix + "/edit";
    }

    /**
     * 修改保存返回内容解析规则配置
     */
    @RequiresPermissions("spider:explain:edit")
    @Log(title = "返回内容解析规则配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SpiderExplain spiderExplain) {
        return toAjax(spiderExplainService.updateSpiderExplain(spiderExplain));
    }

    /**
     * 删除返回内容解析规则配置
     */
    @RequiresPermissions("spider:explain:remove")
    @Log(title = "返回内容解析规则配置", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(spiderExplainService.deleteSpiderExplainByIds(ids));
    }
}