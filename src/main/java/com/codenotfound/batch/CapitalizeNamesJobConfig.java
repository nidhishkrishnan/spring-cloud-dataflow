/*
package com.codenotfound.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.ReferenceJobFactory;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import com.codenotfound.model.Person;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CapitalizeNamesJobConfig {

  private final JobBuilderFactory jobBuilders;
  private final StepBuilderFactory stepBuilders;
  private final JobRegistry jobRegistry;

  public Job getJob(String jobName) throws DuplicateJobException, NoSuchJobException {
    try {
      Job job = jobBuilders.get(jobName)
              .incrementer(new RunIdIncrementer())
              //.listener(new JobExecutionErrorNewrelicNotifier())
              .flow(convertNamesStep())
              .end()
              .build();
      ReferenceJobFactory jobFactory = new ReferenceJobFactory(job);
      jobRegistry.register(jobFactory);
      return job;
    } catch (Exception nse) {
      return jobRegistry.getJob(jobName);
    }
  }


  public Step convertNamesStep() {
    return stepBuilders.get("capitalizeNamesStep")
        .<Person, Person>chunk(10).reader(itemReader())
        .processor(itemProcessor()).writer(itemWriter()).build();
  }

  public FlatFileItemReader<Person> itemReader() {
    BeanWrapperFieldSetMapper<Person> fieldSetMapper =
        new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(Person.class);

    DelimitedLineTokenizer lineTokenizer =
        new DelimitedLineTokenizer();
    lineTokenizer.setDelimiter(",");
    lineTokenizer.setNames(new String[] {"firstName", "lastName"});

    DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>();
    lineMapper.setFieldSetMapper(fieldSetMapper);
    lineMapper.setLineTokenizer(lineTokenizer);

    FlatFileItemReader<Person> flatFileItemReader =
        new FlatFileItemReader<>();
    flatFileItemReader.setName("personItemReader");
    flatFileItemReader
        .setResource(new ClassPathResource("csv/persons.csv"));
    flatFileItemReader.setLineMapper(lineMapper);

    return flatFileItemReader;
  }


  public PersonItemProcessor itemProcessor() {
    return new PersonItemProcessor();
  }


  public FlatFileItemWriter<Person> itemWriter() {
    BeanWrapperFieldExtractor<Person> fieldExtractor =
        new BeanWrapperFieldExtractor<>();
    fieldExtractor.setNames(new String[] {"firstName", "lastName"});
    fieldExtractor.afterPropertiesSet();

    DelimitedLineAggregator<Person> lineAggregator =
        new DelimitedLineAggregator<>();
    lineAggregator.setDelimiter(",");
    lineAggregator.setFieldExtractor(fieldExtractor);

    FlatFileItemWriter<Person> flatFileItemWriter =
        new FlatFileItemWriter<>();
    flatFileItemWriter.setName("personItemWriter");
    flatFileItemWriter.setResource(
        new FileSystemResource("target/test-outputs/persons.txt"));
    flatFileItemWriter.setLineAggregator(lineAggregator);

    return flatFileItemWriter;
  }
}
*/
