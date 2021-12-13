package com.kanon.charlotte.service.persistence;

import com.kanon.charlotte.param.PersistenceParam;
import com.kanon.charlotte.service.BaseService;

/**
 * @author xuhua.jiang
 * @date 2021/6/22 14:22
 */
public interface PersistenceDataService extends BaseService {

    Integer save(PersistenceParam param);
}
