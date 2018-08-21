package myNewProject.projectnew.customerListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import myNewProject.projectnew.TestBase.BasePage;

public class ListenerClass extends BasePage implements ITestListener {

	/*public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
	
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}*/

	public void onTestFailure(ITestResult arg0) {
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format=new SimpleDateFormat("DD_MM_YYYY_HH_MM_SS");
		String method=arg0.getName();
		Reporter.log("TEST IS A FAILURE :"+arg0.getMethod().getMethodName());
		
		if(!arg0.isSuccess())
		{
			        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			        try 
			        {
			            String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/myProject/projectnew/failScreenShots/uiAutomation/";
			            //String methodName;
						File destFile = new File((String) reportDirectory + "/failScreenShots/" + method + "_" + format.format(cal.getTime()) + ".png");
			            
						 FileUtils.copyFile(scrFile, destFile);
			            
			            Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
			            
			        } 
			        catch (IOException e) 
			        {
			            e.printStackTrace();
			        }
		}
		
	}
		// TODO Auto-generated method stub
		

	public void onTestSkipped(ITestResult arg0) {
		Reporter.log("TEST HAS BEEN SKIPPED :"+arg0.getMethod().getMethodName());
		
	}

	public void onTestStart(ITestResult arg0) {
		Reporter.log("TEST HAS STARTED RUNNING :"+arg0.getMethod().getMethodName());
		
	}

	public void onTestSuccess(ITestResult arg0) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format=new SimpleDateFormat("DD_MM_YYYY_HH_MM_SS");
		String method=arg0.getName();
		Reporter.log("TEST IS SUCCESS :"+arg0.getMethod().getMethodName());
		
		if(arg0.isSuccess())
		{
			        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			        try 
			        {
			            String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/com/test/automation/uiAutomation/";
			  
						File destFile = new File((String) reportDirectory + "/passScreenShots/" + method + "_" + format.format(cal.getTime()) + ".png");
			            
						 FileUtils.copyFile(scrFile, destFile);
			            
			            Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
			            
			        } 
			        catch (IOException e) 
			        {
			            e.printStackTrace();
			        }
		}
		
	}


	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}


	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}


	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
