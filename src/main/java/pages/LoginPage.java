package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage {

    //private WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10))

    // Locators
    private final By emailInputLocator = By.name("email");
    private final By passwordInputLocator = By.name("password");
    private final By loginButtonLocator = By.xpath("//input[@type='submit']");
    private final By forgottenPasswordLinkLocator = By.linkText("Forgotten Password");
    private final By logoutLinkLocator = By.xpath("//a[@class='list-group-item' and contains(text(),'Logout')]");

    // Methods
    public void enterEmail(String email) {
        WebElement emailInput = DriverFactory.getDriver().findElement(emailInputLocator);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(emailInputLocator));
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordInput = DriverFactory.getDriver().findElement(passwordInputLocator);
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButton = DriverFactory.getDriver().findElement(loginButtonLocator);
        loginButton.click();
    }

    public void clickForgottenPasswordLink() {
        WebElement forgottenPasswordLink = DriverFactory.getDriver().findElement(forgottenPasswordLinkLocator);
        forgottenPasswordLink.click();
    }

    public boolean checkForgotPwdLink(){
        return DriverFactory.getDriver().findElement(forgottenPasswordLinkLocator).isDisplayed();
    }

    public boolean checkLogoutLink(){
        return DriverFactory.getDriver().findElement(logoutLinkLocator).isDisplayed();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public String getForgotPwdPageUrl(){
        return DriverFactory.getDriver().getCurrentUrl();
    }
}
