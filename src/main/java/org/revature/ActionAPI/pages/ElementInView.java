package org.revature.ActionAPI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

public class ElementInView extends BasePage{

    public ElementInView(WebDriver driver) {
        super(driver);
    }

    @Override
    public void getPage() {
        driver.get("http://127.0.0.1:5500/element-in-view.html");
    }

    @Override
    public void correctInputs() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        /*List<WebElement> pElements = driver.findElements(By.xpath("//p"));
        for (WebElement element : pElements) {
            new Actions(driver).scrollToElement(element).perform();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }*/

        WebElement foundElement = null;
        do {
            scrollIncrement(1f);
            foundElement = checkForElement();
        }
        while (foundElement == null);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(foundElement));
        new Actions(driver).scrollToElement(foundElement).perform();
        scrollIncrement(0.5f);  //put it in center of page

        new Actions(driver).doubleClick(foundElement).click(foundElement).perform();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void scrollIncrement(float percent) {
        int scrollAmount = Float.valueOf(driver.manage().window().getSize().getHeight() * percent).intValue();
        new Actions(driver).scrollByAmount(0, scrollAmount).perform();
    }


    private WebElement checkForElement() {
        List<WebElement> elementList = driver.findElements(By.xpath("//p"));
        for (WebElement element : elementList) {
            if (element.getAttribute("hidden") == null && !element.getText().isEmpty())
                return element;
        }
        return null;
    }
}
