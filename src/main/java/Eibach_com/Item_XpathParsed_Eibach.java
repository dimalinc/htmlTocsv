package Eibach_com;

import classes.utils.data_processing_utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Item_XpathParsed_Eibach {

    Item_DataProcssed_Eibach item_dataProcssed_eibach;

    static ArrayList<String> xpathStringsList = Data_processing_utils_Eibach.xpathStringsListInit();

    static ArrayList<Element> jsoupElementsList = new ArrayList<>();

    protected File inputFile;
    Document doc;
    String urlString;     String urlStringSKU;
    String breadcrumbs;
    String title;
    String carInfo;   String carDetails;
    String sku;
    String shortDesc;
    String liftAndFitmentAttribs;
    String price;
    String longDesc; String longDescHTML;
    String warranty;
    String pdfManualLinks;
    String imgLinks;     String imgLinksURL;
    /*String specs; String specsHTML;
    String application; String applicationHTML;*/
    String recommendedItemLinks; String recommendedItemNames;

    String application_list_href;     String application_list_href_HTML;
    String collapsable_application_list_href;     String collapsable_application_list_href_HTML;


    public Item_XpathParsed_Eibach(File file, String domain) {
        this.inputFile = file;
        try {
            doc = Jsoup.parse(inputFile, "UTF-8", domain);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (doc != null) {
            xpath_fieldsInit();
        }

        item_dataProcssed_eibach= new Item_DataProcssed_Eibach(this);

    }

    public void xpath_fieldsInit() {
        urlString = "https://eibach.com/product/" + inputFile.getName();
        urlStringSKU =  inputFile.getName().replace(".html","");

        breadcrumbs = generateCellContent(xpathStringsList.get(0), doc);
        title = generateCellContent(xpathStringsList.get(1), doc);
        carInfo = generateCellContent(xpathStringsList.get(2), doc);
        carDetails = generateCellContent(xpathStringsList.get(3), doc);
        sku = generateCellContent(xpathStringsList.get(4), doc);
        shortDesc = generateCellContent(xpathStringsList.get(5), doc);
        liftAndFitmentAttribs = generateCellContent(xpathStringsList.get(6), doc);
        price = generateCellContent(xpathStringsList.get(7), doc);
        longDesc = generateCellContent(xpathStringsList.get(8), doc);
        longDescHTML = generateCellContentInnerHTML(xpathStringsList.get(8), doc);
        warranty = generateCellContent(xpathStringsList.get(9), doc);
        pdfManualLinks = "https://eibach.com/"+generateCellContent(xpathStringsList.get(10), doc);
        imgLinks = generateCellContentForPicUrl(xpathStringsList.get(11), doc, "; ");
        imgLinksURL="https://eibach.com"+imgLinks.replace(";","");
        recommendedItemLinks = generateCellContent(xpathStringsList.get(12), doc);
        recommendedItemNames = generateCellContent(xpathStringsList.get(13), doc);


        /*application = generateCellContent(xpathStringsList.get(2), doc);
        applicationHTML = generateCellContentInnerHTML(xpathStringsList.get(2), doc);

        specs = generateCellContent(xpathStringsList.get(3), doc);
        specsHTML = generateCellContentInnerHTML(xpathStringsList.get(3), doc);*/

        /*imgLinks = generateCellContentForPicUrl(xpathStringsList.get(4), doc, "; ");
        imgLinksURL="https://eibach.com/"+imgLinks.replace(";","");
        sku = generateCellContent(xpathStringsList.get(5), doc);*/

       /* application_list_href=generateCellContent(xpathStringsList.get(6), doc);
        collapsable_application_list_href=generateCellContent(xpathStringsList.get(7), doc);*/



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
            sb./*append("\"").*/append(data_processing_utils.generateWebElementValue(element, xpath))/*.append("\"")*/.append(Data_processing_utils_Eibach.lineSeparator);
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
