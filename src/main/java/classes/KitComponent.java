package classes;

import org.jsoup.nodes.Element;
import classes.utils.data_processing_utils;


public class KitComponent extends Item{
    Item item;
    final static String kitComponentsXpath="//ul[@class='kit-component-results']//div[@class='search-result-detail result-view-rows']";
    String xpathKitComponentTitle = "//ul[@class='kit-component-results']//div[@class='search-result-detail result-view-rows']//span[@class='result-title']";
    String xpathKitComponentLink = "//ul[@class='kit-component-results']//div[@class='search-result-detail result-view-rows']//a[@class='detail-link']/@href";
    String xpathQtyRequired = "//span[@class='kit-qty']";
    String xpathKitComponentDescription = "//span[@class='result-description']" ;

    Element kitComponentTitleElement;
    Element kitComponentLinkElement;
    Element qtyRequiredElement;
    Element kitComponentDescriptionElement;

    String kitComponentTitleString;
    String kitComponentLinkString;
    String qtyRequiredString;
    String kitComponentDescriptionString;

    public KitComponent(Element webElement,int i,Item item) {
        this.item=item;
        kitComponentTitleElement=item.doc.selectXpath(xpathKitComponentTitle).get(i);
        kitComponentTitleString =kitComponentTitleElement.text();

        qtyRequiredElement=item.doc.selectXpath(xpathQtyRequired).get(i);
        qtyRequiredString =qtyRequiredElement.text();

        // kitComponentLinkElement=item.doc.selectXpath(xpathKitComponentLink).get(i);
        kitComponentLinkString = data_processing_utils.generateCellContentFor_i_number(
                xpathKitComponentLink,item.doc,i*2); //because we have 2 same links for each KitComponent

        kitComponentDescriptionElement=item.doc.selectXpath(xpathKitComponentDescription).get(i);
        kitComponentDescriptionString =kitComponentDescriptionElement.text();


    }

    public KitComponent(int i,Item item) {
        this.item=item;
        kitComponentTitleElement=item.doc.selectXpath(xpathKitComponentTitle).get(i);
        kitComponentTitleString =kitComponentTitleElement.text();

        qtyRequiredElement=item.doc.selectXpath(xpathQtyRequired).get(i);
        qtyRequiredString =qtyRequiredElement.text();

        // kitComponentLinkElement=item.doc.selectXpath(xpathKitComponentLink).get(i);
        kitComponentLinkString = data_processing_utils.generateCellContentFor_i_number(
                xpathKitComponentLink,item.doc,i*2); //because we have 2 same links for each KitComponent

        kitComponentDescriptionElement=item.doc.selectXpath(xpathKitComponentDescription).get(i);
        kitComponentDescriptionString =kitComponentDescriptionElement.text();
    /*    System.out.println(kitComponentTitleString);
        System.out.println("kitComponentLinkString = "+kitComponentLinkString);
        System.out.println(qtyRequiredString);
        System.out.println(kitComponentDescriptionString);
        System.out.println("---");*/

    }

    @Override
    public String toString() {
       String retString =
                "{" +
                /*"kitComponentTitle='" +*/ kitComponentTitleString +"; "+
                /*", kitComponentLink='" + kitComponentLink + '\'' +*/
                " Quantity Required: " + qtyRequiredString + "; "+
                /*", kitComponentDescription='" + */kitComponentDescriptionString  +
                '}'+lineSeparator;

                retString=retString.replaceAll(";\\.\\.\\.","").replaceAll("\\.\\.\\.","");

                return retString;
    }
}
