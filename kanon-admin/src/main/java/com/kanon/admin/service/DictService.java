package com.kanon.admin.service;

import com.kanon.admin.entity.SystemDictData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * html调用 thymeleaf 实现字典读取
 */
@Service("dict")
public class DictService {

    private ISysDictTypeService dictTypeService;

    private ISysDictDataService dictDataService;

    @Autowired
    public DictService(ISysDictTypeService dictTypeService,
                       ISysDictDataService dictDataService) {
        this.dictTypeService = dictTypeService;
        this.dictDataService = dictDataService;
    }
    /**
     * 根据字典类型查询字典数据信息
     *
     * @param dictType 字典类型
     * @return 参数键值
     */
    public List<SystemDictData> getType(String dictType) {
        return dictTypeService.selectDictDataByType(dictType);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    public String getLabel(String dictType, String dictValue) {
        return dictDataService.selectDictLabel(dictType, dictValue);
    }
}
