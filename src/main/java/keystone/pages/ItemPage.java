package keystone.pages;

import keystone.elements.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ItemPage {

   /* Label title = new Label();
    Span shortDescription = new Span();
    Image image = new Image();
    Span retailPrice = new Span();
    Span keystonePartNumber = new Span();
    Span manufacturerPartNumber = new Span();
    Span supplier = new Span();
    Div productInfo = new Div();
    Div thisFitsTab = new Div();

    // find all clickable fitment elements
    Div fitmentClickablePlusIcon = new Div();
    Span fitmentCarTitle = new Span();
    Li  application = new Li();
    Li  applicationAttribute = new Li();*/

    private Div divThisFits = new Div(By.xpath("//div[@id='ThisFits']"), "divThisFits");

    private Span plusFitmentButton = new Span(By.xpath("//span[@class='expand-collapse-icon fa fa-plus-square']"), "plusFitmentButton");
    public List<WebElement> getPlusFitmentButtonList() {
        return plusFitmentButtonList;
    }
    public List<WebElement> plusFitmentButtonListInit() { return plusFitmentButton.findElements(); }
    private List<WebElement> plusFitmentButtonList=plusFitmentButtonListInit();

    private Span spanCarApplicationTitle = new Span(By.xpath("//span[@class='title']"), "spanCarApplicationTitle");
    private List<WebElement> spanCarApplicationTitleList=spanCarApplicationTitleListInit();
    public List<WebElement> spanCarApplicationTitleListInit() { return listWebElementsInit(spanCarApplicationTitle.getLocator()); }
    public List<WebElement> getSpanCarApplicationTitleList() {
        return spanCarApplicationTitleList;
    }

    private Li liClassApplication = new Li(By.xpath("//li[@class='application']"),"liClassApplication");
    private Li liClassApplicationAltRow = new Li(By.xpath("//li[@class='application alt-row']"),"liClassApplicationAltRow");
    private List<WebElement> liClassApplicationElementsList=listWebElementsInit(liClassApplication.getLocator());
    private List<WebElement> liClassApplicationAltRowElementsList=listWebElementsInit(liClassApplicationAltRow.getLocator());

    public Span getSpanCarApplicationTitle() {
        return spanCarApplicationTitle;
    }
    public List<WebElement> listWebElementsInit(By xpath) {
        return findElementsByXpath(xpath);
    }
    public  List<WebElement> findElementsByXpath(By xpath) {
        return keystone.common.Browser.getInstanceOfWebDriver().getDriver().
                findElements(xpath);
    }
    public Li getLiClassApplication() {
        return liClassApplication;
    }
    public Li getLiClassApplicationAltRow() {
        return liClassApplicationAltRow;
    }
    public List<WebElement> getLiClassApplicationElementsList() {
        return liClassApplicationElementsList;
    }
    public List<WebElement> getLiClassApplicationAltRowElementsList() {
        return liClassApplicationAltRowElementsList;
    }
    public Div getDivThisFits() {
        return divThisFits;
    }
    public Span getPlusFitmentButton() {
        return plusFitmentButton;
    }
    public void divThisFitsClick() {
        divThisFits.click();
    }
    public void plusFitmentButtonClick() {
        plusFitmentButton.click();
    }
    public void plusFitmentButtonScrollIntoView() {

    }
}