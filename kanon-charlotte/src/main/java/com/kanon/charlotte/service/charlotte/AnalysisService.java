package com.kanon.charlotte.service.charlotte;

import java.util.Map;

public interface AnalysisService {

    Map<String, String> changeMap(String content);

    <T> T changeBean(String content, Class<T> clazz);
}
