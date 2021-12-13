package com.kanon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * html调用 thymeleaf 实现参数管理
 */
@Service("config")
public class ConfigService {

    private ISysConfigService configService;

    @Autowired
    public ConfigService(ISysConfigService configService) {
        this.configService = configService;
    }
    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数键名
     * @return 参数键值
     */
    public String getKey(String configKey) {
        return configService.selectConfigByKey(configKey);
    }
}
