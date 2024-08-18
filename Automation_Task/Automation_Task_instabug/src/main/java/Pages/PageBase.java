package Pages;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PageBase {

     protected WebDriver driver;

    //Create Constructor
    public PageBase(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }
}