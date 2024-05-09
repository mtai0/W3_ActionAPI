package org.revature.ActionAPI.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DragAndDrop extends BasePage {

    @FindBy(css="p#toDrag")
    WebElement textToDrag;

    @FindBy(xpath="//div[@ondrop='drop(event)']")
    WebElement dragDestination;

    public DragAndDrop(WebDriver driver) {
        super(driver);
    }

    @Override
    public void getPage() {
        driver.get("http://127.0.0.1:5500/drag-and-drop.html");
    }

    @Override
    public void correctInputs() {
        Actions builder = new Actions(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.elementToBeClickable(textToDrag));
        builder.moveToElement(textToDrag).clickAndHold();

        wait.until(ExpectedConditions.visibilityOf(dragDestination));
        builder.moveToElement(dragDestination).release();

        builder.build().perform();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
