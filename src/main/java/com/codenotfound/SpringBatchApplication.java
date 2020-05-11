package com.codenotfound;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.dataflow.server.EnableDataFlowServer;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableTask
@EnableBatchProcessing
@SpringBootApplication
@EnableScheduling
@EnableDataFlowServer
public class SpringBatchApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBatchApplication.class, args);
  }
}
