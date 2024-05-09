package org.revature.ActionAPI;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.revature.ActionAPI.pages.ClickKeys;
import org.revature.ActionAPI.pages.DragAndDrop;
import org.revature.ActionAPI.pages.ElementInView;

public class Main {

    private static WebDriver driver;

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        ClickKeys p1 = PageFactory.initElements(driver, ClickKeys.class);
        p1.correctInputs();

        DragAndDrop p2 = PageFactory.initElements(driver, DragAndDrop.class);
        p2.correctInputs();

        ElementInView p3 = PageFactory.initElements(driver, ElementInView.class);
        p3.correctInputs();

        driver.quit();
    }
}
