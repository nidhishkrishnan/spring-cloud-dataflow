package com.codenotfound.config;

import com.codenotfound.step.Processor;
import com.codenotfound.step.Reader;
import com.codenotfound.step.Writer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.ReferenceJobFactory;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.task.batch.listener.TaskBatchExecutionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Slf4j
@EnableBatchProcessing
@Component
@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilder;

    @Autowired
    public StepBuilderFactory stepBuilder;

    @Autowired
    public JobRegistry jobRegistry;

    @Autowired
    public JobRepository jobRepository;

	@Autowired
	private TaskBatchExecutionListener taskBatchExecutionListener;

    @Bean
    public Job initialJob() {
        return jobBuilder.get("initializingJob")
                .incrementer(new RunIdIncrementer())
                .start(stepBuilder.get("initializingStep").tasklet((stepContribution, chunkContext) -> RepeatStatus.FINISHED).build())
                .build();
    }


    public Job getJob(String jobName) throws Exception {
        try {
            return jobRegistry.getJob(jobName);
        } catch (NoSuchJobException nse) {
            log.error("Failed:: {}", nse.getMessage());
            Job job = jobBuilder.get(jobName)
                    .incrementer(new RunIdIncrementer())
                    .flow(orderStep1())
                    .end()
                    .listener(taskBatchExecutionListener)
                    .build();
            ReferenceJobFactory jobFactory = new ReferenceJobFactory(job);
            jobRegistry.register(jobFactory);
            return job;
        }
    }

    public Step orderStep1() {
        return stepBuilder.get("orderStep1").<String, String>chunk(1)
                .reader(new Reader()).processor(new Processor())
                .writer(new Writer()).build();
    }
}
