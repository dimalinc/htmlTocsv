package keystone.elements;

/*import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;*/
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseElement {

/*    static final Logger rootLogger = LogManager.getRootLogger();
    static final Logger test1Logger = LogManager.getLogger(BaseElement.class);*/

    protected By locator;

    public String getElementName() {
        return elementName;
    }

    protected String elementName;

    public BaseElement(By locator, String elementName) {
     //   test1Logger.info("created " + elementName);
        this.locator = locator;
        this.elementName = elementName;
    }

    public String getValue() {
        return keystone.common.Browser.getInstanceOfWebDriver().getDriver().findElement(locator).getAttribute("value");
    }

    public By getLocator() {
        return locator;
    }

    protected WebElement findElement() {
        return keystone.common.Browser.getInstanceOfWebDriver().getDriver().findElement(locator);
    }
    public List<WebElement> findElements() {
        return keystone.common.Browser.getInstanceOfWebDriver().getDriver().findElements(locator);
    }


    public void click() {
     //   test1Logger.info("clicked " + elementName);
        findElement().click();
    }


    public boolean isDisplayed() {
      /*  try {
            return findElement().isDisplayed();
        } catch (Exception e) {e.printStackTrace() ; }
        return false;*/

        return findElement().isDisplayed();

    }
    public boolean isClickable() {
        return (findElement().isEnabled())&&(findElement().isDisplayed());
    }

    public String getText() {
        return findElement().getText();
    }

    public void enterText(String text) {
        findElement().sendKeys(text);
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public void setLocator(By locator) {
        this.locator = locator;
    }


}
