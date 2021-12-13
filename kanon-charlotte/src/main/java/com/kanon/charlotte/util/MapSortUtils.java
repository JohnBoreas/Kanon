package com.kanon.charlotte.util;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Map 排序工具类
 */
public class MapSortUtils {
    /**
     * 按照value值倒序排列
     *
     * @param map
     * @return
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        if (CollectionUtils.isEmpty(map)) {
            return new HashMap<>();
        }
        return sortByValue(map, false, map.size());
    }

    /**
     * 按照value值倒序排列
     *
     * @param map   原map
     * @param limit top n
     * @return
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, int limit) {
        if (CollectionUtils.isEmpty(map)) {
            return new HashMap<>();
        }
        return sortByValue(map, false, limit);
    }

    /**
     * 按照value值排列
     *
     * @param map      原map
     * @param positive true: 正序, false: 倒序
     * @param limit    数量
     * @return
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, boolean positive, int limit) {
        if (CollectionUtils.isEmpty(map)) {
            return new HashMap<>();
        }
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        list.sort((o1, o2) -> (positive ? 1 : -1) * (o1.getValue().compareTo(o2.getValue())));
        Map<K, V> result = new LinkedHashMap<>(limit << 1, 1f);
        for (Map.Entry<K, V> entry : list) {
            if (result.size() >= limit) {
                break;
            }
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }


    /**
     * 按照value值正序排列
     *
     * @param map
     * @return
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValuePositive(Map<K, V> map) {
        if (CollectionUtils.isEmpty(map)) {
            return new HashMap<>();
        }
        return sortByValue(map, true, map.size());
    }

    /**
     * 按照value值正序排列
     *
     * @param map   原map
     * @param limit top n
     * @return
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValuePositive(Map<K, V> map, int limit) {
        if (CollectionUtils.isEmpty(map)) {
            return new HashMap<>();
        }
        return sortByValue(map, true, limit);
    }

    /**
     * 按照key的顺序，对map进行排序或过滤
     *
     * @param map   原map
     * @param keys key的集合
     * @return
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByKeys(Map<K, V> map, Collection<K> keys) {
        if(CollectionUtils.isEmpty(map) || CollectionUtils.isEmpty(keys)){
            return new HashMap<>();
        }
        Map<K,V> resultMap = new HashMap<>();
        for(K key : keys){
            if(null != map.get(key)){
                resultMap.put(key,map.get(key));
            }
        }
        return resultMap;
    }

    /**
     * 按照key的顺序，对map进行过滤
     *
     * @param map   原map
     * @param filterKeys 要过滤的key的集合
     * @return
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> filterByKeys(Map<K, V> map, Collection<K> filterKeys) {
        if(CollectionUtils.isEmpty(map) || CollectionUtils.isEmpty(filterKeys)){
            return map;
        }
        Iterator<K> iter = map.keySet().iterator();
        while(iter.hasNext()){
            if(filterKeys.contains(iter.next())){
                iter.remove();
            }
        }
        return map;
    }

    /**
     * 按照value值排列
     *
     * @param map      原map
     * @param positive true: 正序, false: 倒序
     * @return
     */
    public static <K, V extends Comparable<? super V>> Map<K, Integer> getIndexBysortValue(Map<K, V> map, boolean positive) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        list.sort((o1, o2) -> (positive ? 1 : -1) * (o1.getValue().compareTo(o2.getValue())));
        Map<K, Integer> result = new LinkedHashMap<>(map.size(), 1f);
        for (int i = 0; i < list.size(); i++) {
            result.put(list.get(i).getKey(), i + 1);
        }
        return result;
    }

}
