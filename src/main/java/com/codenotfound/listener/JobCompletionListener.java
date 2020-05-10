package com.codenotfound.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.cloud.task.batch.listener.TaskBatchDao;
import org.springframework.cloud.task.batch.listener.TaskBatchExecutionListener;

public class JobCompletionListener extends TaskBatchExecutionListener {

	/**
	 * @param taskBatchDao dao used to persist the relationship.  Must not be null
	 */
	public JobCompletionListener(TaskBatchDao taskBatchDao) {
		super(taskBatchDao);
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("BATCH JOB COMPLETED SUCCESSFULLY");
		}
	}

}
