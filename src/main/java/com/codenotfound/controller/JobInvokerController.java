package com.codenotfound.controller;

import com.codenotfound.config.BatchConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.task.batch.listener.TaskBatchDao;
import org.springframework.cloud.task.repository.TaskExecution;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class JobInvokerController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    private BatchConfig batchConfig;

    @Autowired
    public JobRepository jobRepository;
//    @Autowired
//    TaskExecution taskExecution;

  //  @RequestMapping("/invokejob")
    @Scheduled(fixedDelay = 5000)
    public String handle() throws Exception {

        JobParameters jobParameters = new JobParametersBuilder()

                .addLong("time",System.currentTimeMillis())
                .toJobParameters();
//        jobRepository.createJobExecution("job1", jobParameters);


        JobExecution jobExecution = jobLauncher.run(batchConfig.getJob("job1"), jobParameters);



        return "Batch job has been invoked";
    }


 
/*    @Autowired
    JobLauncher jobLauncher;
 
    @Autowired
    CapitalizeNamesJobConfig batchConfig;

    @Autowired
    private TaskScheduler taskScheduler;
 
    @RequestMapping("/invokejob")
    public String handle() throws Exception {


        try {
            Job job  = batchConfig.getJob("de");
            JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
            jobLauncher.run(job, jobParameters);
            log.info("Batch job has been invoked");
        } catch (Exception e) {
            log.error("error launching job for {}", e.getMessage());
        }
 
        return "Batch job has been invoked";
    }

   // @PostConstruct
    public void initJob() {


        taskScheduler.scheduleWithFixedDelay(shouldRunJob(() -> {
            try {
                Job job  = batchConfig.getJob("de");
                JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
                jobLauncher.run(job, jobParameters);
                log.info("Batch job has been invoked");
            } catch (Exception e) {
                log.error("error launching job for {}", e.getMessage());
            }
        }), 1000);
    }

    private Runnable shouldRunJob(Runnable inner) {
        return () -> inner.run();
    }*/
}
