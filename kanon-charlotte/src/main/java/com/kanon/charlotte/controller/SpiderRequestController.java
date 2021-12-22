package com.kanon.charlotte.controller;

import com.kanon.charlotte.entity.SpiderRequest;
import com.kanon.charlotte.param.SpiderConfigParam;
import com.kanon.charlotte.service.spider.SpiderRequestService;
import com.kanon.common.annotation.Log;
import com.kanon.common.core.controller.BaseController;
import com.kanon.common.core.entity.AjaxResult;
import com.kanon.common.core.page.PageResult;
import com.kanon.common.enums.BusinessType;
import com.kanon.common.utils.poi.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/spider/request")
public class SpiderRequestController extends BaseController {

    private String prefix = "spider/request";

    @Autowired
    private SpiderRequestService spiderRequestService;

    @RequiresPermissions("spider:request:view")
    @GetMapping()
    public String request() {
        return prefix + "/request";
    }

    /**
     * 查询请求request配置列表
     */
    @RequiresPermissions("spider:request:list")
    @PostMapping("/list")
    @ResponseBody
    public PageResult list(SpiderRequest spiderRequest) {
        startPage();
        List<SpiderRequest> list = spiderRequestService.selectSpiderRequestList(spiderRequest);
        return getDataTable(list);
    }

    /**
     * 导出请求request配置列表
     */
    @RequiresPermissions("spider:request:export")
    @Log(title = "请求request配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SpiderRequest SpiderRequest) {
        List<SpiderRequest> list = spiderRequestService.selectSpiderRequestList(SpiderRequest);
        ExcelUtil<SpiderRequest> util = new ExcelUtil<>(SpiderRequest.class);
        return util.exportExcel(list, "request");
    }

    /**
     * 新增请求request配置
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存请求request配置
     */
    @RequiresPermissions("spider:request:add")
    @Log(title = "请求request配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SpiderRequest SpiderRequest) {
        return toAjax(spiderRequestService.insertSpiderRequest(SpiderRequest));
    }

    /**
     * 新增请求request配置
     */
    @GetMapping("/adds")
    public String adds() {
        return prefix + "/adds";
    }

    /**
     * 新增保存请求request配置
     */
    @RequiresPermissions("spider:request:adds")
    @Log(title = "请求request配置", businessType = BusinessType.INSERT)
    @PostMapping("/adds")
    @ResponseBody
    public AjaxResult addsSave(SpiderConfigParam param) {
        if (param.getHeaders() != null && param.getHeaders().size() > 0) {
            int count = 0;
            for (SpiderRequest request : param.getHeaders()) {
                SpiderRequest spiderRequest = new SpiderRequest();
                spiderRequest.setSpiderSource(param.getSpiderSource());
                spiderRequest.setHeaderName(request.getHeaderName());
                spiderRequest.setHeaderValue(request.getHeaderValue());
                int result = spiderRequestService.insertSpiderRequest(spiderRequest);
                if (result > 0) {
                    count ++;
                }
            }
            return toAjax(count);
        }
        return AjaxResult.warn("没有填入Header");
    }

    /**
     * 修改请求request配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        SpiderRequest SpiderRequest = spiderRequestService.selectSpiderRequestById(id);
        mmap.put("spiderRequest", SpiderRequest);
        return prefix + "/edit";
    }

    /**
     * 修改保存请求request配置
     */
    @RequiresPermissions("spider:request:edit")
    @Log(title = "请求request配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SpiderRequest SpiderRequest) {
        return toAjax(spiderRequestService.updateSpiderRequest(SpiderRequest));
    }

    /**
     * 删除请求request配置
     */
    @RequiresPermissions("spider:request:remove")
    @Log(title = "请求request配置", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(spiderRequestService.deleteSpiderRequestByIds(ids));
    }
}
