package myNewProject.projectnew;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import myNewProject.projectnew.TestBase.BasePage;
import myNewProject.projectnew.UIactions.LoginPageUI;

public class TC001 extends BasePage{
	public static final Logger log=Logger.getLogger(TC001.class.getName());
	
    LoginPageUI page;
  @BeforeClass
  @Parameters("browser")
  public void startProcess(String browser) throws Exception {
	  browserSetup(browser,getData("url"));
	  
  }

   @Test
  public void customerRegistration() throws Exception {
	  log.info("----------Starting TC001 Test----------");
	  
	  page=new LoginPageUI(driver);
	  page.customerRegistration();
	  
	  log.info("-----------Ending TC001 Test----------");
	  
	  
  }
  
  @AfterClass
  public void endProcess() {
  }

}
