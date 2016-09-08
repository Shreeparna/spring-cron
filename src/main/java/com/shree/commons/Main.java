package com.shree.commons;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

class ExePath{
	String path;
}

class Input{
	String crnEx, job, path;
	int jobId;
	
	Input(){
		
		crnEx=null;
		job=null;
		path = null;
	}
	/*public Input(String crnEx, String job){
		this.crnEx = crnEx;
		this.job = job;
	}*/
}

class CallJobs extends Thread{
	public void run(){
		System.out.println("running thread name is:"+Thread.currentThread().getName());  
		System.out.println("running thread priority is:"+Thread.currentThread().getPriority());  
		
		try{
		Thread.sleep(5000);
		}
		
		catch(Exception e)
		{System.out.println(e);} 
//		try{
//		 BufferedReader reader = new BufferedReader(new FileReader("D:/JAVA/Spring3-Quartz-Example/SpringExample/src/main/resources/CronInput.properties"));  
//       String line = null;
//       line = reader.readLine();
//       System.out.println("Hi am  here: "+line);
//		}
//		catch(Exception e){System.out.println(e);}
		
		System.out.println("I am before classpathxmlappcontext object creation");
		
		
		ClassPathXmlApplicationContext ob = new ClassPathXmlApplicationContext("Cron-Spring-Quartz.xml");
		
		
		System.out.println("I am after classpathxmlappcontext object creation");
	     }	
	  }


class WriteFile extends Thread{
	
	public Input inp(){
		String job="";
		String path=null;
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Enter cron exp 1");
		String crnEx = sc.nextLine();
		//String crnEx = "0/5 * * * * ?";
		
		System.out.println(" 1. Run Chrome /n 2. Run an Exe /n 3. Run Java Update");
		System.out.println("Enter job no. -");
		int jobno= sc.nextInt();
		
		
		if(jobno == 1)
			job = "com.shree.commons.JobRunChrome";
		
		if(jobno == 2){
		    job = "com.shree.commons.JobRunExe";
		    System.out.println("Enter .cmd path");
		    sc.nextLine();
		    path = sc.nextLine();
		}
		
		if(jobno == 3)
		    job = "com.shree.commons.Job1";
		
		//String job = "com.mkyong.common.RunMeJob";
		
		System.out.println("cron ex is :"+crnEx);
		System.out.println("job is :"+jobno);
		System.out.println("Job is: "+job);
		
		if(path != "")
			System.out.println("Path is: "+path);
		
		Input iob =  new Input();
		
		iob.jobId= jobno;
		iob.job = job;
		iob.crnEx=crnEx;
		
		if(path != null)
			iob.path = path;
		
		sc.close();
		//return;
		return iob;
	}

	public void run() {
		System.out.println("running thread name is:"+Thread.currentThread().getName());  
		System.out.println("running thread priority is:"+Thread.currentThread().getPriority());  

		WriteFile ob = new WriteFile();
		Input ip = ob.inp();

		System.out.println("After insertion in object cron ex: "+ ip.crnEx);
		System.out.println("After insertion in object cron ex: "+ip.job);
		
		if(ip.path != "") 
			System.out.println("After insertion in object cron ex: "+ip.path);

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		System.out.println(System.getProperty("java.class.path"));
		
		
		
		URL url = classLoader.getResource("CronInput.properties");

		try {
			String path = url.toURI().getPath();
			File file = new File(path);
			System.out.println(path);       
			//File outFile =  new File("D:/JAVA/Spring3-Quartz-Example/SpringExample/src/main/resources/CronInput.properties");
			FileWriter fWriter=null;
			fWriter = new FileWriter(file, true);
			PrintWriter pWriter = new PrintWriter(fWriter);
			
			pWriter.println("jdbc.jobClass1=com.shree.commons.JobRunChrome");
			pWriter.println("jdbc.jobClass2=com.shree.commons.JobRunExe");
			pWriter.println("jdbc.jobClass3=com.shree.commons.Job1");
			
			String doNotRunCrn = "59 59 23 31 12 ? 2099";
			
			
			
			if(ip.jobId == 1){
			
				pWriter.println("jdbc.cronExpression1="+ip.crnEx);
				pWriter.println("jdbc.cronExpression2"+doNotRunCrn);
				pWriter.println("jdbc.cronExpression3="+doNotRunCrn);
			}
			
			if(ip.jobId == 2){
				pWriter.println("jdbc.cronExpression1="+doNotRunCrn);
				pWriter.println("jdbc.cronExpression2="+ip.crnEx);
				pWriter.println("jdbc.cronExpression3="+doNotRunCrn);
			}
			
			if(ip.jobId == 3){
				pWriter.println("jdbc.cronExpression1="+doNotRunCrn);				
				pWriter.println("jdbc.cronExpression2="+doNotRunCrn);
				pWriter.println("jdbc.cronExpression3="+ip.crnEx);
			}
			
			
			if(ip.path != ""){
				System.out.println("Before writing to cron.proprties file: "+ ip.path);
				pWriter.println("jdbc.path="+ip.path);
			}
			pWriter.println();
			pWriter.close();
			fWriter.close();
			//new ClassPathXmlApplicationContext("Spring-Quartz.xml");	
			pWriter.flush();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}


	
public class Main{

	public static void main(String[] args) {
		
		CallJobs t2 = new CallJobs();
	  WriteFile t1 = new WriteFile();
	    
	    t1.start();
	    try{
	    	t1.join();
	    	System.out.println("Thread t1 joined!");
	    }
	    catch(Exception e){System.out.println(e);}	    	    	    
	    t2.start();
		
//		ApplicationContext context = new ClassPathXmlApplicationContext("SpringSetVar.xml");
//		SetUserName vr1 = (SetUserName)context.getBean("SetVariable1");
//		SetUserName vr2 = (SetUserName)context.getBean("SetVariable2");
//	    vr1.printUname();	
//	    vr2.printUname();	
		}	

	
	}
