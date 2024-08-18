package tests;

import Pages.LoginPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    // Define an enum for user types
    enum UserType {
        STANDARD_USER,
        LOCKED_OUT_USER,
        PROBLEM_USER,
        ERROR_USER,
        VISUAL_USER,
        VALID_USER_INVALID_PASSWORD,
        VALID_USER_EMPTY_PASSWORD,
        EMPTY_USER_EMPTY_PASSWORD
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
                { UserType.STANDARD_USER, "standard_user", "secret_sauce" },
                { UserType.LOCKED_OUT_USER, "locked_out_user", "secret_sauce" },
                { UserType.PROBLEM_USER, "problem_user", "secret_sauce" },
                { UserType.ERROR_USER, "error_user", "secret_sauce" },
                { UserType.VISUAL_USER, "visual_user", "secret_sauce" },
                { UserType.VALID_USER_INVALID_PASSWORD, "standard_user", "wrong_password" },
                { UserType.VALID_USER_EMPTY_PASSWORD, "standard_user", "" },
                { UserType.EMPTY_USER_EMPTY_PASSWORD, "", "" },

        };
    }

    @Test(dataProvider = "loginData")
    public void userlogincase(UserType userType, String username, String password) {
        LoginPage loginobject = new LoginPage(driver);
        loginobject.userLogin(username, password);

        switch (userType) {
            case STANDARD_USER:
                String expected_url = "https://www.saucedemo.com/inventory.html";
                String actualTitle = driver.getCurrentUrl();
                Assert.assertEquals(actualTitle, expected_url);
                break;
            case LOCKED_OUT_USER:
                String actualErrorMessage = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[3]/h3")).getText();
                String expectedErrorMessage = "Epic sadface: Sorry, this user has been locked out.";
                Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
                break;
            case PROBLEM_USER:
                String image_source = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[1]/div[1]/a/img")).getAttribute("src");
                Assert.assertEquals(image_source, "https://www.saucedemo.com/static/media/sl-404.168b1cce.jpg");
                break;
            case ERROR_USER:
                driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
                driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
                String shopping_bag_text = driver.findElement(By.cssSelector("#shopping_cart_container > a > span")).getText();
                Assert.assertEquals(shopping_bag_text, "1");
                break;
            case VISUAL_USER:
                String visual_error_user = driver.findElement(By.id("shopping_cart_container")).getAttribute("class");
                String visual_error_user_exp = "shopping_cart_container visual_failure";
                Assert.assertEquals(visual_error_user, visual_error_user_exp);
                break;
            case VALID_USER_INVALID_PASSWORD:
                String val_user_inv_pas = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[3]/h3")).getText();
                String val_user_inv_pas_exp = "Epic sadface: Username and password do not match any user in this service";
                Assert.assertEquals(val_user_inv_pas, val_user_inv_pas_exp);
                break;
            case VALID_USER_EMPTY_PASSWORD:
                String val_user_emp_pas = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[3]/h3")).getText();
                String val_user_emp_pas_exp = "Epic sadface: Password is required";
                Assert.assertEquals(val_user_emp_pas, val_user_emp_pas_exp);
                break;
            case EMPTY_USER_EMPTY_PASSWORD:
                String emp_user_emp_pas = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[3]/h3")).getText();
                String emp_user_emp_pas_exp = "Epic sadface: Username is required";
                Assert.assertEquals(emp_user_emp_pas, emp_user_emp_pas_exp);
                break;
            default:
                break;
        }
    }



}
