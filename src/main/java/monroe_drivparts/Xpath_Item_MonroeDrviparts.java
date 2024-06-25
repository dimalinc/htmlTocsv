package monroe_drivparts;

import classes.utils.data_processing_utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Xpath_Item_MonroeDrviparts {


    static ArrayList<String> xpathStringsList = Data_processing_utils_Monroe.itemFields_XpathStringsList;

    static ArrayList<Element> jsoupElementsList = new ArrayList<>();

    protected File inputFile;
    final static String domain = "https://www.drivparts.com/";
    Document doc;

    final static String xpath_Spec = "//div[@class='driv-part-detail-page-specification']";
    final static String xpath_Spec_Label = "/div[@class='driv-part-detail-page-specification-label']";
    final static String xpath_Spec_Value = "/div[@class='driv-part-detail-page-specification-value']";
    final static String final_Spec_xpath = xpath_Spec+xpath_Spec_Label;
    final static String final_Label_xpath = xpath_Spec+xpath_Spec_Value;

    final static String xpath_appAtt = "//tr[@class='product']";
    final static String xpath_appAtt_field = "//tr[@class='product']/td";
    final static String xpath_appAtt_coloumTitleElement =
            "(//table[@class='tab-table desktop']/thead)[1]/tr/th";

    ArrayList<Element> xpath_Spec_jsoupElementsList=new ArrayList<>();
    ArrayList<Element> xpath_appAttr_jsoupElementsList=new ArrayList<>();

    String urlString;     String urlStringSKU;
    String name;
    String specifications; String specificationsHTML;
    String applications; String applicationsHTML;
    String otherMedia;
    String sku;
    String shortDesc;
    String longDesc; String longDescHTML;
    String imgLinks;     String imgLinksURL;


    public Xpath_Item_MonroeDrviparts(File file, String domain) {
        this.inputFile = file;
        try {
            doc = Jsoup.parse(inputFile, "UTF-8", domain);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (doc != null) {
            fieldsInit();
        }

    }

    public void fieldsInit() {
        urlString = domain + inputFile.getName().replace("--","?");
        urlStringSKU =  inputFile.getName().substring(inputFile.getName().lastIndexOf("part_number=")+"part_number=".length());

        sku = generateCellContent(xpathStringsList.get(0), doc);
        name = generateCellContent(xpathStringsList.get(1), doc);
        imgLinks = generateCellContentForPicUrl(xpathStringsList.get(2), doc, "; ");
        imgLinksURL=domain.substring(0,domain.length()-1)+imgLinks.replace(";","");
        specifications=generateCellContent(xpathStringsList.get(3), doc);
        specificationsHTML=generateCellContentInnerHTML(xpathStringsList.get(3), doc);
        applications=generateCellContent(xpathStringsList.get(4), doc);
        applicationsHTML=generateCellContentInnerHTML(xpathStringsList.get(4), doc);
        otherMedia=generateCellContentForPicUrl(xpathStringsList.get(5), doc, "; ");
        shortDesc = generateCellContent(xpathStringsList.get(6), doc);
        longDesc = generateCellContent(xpathStringsList.get(7), doc);
        longDescHTML = generateCellContentInnerHTML(xpathStringsList.get(7), doc);

        /*application = generateCellContent(xpathStringsList.get(2), doc);
        applicationHTML = generateCellContentInnerHTML(xpathStringsList.get(2), doc);

        specs = generateCellContent(xpathStringsList.get(3), doc);
        specsHTML = generateCellContentInnerHTML(xpathStringsList.get(3), doc);*/

        /*imgLinks = generateCellContentForPicUrl(xpathStringsList.get(4), doc, "; ");
        imgLinksURL="https://eibach.com/"+imgLinks.replace(";","");
        sku = generateCellContent(xpathStringsList.get(5), doc);*/

       /* application_list_href=generateCellContent(xpathStringsList.get(6), doc);
        collapsable_application_list_href=generateCellContent(xpathStringsList.get(7), doc);*/

        xpath_Spec_jsoupElementsList=doc.selectXpath(xpath_Spec);
        xpath_appAttr_jsoupElementsList=doc.selectXpath(xpath_Spec);



    }

    public static String generateCellContent(String xpath, Document doc) {
        // jsoupElementsList=new ArrayList<>();
        if ( (xpath.endsWith("href")) || (xpath.endsWith("src")) || (xpath.endsWith("data-thumb"))  )
            jsoupElementsList = doc.selectXpath(xpath.substring(0, xpath.lastIndexOf("/")));
        else jsoupElementsList = doc.selectXpath(xpath);

        StringBuilder sb = new StringBuilder();/*sb.append("=");*/
        for (Element element : jsoupElementsList)
        //csv
        {
            sb./*append("\"").*/append(data_processing_utils.generateWebElementValue(element, xpath))/*.append("\"")*/.append(Data_processing_utils_Monroe.lineSeparator);
        }
       /* if (sb.lastIndexOf(lineSeparator)>0)
            sb.delete(sb.lastIndexOf(lineSeparator),sb.lastIndexOf(lineSeparator.substring(lineSeparator.length())));*/
        return sb.toString();
    }

    public static String generateCellContentFor_i_number(String xpath, Document doc, int i) {
        // jsoupElementsList=new ArrayList<>();
        if ((xpath.endsWith("href")) || (xpath.endsWith("src")))
            jsoupElementsList = doc.selectXpath(xpath.substring(0, xpath.lastIndexOf("/")));
        else jsoupElementsList = doc.selectXpath(xpath);

        String returnString=null;
        if (jsoupElementsList.size()>i)
        {
            returnString = jsoupElementsList.get(i).text();
            if ((xpath.endsWith("href"))) returnString = jsoupElementsList.get(i).attr("href");
            if ((xpath.endsWith("src"))) returnString = jsoupElementsList.get(i).attr("src");
        }

        return returnString;
    }

    public static String generateCellContentForPicUrl(String xpath, Document doc, String lineSep) {
        // jsoupElementsList=new ArrayList<>();
        if ((xpath.endsWith("href")) || (xpath.endsWith("src")))
            jsoupElementsList = doc.selectXpath(xpath.substring(0, xpath.lastIndexOf("/")));
        else jsoupElementsList = doc.selectXpath(xpath);

        StringBuilder sb = new StringBuilder();/*sb.append("=");*/
        for (Element element : jsoupElementsList)
        //csv
        {
            sb./*append("\"").*/append(data_processing_utils.generateWebElementValue(element, xpath))/*.append("\"")*/.append(lineSep);
        }
        if (sb.lastIndexOf(lineSep) > 0)
            sb.delete(sb.lastIndexOf(lineSep), sb.lastIndexOf(lineSep.substring(lineSep.length())));

        // System.out.println(sb.toString());

        return sb.toString();
    }

    public static String generateCellContentInnerHTML(String xpath, Document doc) {
        // jsoupElementsList=new ArrayList<>();
        if ((xpath.endsWith("href")) || (xpath.endsWith("src")))
            jsoupElementsList = doc.selectXpath(xpath/*.substring(0,xpath.lastIndexOf("/"))*/);
        else jsoupElementsList = doc.selectXpath(xpath);

        StringBuilder sb = new StringBuilder();/*sb.append("=");*/
        for (Element element : jsoupElementsList) {
            sb./*append("\"").*/append(data_processing_utils.generateWebElementInnerHTML(element, xpath))/*.append("\"")*/.append(System.lineSeparator());
        }
       /* if (sb.lastIndexOf(lineSeparator)>0)
            sb.delete(sb.lastIndexOf(lineSeparator),sb.lastIndexOf(lineSeparator.substring(lineSeparator.length())));*/
        return sb.toString();
    }

    public static String generateCellContentOuterHTML(String xpath, Document doc) {
        // jsoupElementsList=new ArrayList<>();
        if ((xpath.endsWith("href")) || (xpath.endsWith("src")))
            jsoupElementsList = doc.selectXpath(xpath/*.substring(0,xpath.lastIndexOf("/"))*/);
        else jsoupElementsList = doc.selectXpath(xpath);

        StringBuilder sb = new StringBuilder();/*sb.append("=");*/
        for (Element element : jsoupElementsList) {
            sb./*append("\"").*/append(data_processing_utils.generateWebElementOuterHTML(element, xpath))/*.append("\"")*/.append(System.lineSeparator());
        }
       /* if (sb.lastIndexOf(lineSeparator)>0)
            sb.delete(sb.lastIndexOf(lineSeparator),sb.lastIndexOf(lineSeparator.substring(lineSeparator.length())));*/
        return sb.toString();
    }


}
