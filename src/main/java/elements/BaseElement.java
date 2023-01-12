/*
package elements;

import browser.BrowserFactory;
import elements.webelements.ElementStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public abstract class BaseElement {

   */
/* static final Logger rootLogger = LogManager.getRootLogger();
    static final Logger test1Logger = LogManager.getLogger(BaseElement.class);*//*

    protected By locator;
    protected String elementName;
    private ElementStatus elementStatus;

    public String getElementName() {
        return elementName;
    }


    public BaseElement(By locator, String elementName) {
        this.locator = locator;
        this.elementName = elementName;
        test1Logger.info("created " + elementName);
    }

    public String getValue() {
        return BrowserFactory.getDriver().findElement(locator).getAttribute("value");
    }

    public By getLocator() {
        return locator;
    }

    protected WebElement findElement() {
        return BrowserFactory.getDriver().findElement(locator);
    }

    protected List<WebElement> findElements() {
        return BrowserFactory.getDriver().findElements(locator);
    }

    public void click() {
        test1Logger.info("clicked " + elementName);
        findElement().click();
    }

    public boolean isDisplayed() {
        return findElement().isDisplayed();
    }

    public boolean isPresent() {
        return (findElements().size() > 0);
    }

    public boolean isSelected() {
        return findElement().isSelected();
    }

    public String getText() {
        test1Logger.info("get text element  ==>" + this.elementName+"="+findElement().getText());
        return findElement().getText();
    }

    public void sendKeys(CharSequence text) {
        findElement().sendKeys(text);
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public void setLocator(By locator) {
        this.locator = locator;
    }

    public void moveToClick() {
        test1Logger.info("Move to  element and Click  ==>" + this.elementName);
        new Actions(BrowserFactory.getDriver()).moveToElement(findElement()).click().perform();
    }
    public void moveToElement() {
        test1Logger.info("Move to  element ==>" + this.elementName);
        new Actions(BrowserFactory.getDriver()).moveToElement(findElement()).perform();
    }

    // https://stackoverflow.com/questions/14156656/how-to-verify-element-present-or-visible-in-selenium-2-selenium-webdriver
    public ElementStatus isElementVisible(WebDriver driver, By by, ElementStatus getStatus){
        try{
            if(getStatus.equals(ElementStatus.ENABLED)){
                if(driver.findElement(by).isEnabled())
                    return ElementStatus.ENABLED;
                return ElementStatus.NOTENABLED;
            }
            if(getStatus.equals(ElementStatus.VISIBLE)){
                if(driver.findElement(by).isDisplayed())
                    return ElementStatus.VISIBLE;
                return ElementStatus.NOTVISIBLE;
            }
            return ElementStatus.PRESENT;
        }catch(org.openqa.selenium.NoSuchElementException nse){
            return ElementStatus.NOTPRESENT;
        }
    }

    public String elementVisibleCheck (WebDriver driver) {
        return this.getElementName() + " " + isElementVisible(driver, this.getLocator(), ElementStatus.VISIBLE).toString();
    }


}

*/
