package myNewProject.projectnew.UIactions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import myNewProject.projectnew.TestBase.BasePage;


public class LoginPageUI extends BasePage{
	
	public static final Logger log=Logger.getLogger(LoginPageUI.class.getName());
	
	//LoginPageUI page;

@FindBy(linkText="Sign in")public WebElement signIn;
@FindBy(id="email")public WebElement Email;
@FindBy(id="passwd")public WebElement password;
@FindBy(id="SubmitLogin")public WebElement submit;
@FindBy(xpath=(".//*[@id='center_column']/div[1]/ol/li"))public WebElement error;

//Registration page properties
@FindBy(id="email_create")public WebElement regEmail;
@FindBy(id="SubmitCreate")public WebElement regSubmit;
@FindBy(id="id_gender2")public WebElement regGender;
@FindBy(id="customer_firstname")public WebElement regFirstName;
@FindBy(id="customer_lastname")public WebElement regLastName;
@FindBy(id="passwd")public WebElement regPass;
@FindBy(id="days")public WebElement regDateBirth;
@FindBy(id="months")public WebElement regMonthBirth;
@FindBy(id="years")public WebElement regYearBirth;
@FindBy(id="address1")public WebElement regAddress ;
@FindBy(id="city")public WebElement regCity;
@FindBy(id="id_state")public WebElement regState;
@FindBy(id="postcode")public WebElement regPostcode;
@FindBy(id="id_country")public WebElement regCountry;
@FindBy(id="phone_mobile")public WebElement regMobilePhone;
@FindBy(id="alias")public WebElement regAlias;
@FindBy(id="submitAccount")public WebElement regSubmitAccount;

public LoginPageUI(WebDriver driver) {
PageFactory.initElements(driver, this);
}
public void LoginVerification()
{
  signIn.click();
  Email.sendKeys("juveria1@gmail.com");
  password.sendKeys("summer2018");
  submit.click();
}
 
   public void customerRegistration() throws Exception
{
	   
	signIn.click();
	log.info("CLICKED ON SIGN-IN BY USING OBJECT :"+signIn.toString());
	regEmail.sendKeys(getData("firstname")+getData("lastname")+randomNumber(5)+getData("domain"));
	log.info("ENTERED E_MAIL ADDRESS :"+regEmail.getAttribute("value")+"BY USING OBJECT "+regEmail.toString());
	System.out.println(regEmail.getAttribute("value"));
	regSubmit.click();
	
	waitForElement(regGender, 20);
	regGender.click();
	
	regFirstName.sendKeys(getData("firstname"));
	regLastName.sendKeys(getData("lastname"));
	regPass.sendKeys(getData("password"));

	selectOption(regDateBirth,26);
	selectOption(regMonthBirth, 9);
	selectOption(regYearBirth,15);
    regAddress.sendKeys(getData("Address"));
    regState.sendKeys("Indiana");
    regCity.sendKeys(getData("City"));
    regCountry.sendKeys(getData("Country"));
    regPostcode.sendKeys(randomNumber(5));
    regMobilePhone.sendKeys(randomNumber(10));
    regAlias.sendKeys("Hyderabad");
    regSubmitAccount.click();
      
    
}
   public String LoginErrorVerification()
   {
     return error.getText();
   }
}