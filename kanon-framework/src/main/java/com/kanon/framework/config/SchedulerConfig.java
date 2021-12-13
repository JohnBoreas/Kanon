package com.kanon.framework.config;

import com.kanon.framework.factory.CustomJobFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @author xuhua.jiang
 * @date 2021/6/8 19:06
 */
public class SchedulerConfig {
    /*
     * 通过SchedulerFactoryBean获取Scheduler
     */
//    @Bean(name = "scheduler")
    public Scheduler scheduler(@Qualifier("schedulerFactoryBean") SchedulerFactoryBean schedulerFactoryBean,
                               @Qualifier("customJobFactory") CustomJobFactory customJobFactory) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        // 自定义 JobFactory 使得在 Quartz Job 中可以使用 @Autowired
        scheduler.setJobFactory(customJobFactory);
        return schedulerFactoryBean.getScheduler();
    }

    /**
     * schedulerFactoryBean
     * @return
     * @throws IOException
     */
//    @Bean(name = "schedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("spiderDataSource") DataSource dataSource) throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setAutoStartup(true);
        factory.setStartupDelay(5);//延时5秒启动
        factory.setQuartzProperties(quartzProperties());
        // 必须配置数据源, 配置文件无法拿到config里的数据源，需要配置bean来获取
        factory.setDataSource(dataSource);
        return factory;
    }

    /**
     * 配置
     * @return
     * @throws IOException
     */
//    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        // 这个东西如果不配置的话，quartz有自己默认的配置文件
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }
}
