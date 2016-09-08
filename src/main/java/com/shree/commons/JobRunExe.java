package com.shree.commons;


import java.util.ArrayList;
import java.util.List;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class JobRunExe extends QuartzJobBean{
	
	List<String> command = new ArrayList<String>();
	String path;
	
	public JobRunExe(){
		System.out.println("Job2 is created");
	}
	
	public void setPath(String pt){
		System.out.println("I m in setter");
		this.path = pt;
	}
	
	
	public String getPath(){
		return path;
	}
	
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("Entered job 2");
	System.out.println("Path in job bean is : "+path);
	
	//command.add("C:/Users/20706/command.cmd");
	command.add(path);
	ProcessBuilder processBuilder = new ProcessBuilder(command);
	
	try{
		Process process = processBuilder.start();
		process.waitFor();
		System.out.println(process.exitValue());
		
	}
	catch(Exception e){System.out.println(e);}
	}	
}	