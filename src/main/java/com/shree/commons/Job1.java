package com.shree.commons;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class Job1 extends QuartzJobBean {
	private Task1 taskClass;
	
	public void setTaskClass(Task1 tClass) {
		this.taskClass = tClass;
	}

	public Task1 getTaskClass() {
		return taskClass;
	}
	
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
      System.out.println("I am in job file");
		taskClass.doSomeJob();
	}
}