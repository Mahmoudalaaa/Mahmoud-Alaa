package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase {
    public LoginPage(WebDriver driver) {
        super(driver);
        driver.get("https://www.saucedemo.com/"); // Move this line here
    }

    @FindBy(id = "user-name") // Add the correct locator (e.g., By.id)
    private WebElement usernamebox;

    @FindBy(id = "password") // Add the correct locator (e.g., By.id)
    private WebElement passwordbox;

    @FindBy(id = "login-button") // Add the correct locator (e.g., By.id)
    private WebElement loginbutton;

    public void userLogin(String username, String password) {
        usernamebox.sendKeys(username);
        passwordbox.sendKeys(password);
        loginbutton.click();
    }
}
