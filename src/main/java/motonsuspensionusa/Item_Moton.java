package motonsuspensionusa;

import classes.utils.data_processing_utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Item_Moton {

    static ArrayList<String> xpathStringsList = Moton_data_processing_utils.xpathStringsListInit();

    static ArrayList<Element> jsoupElementsList = new ArrayList<>();

    protected File inputFile;
    Document doc;
    String urlString;String breadcrumbs;String itemTitle;
    String manufacturer;String itemType;String sku;
    String fullDesc;String descGeneralAbstract1;String descGeneralAbstract2;
    String fullTextDescription;String features;
    String installationInfo;
    String productSpecs;
    String vehicleFitment;
    String imgLinks;
    String price;

    public Item_Moton(File file, String domain) {
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
        urlString = "https://motonsuspensionusa.com/products/" + inputFile.getName();
        breadcrumbs = generateCellContent(xpathStringsList.get(0), doc);
        itemTitle = generateCellContent(xpathStringsList.get(1), doc);
        manufacturer = generateCellContent(xpathStringsList.get(2), doc);
        itemType = generateCellContent(xpathStringsList.get(3), doc);
        sku = generateCellContent(xpathStringsList.get(4), doc);
        fullDesc = generateCellContent(xpathStringsList.get(5), doc);
        descGeneralAbstract1= generateCellContent(xpathStringsList.get(6), doc);
        descGeneralAbstract2= generateCellContent(xpathStringsList.get(7), doc);
        fullTextDescription= generateCellContent(xpathStringsList.get(8), doc);
        features= generateCellContent(xpathStringsList.get(9), doc);
        installationInfo= generateCellContent(xpathStringsList.get(10), doc);
        productSpecs= generateCellContent(xpathStringsList.get(11), doc);
        vehicleFitment= generateCellContent(xpathStringsList.get(12), doc);

        imgLinks = generateCellContentForPicUrl(xpathStringsList.get(13), doc, ", ");
        price = generateCellContent(xpathStringsList.get(14), doc);
    }

    public static String generateCellContent(String xpath, Document doc) {
        // jsoupElementsList=new ArrayList<>();
        if ((xpath.endsWith("href")) || (xpath.endsWith("src")))
            jsoupElementsList = doc.selectXpath(xpath.substring(0, xpath.lastIndexOf("/")));
        else jsoupElementsList = doc.selectXpath(xpath);

        StringBuilder sb = new StringBuilder();/*sb.append("=");*/
        for (Element element : jsoupElementsList)
        //csv
        {
            sb./*append("\"").*/append(data_processing_utils.generateWebElementValue(element, xpath))/*.append("\"")*/.append(Moton_data_processing_utils.lineSeparator);
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

        String returnString = jsoupElementsList.get(i).text();
        if ((xpath.endsWith("href"))) returnString = jsoupElementsList.get(i).attr("href");
        if ((xpath.endsWith("src"))) returnString = jsoupElementsList.get(i).attr("src");

        return returnString;
    }

    public static String generateCellContentForPicUrl(String xpath, Document doc, String lineSep) {
        // jsoupElementsList=new ArrayList<>();
        if ((xpath.endsWith("href")) || (xpath.endsWith("src")) || (xpath.endsWith("data-srcset")) ) {
            jsoupElementsList = doc.selectXpath(xpath.substring(0, xpath.lastIndexOf("/")));
       // System.out.println(jsoupElementsList);
        }
        else jsoupElementsList = doc.selectXpath(xpath);

        StringBuilder sb = new StringBuilder();/*sb.append("=");*/
        for (Element element : jsoupElementsList)
        //csv
        {
            sb./*append("\"").*/append(data_processing_utils.generateWebElementValue(element, xpath))/*.append("\"")*/.append(lineSep);
        }
        if (sb.lastIndexOf(lineSep) > 0)
            sb.delete(sb.lastIndexOf(lineSep), sb.lastIndexOf(lineSep.substring(lineSep.length())));

       //  System.out.println(sb.toString());

        return sb.toString();
    }


}
