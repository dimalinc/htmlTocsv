package kingshocks;

import classes.utils.data_processing_utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Item_Kingshocks {

    static ArrayList<String> xpathStringsList = Data_processing_utils_Kingshocks.xpathStringsListInit();

    static ArrayList<Element> jsoupElementsList = new ArrayList<>();

final static String domain ="https://kingshocks.com/";
    protected File inputFile;
    Document doc;
    String urlString;     String urlStringSKU;
    String title;
    String sku;
    String shortDesc;
    String features; String featuresHTML;
    String details; String detailsHTML;
    String details2; String details2HTML;
    String details3; String details3HTML;
    String details4; String details4HTML;

    String shipping; String shippingHTML;
    String instructions_pdf_Link;
    String imgLinks;     String imgLinksURL;


    public Item_Kingshocks(File file, String domain) {
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
        urlString = domain + inputFile.getName();
        urlStringSKU =  inputFile.getName().replace(".html","");

        title = generateCellContent(xpathStringsList.get(0), doc);
        sku = generateCellContent(xpathStringsList.get(1), doc);
        shortDesc = generateCellContent(xpathStringsList.get(2), doc);
        features = generateCellContent(xpathStringsList.get(3), doc);
        featuresHTML = generateCellContentInnerHTML(xpathStringsList.get(3), doc);
        details = generateCellContent(xpathStringsList.get(4), doc);
        detailsHTML = generateCellContentInnerHTML(xpathStringsList.get(4), doc);
        details2 = generateCellContent(xpathStringsList.get(5), doc);
        details2HTML = generateCellContentInnerHTML(xpathStringsList.get(5), doc);
        details3 = generateCellContent(xpathStringsList.get(6), doc);
        details3HTML = generateCellContentInnerHTML(xpathStringsList.get(6), doc);
        details4 = generateCellContent(xpathStringsList.get(7), doc);
        details4HTML = generateCellContentInnerHTML(xpathStringsList.get(7), doc);

        shipping = generateCellContent(xpathStringsList.get(8), doc);
        shippingHTML = generateCellContentInnerHTML(xpathStringsList.get(8), doc);

        instructions_pdf_Link=(domain+generateCellContent(xpathStringsList.get(9), doc)).replace("//","/");

        imgLinks = generateCellContentForPicUrl(xpathStringsList.get(10), doc, "; ");
        imgLinksURL=imgLinks.replace(";","");

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
            sb./*append("\"").*/append(data_processing_utils.generateWebElementValue(element, xpath))/*.append("\"")*/.append(Data_processing_utils_Kingshocks.lineSeparator);
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
