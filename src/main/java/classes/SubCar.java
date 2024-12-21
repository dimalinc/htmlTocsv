package classes;

import net.minidev.json.JSONUtil;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class SubCar extends Car{
    public Car car;
    public String subCarStringValue;
    public String subCarXpath;
    public String eachSubCarXpath;


    public static final String subCars_xpath ="/following-sibling::div/ul/li[contains(@class, 'application')]/a";
    public static final String subCarsXpathBeginning="("+ subCars_xpath +")"+"[";
    public static final String subCarsXpathEnd="]";
    public String subCarEngineString;
    public ArrayList<ApplicationFootnote> subCarAppFootnote_ObjectsList;
    public ArrayList<ApplicationAttribute> subCarAppAttribute_ObjectsList;
    public ArrayList<Element> appFootnote_ElementsList;
    public ArrayList<Element> appAttribute_ElementsList;
    public String driveString;
    public LinkedHashSet<Double> subCarLiftNumbersSet;


    public SubCar(Element subCarWebElement,int i, Car car) {
        doc=car.doc;
        this.car=car;
        subCarXpath=car.carXpath+subCars_xpath;
       // ?? subCarElementsList=doc.selectXpath(subCarXpath);
        eachSubCarXpath="("+subCarXpath+")["+i+"]";

        // subCarValue =doc.selectXpath(eachSubCarXpath).text();
        subCarStringValue = subCarWebElement.text();
       // System.out.println("subCarValue = "+ subCarStringValue);

        if (doc != null) {
            appFootnote_ElementsList=doc.selectXpath(eachSubCarXpath+ApplicationFootnote.appFootnotes_Xpath);
            subCarAppFootnote_ObjectsList = new ArrayList<>();
            int ii=1; //xpath outnumbered from 1
            for (Element appFootnoteWebElement: appFootnote_ElementsList) {
                subCarAppFootnote_ObjectsList.add(new ApplicationFootnote(appFootnoteWebElement,ii++,this));
            }

           // System.out.println(eachSubCarXpath+ApplicationAttribute.appAtts_Xpath);
            appAttribute_ElementsList = doc.selectXpath(eachSubCarXpath+ApplicationAttribute.appAtts_Xpath);
            subCarAppAttribute_ObjectsList =new ArrayList<>();
            ii=1;
            for (Element appAttrWebElement:appAttribute_ElementsList) {
                subCarAppAttribute_ObjectsList.add(new ApplicationAttribute(appAttrWebElement,ii++,this));
            }
        }

        int driveIndexFinish= subCarStringValue.indexOf(" Wheel Drive");
        int driveIndexStart= 0;

        if (driveIndexFinish>0) {
            driveString = subCarStringValue.substring(0, driveIndexFinish).
                    split(" ")[subCarStringValue.substring(0, driveIndexFinish).split(" ").length - 1];
        }

        // TODO:  находить первую точку с запятой, и отрывать по первому пробелу ДООО первой точки с запятой
        String firstEngineWord = "";
        if (subCarStringValue.contains(";")) {
            String subCarPlusFirstEngineWord = subCarStringValue.substring(0, subCarStringValue.indexOf(";"));

            // System.out.println("subCarPlusFirstEngineWord ="+ subCarPlusFirstEngineWord);
            firstEngineWord = subCarPlusFirstEngineWord.substring(subCarPlusFirstEngineWord.lastIndexOf(" "));
        }
      //  System.out.println("firstEngineWord = "+ firstEngineWord);
        subCarEngineString = (firstEngineWord+subCarStringValue.substring
                (subCarStringValue.indexOf(" ",subCarStringValue.indexOf(";")))).trim();
       // System.out.println(subCarEngineString);
    }

    public SubCar(int i, Car car) {
        doc=car.doc;
        subCarXpath=car.carXpath+subCars_xpath;
     // ?? subCarWebElementsList =doc.selectXpath(subCarXpath);
        eachSubCarXpath="("+subCarXpath+")["+i+"]";

         subCarStringValue =doc.selectXpath(eachSubCarXpath).text();

        if (doc != null) {
            appFootnote_ElementsList=doc.selectXpath(eachSubCarXpath+ApplicationFootnote.appFootnotes_Xpath);
            subCarAppFootnote_ObjectsList = new ArrayList<>();
            appAttribute_ElementsList = doc.selectXpath(eachSubCarXpath+ApplicationAttribute.appAtts_Xpath);
            subCarAppAttribute_ObjectsList =new ArrayList<>();
            int ii=1; //xpath outnumbered from 1
            for (Element element: appFootnote_ElementsList) {
                subCarAppFootnote_ObjectsList.add(new ApplicationFootnote(ii++,this));
            }
            ii=1;
            for (Element element:appAttribute_ElementsList) {
                subCarAppAttribute_ObjectsList.add(new ApplicationAttribute(ii++,this));
            }
        }

        int driveIndexFinish= subCarStringValue.indexOf(" Wheel Drive");
        int driveIndexStart= 0;

        if (driveIndexFinish>0) {
            driveString = subCarStringValue.substring(0, driveIndexFinish).
                    split(" ")[subCarStringValue.substring(0, driveIndexFinish).split(" ").length - 1];
        }
    }

    public SubCar() { }

    @Override
    public String toString() {
        return /*"S/*ubCar{" +
                "value='" + */
        subCarStringValue
                /*+ "\r\n" +
                ", appFootnote_objList=" + subCarAppFootnote_ObjectsList + "\r\n" +
                ", appAttribute_ObjectsList=" + subCarAppAttribute_ObjectsList + "\r\n" +
                '}'*/;
    }
}
