package com.kanon.common.core.controller;

import com.kanon.common.config.Global;
import com.kanon.common.config.ServerConfig;
import com.kanon.common.constant.Constants;
import com.kanon.common.core.entity.AjaxResult;
import com.kanon.common.utils.StringUtils;
import com.kanon.common.utils.file.FileUploadUtils;
import com.kanon.common.utils.file.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用请求处理
 *
 */
@Slf4j
@Controller
public class CommonController {

    @Autowired
    private ServerConfig serverConfig;

    private static final String FILE_DELIMETER = ",";

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete   是否删除
     */
    @GetMapping("common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response) {
        try {
            if (!FileUtils.checkAllowDownload(fileName)) {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }

            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = Global.getDownloadPath() + fileName;

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);

            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete) {
                FileUtils.deleteFile(filePath);
            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/common/upload")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file, HttpServletRequest request) throws Exception {
        try {
            // 上传文件路径
            String filePath = Global.getUploadPath();
            // 上传并返回新文件名称
            String url = FileUploadUtils.upload(filePath, file);
            //String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", file.getOriginalFilename());
            ajax.put("url", url);
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }


    /**
     * 通用上传请求（多个）
     */
    @PostMapping("/common/uploads")
    @ResponseBody
    public AjaxResult uploadFiles(List<MultipartFile> files) throws Exception {
        try {
            // 上传文件路径
            String filePath = Global.getUploadPath();
            List<String> fileNames = new ArrayList<String>();
            List<String> urls = new ArrayList<String>();
            for (MultipartFile file : files) {
                // 上传并返回新文件名称
                String fileName = FileUploadUtils.upload(filePath, file);
                String url = serverConfig.getUrl() + fileName;
                fileNames.add(fileName);
                urls.add(url);
            }
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileNames", StringUtils.join(fileNames, FILE_DELIMETER));
            ajax.put("urls", StringUtils.join(urls, FILE_DELIMETER));
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    @PostMapping("/common/upload/get")
    @ResponseBody
    public AjaxResult getUploadFile(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        if (StringUtils.isNotEmpty(id)) {
            Map<String, Object> data = new HashMap();
            data.put("url", "");
            return AjaxResult.success(data);
        }
        return AjaxResult.error();
    }

    @PostMapping("/common/upload/delete")
    @ResponseBody
    public AjaxResult deleteUploadFile(HttpServletRequest request) {
        String id = request.getParameter("id");
        if (StringUtils.isNotEmpty(id)) {
            String url = "attachment.getFileUrl()";
            try {
                FileUploadUtils.deleteFile(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String url = request.getParameter("url");
            if (StringUtils.isNotEmpty(url)) {
                try {
                    FileUploadUtils.deleteFile(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return AjaxResult.success();
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/common/download/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (!FileUtils.checkAllowDownload(resource)) {
            throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", resource));
        }
        // 本地资源路径
        String localPath = Global.getProfile();
        // 数据库资源地址
        String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
        // 下载名称
        String downloadName = StringUtils.substringAfterLast(downloadPath, "/");

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, downloadName);

        FileUtils.writeBytes(downloadPath, response.getOutputStream());
    }
}
