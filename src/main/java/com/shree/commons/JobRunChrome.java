package com.shree.commons;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobRunChrome implements Job {
	
 public void execute(JobExecutionContext arg0) throws JobExecutionException{
	 System.out.println("Execeuting the 1st job");
	 //(CAN USE)List<String> command = new ArrayList<String>();
	  List<String> command = new ArrayList<String>();
	  command.add("cmd.exe");
	  command.add("/c");
	  command.add("start \"C:/Program Files (x86)/Google/Chrome/Application/chrome.exe\" http://www.google.com");

	/* 
	 String command;
 	 StaticLoggerBinder ob = null;	
	 (WORKS)to create a notepad file with the following name
	 command.add("notepad.exe");
	 command.add("myFile.txt");
	 String[] arg = {"cmd","/c","C:/Source File/test.c"};
	 ProcessBuilder pb = new ProcessBuilder(arg);
	 Process pr = pb.start();  
	 (WORKS)command.add("C:/Users/20706/command.cmd");
	 command = "C:/Program Files(x86)/Google/Chrome/Application&chrome.exe";
	 ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe","/c",command);
	 String[] command = {"cmd","/c","C:/Program Files(x86)/Google/Chrome/Application&chrome.exe"};
	  */
	  
	 ProcessBuilder processBuilder = new ProcessBuilder(command);
	 
	 try {
		Process process = processBuilder.start();
		process.waitFor();
		System.out.println(process.exitValue());
	 } catch (Exception e) {
		e.printStackTrace();
	}
	 
 }
}

