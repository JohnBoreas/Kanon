package com.kanon.charlotte.service.spider.impl;

import com.kanon.charlotte.common.SpiderResult;
import com.kanon.charlotte.constants.SpiderConstants;
import com.kanon.charlotte.dao.SpiderSourceDao;
import com.kanon.charlotte.common.SpiderPageResult;
import com.kanon.charlotte.entity.SpiderExplain;
import com.kanon.charlotte.entity.SpiderRequest;
import com.kanon.charlotte.entity.SpiderSource;
import com.kanon.charlotte.param.SpiderParam;
import com.kanon.charlotte.service.StrategyService;
import com.kanon.charlotte.service.explain.ExplainStringService;
import com.kanon.charlotte.service.spider.SpiderDataService;
import com.kanon.charlotte.util.DateUtils;
import com.kanon.common.http.httpclient.HttpClientUtils;
import com.kanon.charlotte.util.StructureChangeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("spiderDataService")
@Slf4j
public class SpiderDataServiceImpl implements SpiderDataService {

    private SpiderSourceDao spiderSourceDao;

    private StrategyService<SpiderParam> explainStrategyService;

    @Autowired
    public SpiderDataServiceImpl(@Qualifier("spiderSourceDao") SpiderSourceDao spiderSourceDao,
                                 @Qualifier("explainStrategyService")StrategyService<SpiderParam> explainStrategyService) {
        this.spiderSourceDao = spiderSourceDao;
        this.explainStrategyService = explainStrategyService;
    }

    /**
     * 原始
     * @param param
     * @return
     */
    @Override
    public String originalContent(SpiderParam param) {
        // 设置参数
        setSpiderParam(param);
        // 获取request
        List<SpiderRequest> requestDtoList = spiderSourceDao.selectRequestBySource(param.getSpiderSource());
        // header map
        Map<String, String> headersParams = new HashMap<>();
        requestDtoList.stream().forEach(dto -> {
            headersParams.put(dto.getHeaderName(), dto.getHeaderValue());
        });
        // param map
        String paramStr = param.getReqParam();
        // db配置的参数
        Map<String, String> params = StructureChangeUtils.stringToMap(paramStr);
        // 传入的参数
        params.putAll(StructureChangeUtils.paramsToMap(param.getRequestParams()));
        // 当前时间
        if (params != null && params.containsKey(SpiderConstants.CURRENT_TIME)) {
            params.put(SpiderConstants.CURRENT_TIME, String.valueOf(System.currentTimeMillis()));
        }
        // 当前时间
        if (params != null && params.containsKey(SpiderConstants.CURRENT_DATE)) {
            params.put(SpiderConstants.CURRENT_DATE, DateUtils.currentDate());
        }
        param.setParamMap(params);
        String method = param.getReqMethod();
        switch (HttpMethod.resolve(method)) {
            case GET:
                return HttpClientUtils.get(param.getReqUrl(), params, null, headersParams, String.class, param.getNeedProxy());
            case HEAD:
            case POST:
            case PUT:
            case PATCH:
            case DELETE:
            case OPTIONS:
            case TRACE:
            default:
                throw new IllegalArgumentException("Invalid HTTP method: " + method);
        }
    }

    /**
     * 解释后的字段
     * @param param
     * @return
     */
    @Override
    public Object explainContent(SpiderParam param) {
        String spiderSource = param.getSpiderSource();
        try {
            // 设置参数
            setSpiderParam(param);
            // 解析
            List<SpiderExplain> explainStringDtoList = spiderSourceDao.selectExplainBySource(spiderSource);
            Map<String, SpiderExplain> dtoMap = explainStringDtoList.stream().collect(Collectors.toMap(SpiderExplain::getExplainName, Function.identity()));

            String content = originalContent(param);
            ExplainStringService explainStringService = (ExplainStringService) explainStrategyService.strategy(param);
            // 解析数据
            if (param.isPage()) {
                SpiderPageResult<Map<String, String>> spiderPageResult = explainStringService.explainPage(dtoMap, content);
                return spiderPageResult;
            }
            SpiderResult<Map<String, String>> spiderResult = explainStringService.explain(dtoMap, content);
            return spiderResult;
        } catch (Exception e) {
            log.error(spiderSource + " Fetch Error", e);
        }
        return null;
    }

    private void setSpiderParam(SpiderParam param) {
        // 参数中缺少相应的参数则查db获取
        if (StringUtils.isEmpty(param.getReqUrl())) {
            SpiderSource sourceDto = spiderSourceDao.selectSourceBySource(param.getSpiderSource());
            // 初始化抓取参数
            param.setSpiderSource(param.getSpiderSource());
            param.setReqUrl(sourceDto.getReqUrl());
            param.setNeedProxy(sourceDto.needProxy());
            // 支持 {offset} {pageNo}
            param.setReqParam(sourceDto.getReqParam());
            param.setReqMethod(sourceDto.getReqMethod());
            param.setDataType(sourceDto.getDataType());
            param.setPage(sourceDto.isPageList());
        }
    }
}
