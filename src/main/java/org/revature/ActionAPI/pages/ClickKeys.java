package org.revature.ActionAPI.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ClickKeys extends BasePage {

    @FindBy(css="p#characterIndicator")
    WebElement currentKey;

    public ClickKeys(WebDriver driver) {
        super(driver);
    }

    @Override
    public void getPage() {
        driver.get("http://127.0.0.1:5500/click-keys.html");
    }

    @Override
    public void correctInputs() {
        boolean handledAlert = false;
        Alert alert = null;
        while (!handledAlert) {

            performInput(currentKey.getText());
            try
            {
                alert = driver.switchTo().alert();
                handledAlert = true;
            } // try
            catch (NoAlertPresentException Ex)
            {
                alert = null;
                handledAlert = false;
            }
        }

        if (alert != null) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            alert.accept();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void performInput(String input) {
        Actions builder = new Actions(driver);
        switch(input) {
            case "left arrow":
                builder.keyDown(Keys.ARROW_LEFT).keyUp(Keys.ARROW_LEFT);
                break;
            case "right arrow":
                builder.keyDown(Keys.ARROW_RIGHT).keyUp(Keys.ARROW_RIGHT);
                break;
            case "down arrow":
                builder.keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN);
                break;
            case "up arrow":
                builder.keyDown(Keys.ARROW_UP).keyUp(Keys.ARROW_UP);
                break;
            default:
                builder.keyDown(input).keyUp(input);
                break;
        }
        Action toPerform = builder.build();
        toPerform.perform();
    }
}
