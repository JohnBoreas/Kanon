package com.kanon.charlotte.param;

import com.kanon.charlotte.entity.SpiderExplain;
import com.kanon.charlotte.entity.SpiderRequest;
import lombok.Data;

import java.util.List;

/**
 * @author xuhua.jiang
 * @date 2021/12/22 14:42
 */
@Data
public class SpiderConfigParam {

    protected String spiderSource;

    protected List<SpiderRequest> headers;

    protected List<SpiderExplain> explains;

}