package com.kanon.charlotte.service.spider;

import com.kanon.charlotte.param.SchedulerJobParam;

/**
 * @author xuhua.jiang
 * @date 2021/6/8 19:12
 */
public interface ScheduleJobService {

    Boolean addJob(SchedulerJobParam param);

    void deleteJob(SchedulerJobParam param);

    void restartJob(SchedulerJobParam param);

    void restartAll();

    void pauseAll();

    void shutdown();
}
