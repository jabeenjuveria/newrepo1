package myNewProject.projectnew;

import org.testng.annotations.Test;

import myNewProject.projectnew.UIactions.excelReader;

import org.testng.annotations.BeforeMethod;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterMethod;

public class TC002 extends excelReader {
	public static final Logger log=Logger.getLogger(TC002.class.getName());

 
  @BeforeMethod
  public static void logInitiate()
	  
		{
			String log4jconf="log4j.properties";
			PropertyConfigurator.configure(log4jconf);
		}

  @Test
  public void getvalue() throws Exception
  {
	 log.info("VALUES FROM EXCEL ROWS OF DATA SHEET ARE.....");
	 System.out.println(getValue("userName"));
	 System.out.println(getValue("password"));
  }
  
  @AfterMethod
  public void End() {
  }

}
