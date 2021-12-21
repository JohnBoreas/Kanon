package com.kanon.charlotte.service.spider.impl;

import com.kanon.charlotte.dao.ScheduleJobDao;
import com.kanon.charlotte.entity.SchedulerManage;
import com.kanon.charlotte.mapper.SchedulerManageMapper;
import com.kanon.charlotte.param.SchedulerParam;
import com.kanon.charlotte.service.spider.SchedulerManageService;
import com.kanon.charlotte.util.ScheduleJobUtils;
import com.kanon.common.core.text.Convert;
import com.kanon.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuhua.jiang
 * @date 2021/6/8 19:17
 */
@Service("schedulerManageService")
@Slf4j
public class SchedulerManageServiceImpl implements SchedulerManageService {

    private ScheduleJobDao scheduleJobDao;

    private SchedulerManageMapper schedulerManageMapper;

    @Autowired
    public SchedulerManageServiceImpl(@Qualifier("scheduleJobDao") ScheduleJobDao scheduleJobDao,
                                      @Qualifier("schedulerManageMapper") SchedulerManageMapper schedulerManageMapper) {
        this.scheduleJobDao = scheduleJobDao;
        this.schedulerManageMapper = schedulerManageMapper;
    }

    public Boolean addJob(SchedulerParam param) {
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

    public void restartJob(SchedulerParam param) {

    }

    public void restartAll() {

    }

    public void pauseAll() {

    }

    public void shutdown() {

    }

    @Override
    public SchedulerManage selectSchedulerManageById(Long id) {
        return schedulerManageMapper.selectSchedulerManageById(id);
    }

    @Override
    public List<SchedulerManage> selectSchedulerManageList(SchedulerManage schedulerManage) {
        return schedulerManageMapper.selectSchedulerManageList(schedulerManage);
    }

    @Override
    public int insertSchedulerManage(SchedulerManage schedulerManage) {
        SchedulerParam param = new SchedulerParam();
        BeanUtils.copyProperties(schedulerManage, param);
        String id = scheduleJobDao.selectIdByUnique(param.getJobName(), param.getJobGroup());
        if (StringUtils.isNotEmpty(id)) {
            return -1;
        }
        Boolean isAdd = ScheduleJobUtils.addJob(param);
        schedulerManage.setStatus("1");
        if (isAdd) {
            int dbId = schedulerManageMapper.insertSchedulerManage(schedulerManage);
            log.info("新建job成功：" + param.getJobName() + "-" + param.getJobGroup());
            return dbId;
        }
        return -1;
    }

    @Override
    public int updateSchedulerManage(SchedulerManage schedulerManage) {
        schedulerManage.setUpdateTime(DateUtils.getNowDate());
        schedulerManage.setStatus("1");
        int i = schedulerManageMapper.updateSchedulerManage(schedulerManage);
        if (i > 0) {
            SchedulerParam param = new SchedulerParam();
            BeanUtils.copyProperties(schedulerManage, param);
            Boolean isUpdate= ScheduleJobUtils.updateJob(param);
            if (isUpdate) {
                log.info("更新job成功：" + param.getJobName() + "-" + param.getJobGroup());
                return 1;
            }
        }
        return -1;
    }

    @Override
    public int deleteSchedulerManageByIds(String ids) {
        return schedulerManageMapper.deleteSchedulerManageByIds(Convert.toStrArray(ids));
    }

    @Override
    public int deleteSchedulerManageById(Long id) {
        SchedulerManage schedulerManage = schedulerManageMapper.selectSchedulerManageById(id);
        if (schedulerManage != null) {
            SchedulerParam param = new SchedulerParam();
            BeanUtils.copyProperties(schedulerManage, param);
            boolean isDel = ScheduleJobUtils.deleteJob(param.getJobName(), param.getJobGroup());
            if (isDel) {
                schedulerManageMapper.deleteSchedulerManageById(id);
                log.info("删除job成功：" + param.getJobName() + "-" + param.getJobGroup());
                return 1;
            }
        }
        return -1;
    }
}
