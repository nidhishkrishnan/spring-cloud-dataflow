package com.codenotfound.config;

import com.codenotfound.controller.JobInvokerController;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.task.batch.configuration.TaskBatchExecutionListenerFactoryBean;
import org.springframework.cloud.task.batch.listener.TaskBatchDao;
import org.springframework.cloud.task.batch.listener.TaskBatchExecutionListener;
import org.springframework.cloud.task.batch.listener.support.JdbcTaskBatchDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.sql.DataSource;


@Configuration
public class ThreadPoolTaskSchedulerConfig {
//
/*    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        driverManagerDataSource.setUrl("jdbc:hsqldb:mem:batchdb");
        driverManagerDataSource.setUsername("sa");
        driverManagerDataSource.setPassword("");
        return driverManagerDataSource;
    }*/
//		return DataSourceBuilder.create()
//				.driverClassName("org.hsqldb.jdbcDriver")
//				.url("jdbc:hsqldb:mem:batchdb")
//				.username("sa")
//				.password("")
//				.build();

//@Bean
//public TaskBatchExecutionListener taskBatchExecutionListener(TaskBatchExecutionListenerFactoryBean jdbcTaskBatchDao) throws Exception {
//    return jdbcTaskBatchDao.getObject();
//}

}

