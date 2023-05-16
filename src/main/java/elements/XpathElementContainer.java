package elements;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class XpathElementContainer {

    protected String xpath;
    protected Document doc;
    ArrayList<Element> jsoupElementsList=new ArrayList<>();
    protected String webElementValue=null;
    protected String csvCellValue=null;

    public XpathElementContainer(String xpath, Document doc) {
        this.xpath = xpath;
        this.doc=doc;
        csvValueInit();
    }

    public String getWebElementValue() {
        return webElementValue;
    }

    protected String generateWebElementValue(Element element) {
        if (xpath.contains("href"))
           return element.attr("href");
        else if (xpath.contains("src"))
            return element.attr("src");
        else return element.text();
    }

    protected void csvValueInit() {
        // встроить обработчик /@scr @href
        if ( (xpath.contains("href")) || (xpath.contains("src")) )
            jsoupElementsList=doc.selectXpath(xpath.substring(0,xpath.lastIndexOf("/")));
        else  jsoupElementsList=doc.selectXpath(xpath);
        StringBuilder sb= new StringBuilder();
        for (Element element:jsoupElementsList)
            sb.append(generateWebElementValue(element)).append(System.getProperty("line.separator")).toString();
        csvCellValue= sb.toString()/*.substring(0,sb.toString().lastIndexOf("line.separator"))*/;
    }


    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public String getCsvCellValue() {
        return csvCellValue;
    }

    public void setCsvCellValue(String csvCellValue) {
        this.csvCellValue = csvCellValue;
    }
}
