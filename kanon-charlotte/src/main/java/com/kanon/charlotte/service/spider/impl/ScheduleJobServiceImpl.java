package com.kanon.charlotte.service.spider.impl;

import com.kanon.charlotte.dao.ScheduleJobDao;
import com.kanon.charlotte.param.SchedulerJobParam;
import com.kanon.charlotte.service.spider.ScheduleJobService;
import com.kanon.charlotte.util.ScheduleJobUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author xuhua.jiang
 * @date 2021/6/8 19:17
 */
@Service("scheduleJobService")
@Slf4j
public class ScheduleJobServiceImpl implements ScheduleJobService {

    private ScheduleJobDao scheduleJobDao;

    @Autowired
    public ScheduleJobServiceImpl(@Qualifier("scheduleJobDao") ScheduleJobDao scheduleJobDao) {
        this.scheduleJobDao = scheduleJobDao;
    }

    @Override
    public Boolean addJob(SchedulerJobParam param) {
        String id = scheduleJobDao.selectIdByUnique(param.getJobName(), param.getJobGroup());
        if (StringUtils.isNotEmpty(id)) {
            return false;
        }
        Boolean isAdd = ScheduleJobUtils.addJob(param);
        if (isAdd) {
            scheduleJobDao.insertJob(param);
            log.info("新建job成功：" + param.getJobName() + "-" + param.getJobGroup());
            return true;
        }
        return false;
    }

    @Override
    public void deleteJob(SchedulerJobParam param) {

    }

    @Override
    public void restartJob(SchedulerJobParam param) {

    }

    @Override
    public void restartAll() {

    }

    @Override
    public void pauseAll() {

    }

    @Override
    public void shutdown() {

    }
}
