package classes;

import org.jsoup.nodes.Element;

public class ApplicationFootnote extends SubCar{

    public String appFootnoteS_Xpath;
    public String appFootnote_Xpath;
    public static final String appFootnotes_Xpath ="/ancestor::li[1]/ul/li[@class='application-footnote']";
    /*static final String appAttXpathBeginning="("+ appAtts_Xpath +")"+"[";
    static final String appAttXpathEnd="]";*/
    public String appFootnote_Value;

    public ApplicationFootnote(Element webElement,int i, SubCar subCar) {
        doc = subCar.doc;
        appFootnoteS_Xpath = subCar.eachSubCarXpath+ appFootnotes_Xpath;
        appFootnote_Xpath="("+appFootnoteS_Xpath+")["+i+"]";
       // appFootnote_Value = doc.selectXpath(appFootnote_Xpath).text();
        appFootnote_Value = webElement.text();
       // System.out.println("appFootnote_Value = "+appFootnote_Value);
    }

    public ApplicationFootnote(int i, SubCar subCar) {
        doc = subCar.doc;
        appFootnoteS_Xpath = subCar.eachSubCarXpath+ appFootnotes_Xpath;
        appFootnote_Xpath="("+appFootnoteS_Xpath+")["+i+"]";
        appFootnote_Value = doc.selectXpath(appFootnote_Xpath).text();
    }

    public ApplicationFootnote() {
    }

    @Override
    public String toString() {
        return /*"ApplicationFootnote{" +
                "appFootnote_Value='" +*/ appFootnote_Value
                /*+ '}'+ "\r\n"*/ ;
    }
}
