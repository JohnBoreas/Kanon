package com.kanon.common.cache;

import org.quartz.Scheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * cache
 */
public class CoreSchedulerCache {

    public static volatile List<Scheduler> PROXY = new ArrayList<>();

}
