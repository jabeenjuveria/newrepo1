package myNewProject.projectnew.customerListener;

import java.util.logging.Logger;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Retry implements IRetryAnalyzer{
	
	
	public static final Logger log=Logger.getLogger(Retry.class.getName());
	private int min_count=0;
	private int max_count=2;
	
	
	public boolean retry(ITestResult arg0) {
		if(min_count<max_count)
		{
			log("RETRYING TEST"+arg0.getName()+"WITH STATUS"+getResultStatusName(arg0.getStatus())+"FOR THE"
		     +(min_count+1)+"ONE TIME");
			min_count++;
			  return true;
			
		}
		return false;
	}

	public void log(String data) 
	{
	  log.info(data);
	  Reporter.log(data);
	  
	}

	
	
	public String getResultStatusName(int status) {
		String resultname=null;
		if(status==1)
		    resultname="SUCCESS";
		if(status==2)
			resultname="FAILURE";
		if(status==3)
			resultname="SKIP";
		
		
		// TODO Auto-generated method stub
		return resultname;
	}
	
	

}
