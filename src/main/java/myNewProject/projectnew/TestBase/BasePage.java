package myNewProject.projectnew.TestBase;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BasePage
{
	public static WebDriver driver;
	public static final String path="./data.properties";
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ITestResult result;
	public static final Logger log=Logger.getLogger(BasePage.class.getName());;

	public static String getData(String key) throws Exception
	{
		
		File f = new File(path);
		FileInputStream fis= new FileInputStream(f); 
		Properties p= new Properties();
		p.load(fis);
		return p.getProperty(key);
		
	}
	public static void logInitiate()
	{
		String log4jconf="log4j.properties";
		PropertyConfigurator.configure(log4jconf);
	}
	
	public String randomNumber(int range) {
		Random r=new Random();
		if(range==5)
		{
			return String.valueOf(r.nextInt(90000)+10000);
			
		}else if(range==10)
		{
			return String.valueOf(r.nextInt(900000000)+100000000);
			
		}
		else return null;
	}
	public void selectOption(WebElement element,int option)
	{
		Select s=new Select(element);
		s.selectByIndex(option);
	}
    

	public static void waitForElement(WebElement element,int seconds)
	{
		WebDriverWait wait=new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	@AfterClass()
	public void endProcess()
	{
		closeBrowser();
	}
	
	public void closeBrowser() {
		
		driver.quit();
		log.info("BROWSER CLOSED...");
		extent.endTest(test);
		extent.flush();
		
	}
	@BeforeMethod
	public void beforeMethod(Method r)
	{
		extent=new ExtentReports(System.getProperty("user.dir")+"\\ExterntReportResult.html");
		test=extent.startTest(r.getName());
		test.log(LogStatus.INFO, r.getName()+"......TEST HAS STARTED.....");
	}
	@AfterMethod
	public void afterMethod(ITestResult itr)
	{
		getResult(itr);
	}
	public void getResult(ITestResult itr) 
	{
		if(itr.getStatus()==ITestResult.SUCCESS)
		{
			test.log(LogStatus.PASS,itr.getName()+".......TEST IS PASSED.....");
		}
		else if(itr.getStatus()==ITestResult.SKIP)
		{
			test.log(LogStatus.SKIP, itr.getName()+"TEST IS SKIPPED AND REASON IS:"+itr.getThrowable());
		}
		else if(itr.getStatus()==ITestResult.FAILURE)
		{
			test.log(LogStatus.ERROR, itr.getName()+"TEST IS FAILED"+itr.getThrowable());
			String screencap=captureScreen("");
			test.log(LogStatus.FAIL,test.addScreenCapture(screencap));
		}
	}
	public String captureScreen(String filename) {
		if(filename=="")
		{
			filename="blank";
		}

	    File destFile = null;
	    Calendar calendar = Calendar.getInstance();
	    SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

	    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	    try 
	    {
	        String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/com/test/automation/UIAutomation/screenshot/";
	        destFile = new File((String) reportDirectory + filename + "_" + formater.format(calendar.getTime()) + ".png");
	        FileUtils.copyFile(scrFile, destFile);
	        // This will help us to link the screen shot in testNG report
	        Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	    return destFile.toString();
	}
		public static void browserSetup(String browsertype,String url)
	{
		logInitiate();
		if(browsertype.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\drivers\\Chrome2.40.exe");
			driver=new ChromeDriver();
			
		}
		else if (browsertype.equalsIgnoreCase("internetExplorer"))
		{
			
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\drivers\\IEDriverServer.exe");
		    driver=new InternetExplorerDriver();
		    
		  
		
		}
		else if (browsertype.equalsIgnoreCase("firefox"))
		{/*
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"C:\\Users\\MARS\\Desktop\\selinium drivers\\geckodriver.exe");
			File pathToBinary = new File("C:\\Users\\MARS\\Downloads\\firefox-46.0.1.win64.sdk\\firefox-sdk\\bin\\firefox.exe");
			FirefoxBinary Fb= new FirefoxBinary(pathToBinary);
			FirefoxProfile fireProfile=new FirefoxProfile();
			FirefoxDriver driverff=new FirefoxDriver(Fb,pathToBinary);
			driver=new FirefoxDriver();
			
			*/
	              
	}
     driver.get(url);
     driver.manage().window().maximize();
}
}
