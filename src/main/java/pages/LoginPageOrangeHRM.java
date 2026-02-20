package pages;

import java.sql.Driver;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageOrangeHRM {

	private final By userName=By.xpath("//label[contains(text(),'Username')]");
	private final By password=By.xpath("//label[contains(text(),'Password')]");
	private final By usernameField=By.xpath("//input[@name='username']");
	private final By passwordField=By.xpath("//input[@name='password']");
	private final By loginButton=By.xpath("//button[normalize-space(text()='Login')]");
	
	WebDriverWait wait=new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
	public void isUserNameDisplayed()
	{
		try {
			WebElement username=wait.until(ExpectedConditions.visibilityOfElementLocated(userName));
			username.isDisplayed();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			System.out.println("usenrname should display");
		}
		
	}
	
	public void isPasswordDisplayed()
	{
		try {
			WebElement passWord=wait.until(ExpectedConditions.visibilityOfElementLocated(password));
			passWord.isDisplayed();
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	public void enterUsernameAndPassword(String username,String password)
	{
		try {
			WebElement loginUsername=wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
			WebElement loginPassword=wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
		//WebElement loginUsername=DriverFactory.getDriver().findElement(usernameField);
		//WebElement loginPassword=DriverFactory.getDriver().findElement(passwordField);
		
		
		loginUsername.sendKeys(username);
		loginPassword.sendKeys(password);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void clickOnLoginButton()
	{
		WebElement login=DriverFactory.getDriver().findElement(loginButton);
		login.click();
	}
}
