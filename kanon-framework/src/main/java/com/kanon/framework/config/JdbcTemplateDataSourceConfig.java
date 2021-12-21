package com.kanon.framework.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * JdbcTemplate多数据源配置
 * 依赖于数据源配置
 *
 * @see DynamicDataSourceConfig
 */
@Configuration
public class JdbcTemplateDataSourceConfig {

    // JdbcTemplate主数据源数据源
    @Primary
    @Bean(name = "masterJdbcTemplate")
    public JdbcTemplate masterJdbcTemplate(@Qualifier("masterDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    // JdbcTemplate第二个数据源
    @Bean(name = "spiderJdbcTemplate")
    public JdbcTemplate spiderJdbcTemplate(@Qualifier("spiderDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "wealthJdbcTemplate")
    public JdbcTemplate wealthJdbcTemplate(@Qualifier("wealthDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}