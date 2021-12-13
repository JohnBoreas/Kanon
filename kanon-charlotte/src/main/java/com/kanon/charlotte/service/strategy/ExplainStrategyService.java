package com.kanon.charlotte.service.strategy;

import com.kanon.charlotte.constants.DataType;
import com.kanon.charlotte.service.explain.ExplainStringService;
import com.kanon.charlotte.param.SpiderParam;
import com.kanon.charlotte.service.BaseService;
import com.kanon.charlotte.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author xuhua.jiang
 * @date 2021/6/9 14:04
 */
@Service("explainStrategyService")
public class ExplainStrategyService implements StrategyService<SpiderParam> {

    @Qualifier("explainHtmlStringService")
    @Autowired
    protected ExplainStringService explainHtmlStringService;

    @Qualifier("explainJsonStringService")
    @Autowired
    protected ExplainStringService explainJsonStringService;

    @Override
    public BaseService strategy(SpiderParam param) {
        String dataTYpe = param.getDataType();
        switch (Objects.requireNonNull(DataType.resolve(dataTYpe))) {
            case JSON:
                return explainJsonStringService;
            case HTML:
                return explainHtmlStringService;
            default:
                throw new IllegalArgumentException("Invalid data type: " + dataTYpe);
        }
    }
}
