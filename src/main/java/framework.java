import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import us.codecraft.xsoup.Xsoup;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class framework {

    static ArrayList<String> xpathStringsList = new ArrayList<>();
    static String[] elementsStringArrayOneRow = new String[11];
    static StringBuilder cellContents = new StringBuilder();
    static File input=null;
    static int n = 0;
    private static ArrayList<String> xpathStringsListinit() {
        xpathStringsList.add("");
        xpathStringsList.add("");
        xpathStringsList.add("");
        xpathStringsList.add("");
        xpathStringsList.add("");
        xpathStringsList.add("");
        xpathStringsList.add("");
        xpathStringsList.add("");
        xpathStringsList.add("");
        xpathStringsList.add("");
        xpathStringsList.add("");
        return xpathStringsList;
    }
    static String filesFolder = "D:\\Wheelersoffroad_BACKUP\\savedHtml_Replaced" + "\\";
    static String domain = "https://wheelersoffroad.com/";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\writeAll_WHEELERSOFFROAD.csv";
    static CSVWriter csvWriter;
    static Document doc = null;
    static ArrayList<ArrayList<Element>> listOfListsOfElements;

    public static List<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }

    private static CSVWriter csvWriterInit() {
        CSVWriter csvWriter = null;
        try {
            csvWriter = new CSVWriter(new FileWriter(writeAllCSV_fileName/*,true*/));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvWriter;
    }

    private static Document docInit(File input) {
        try {
            long jsoupParseStart = System.currentTimeMillis();
            doc = Jsoup.parse(input, "UTF-8", domain);
            System.out.println("Jsoup.parsed in " + (System.currentTimeMillis() - jsoupParseStart) + " mili_seconds");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static void writeAll(String[] args) throws IOException {

        List<String[]> entries = new ArrayList<>();
        entries.addAll(Collections.singleton(args));

        try (var fos = new FileOutputStream(writeAllCSV_fileName);
             var osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             var writer = new CSVWriter(osw)) {
            writer.writeAll(entries);
        }
    }

    public static void main(String[] args) {

        xpathStringsList = xpathStringsListinit();

        long start = System.currentTimeMillis();

        // reading files from folder into Set<String>
        List<String> filesStringsListInDir = listFilesUsingJavaIO(filesFolder);
        /*System.out.println("filesStringsListInDir " + filesStringsListInDir.size());
        System.out.println("--------");*/

        ArrayList<String[]> arrayListOfAllStringsForCSV = new ArrayList<>();
        csvWriter=csvWriterInit();

        for (String fileString : filesStringsListInDir) {

            n = n + 1;

            input = new File(filesFolder + fileString);
            System.out.println(n + "___" + fileString);
            doc=docInit(input);

            listOfListsOfElements = new ArrayList<>();
            // TODO: добавить многопоточность
            for(String xpathString:xpathStringsList) {
                ArrayList<Element> jsoupElementsList = Xsoup.compile(xpathString).evaluate(doc).getElements();
                listOfListsOfElements.add(jsoupElementsList);
            }

           /* ArrayList<Element> jsoupElementsList9 = Xsoup.compile("//h1").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList9.size(); i++) {
                //  System.out.println("------------");
                //  System.out.println(jsoupElementsList9.get(i).text());
                //  System.out.println("------------");
            }
            listOfListsOfElements.add(jsoupElementsList9);
            ArrayList<Element> jsoupElementsList0 = Xsoup.compile("//span[@class='price']").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList0.size(); i++) {
                // System.out.println(jsoupElementsList0.get(i).text());
                // System.out.println("-----");
            }
            listOfListsOfElements.add(jsoupElementsList0);

            ArrayList<Element> jsoupElementsList1 = Xsoup.compile("//span[contains(@class, 'amstockstatus')]").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList1.size(); i++) {
                //   System.out.println(jsoupElementsList1.get(i).text());
            }
            listOfListsOfElements.add(jsoupElementsList1);
            ArrayList<Element> jsoupElementsList2 = Xsoup.compile("//div[@itemprop='sku']").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList2.size(); i++) {
                //  System.out.println(jsoupElementsList2.get(i).text());
            }
            listOfListsOfElements.add(jsoupElementsList2);
            ArrayList<Element> jsoupElementsList3 = Xsoup.compile("//img[starts-with(@src,'https://trail-gear.com/media/catalog/')]").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList3.size(); i++) {
                //   System.out.println(jsoupElementsList3.get(i).text());
            }
            listOfListsOfElements.add(jsoupElementsList3);
            ArrayList<Element> jsoupElementsList4 = Xsoup.compile("//div[@class='product attribute description']").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList4.size(); i++) {
                //  System.out.println(jsoupElementsList4.get(i).text());
            }
            listOfListsOfElements.add(jsoupElementsList4);
            //
            ////h2[contains(text(),'Specifications')]
            ArrayList<Element> jsoupElementsList5 = Xsoup.compile("//div[@id='specstab']/ul/li").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList5.size(); i++) {
                //   System.out.println(jsoupElementsList5.get(i).text());
            }
            listOfListsOfElements.add(jsoupElementsList5);
            //[contains(text(),'Application Data')]
            ArrayList<Element> jsoupElementsList6 = Xsoup.compile("//div[@id='apptab']/ul/li").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList6.size(); i++) {
                //   System.out.println(jsoupElementsList6.get(i).text());
            }
            listOfListsOfElements.add(jsoupElementsList6);
            ArrayList<Element> jsoupElementsList7 = Xsoup.compile("//div[@id='instructionstab']/a/@href").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList7.size(); i++) {
                //   System.out.println(jsoupElementsList7.get(i).text());
            }
            listOfListsOfElements.add(jsoupElementsList7);
            ArrayList<Element> jsoupElementsList8 = Xsoup.compile("//div[@class='data item content']").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList8.size(); i++) {
                // System.out.println(jsoupElementsList8.get(i).text());
            }
            listOfListsOfElements.add(jsoupElementsList8);*/

            //page link
            elementsStringArrayOneRow[0] = "https://wheelersoffroad.com/" + fileString;
            for (int i = 0; i < listOfListsOfElements.size(); i++) {
                for (Element element : listOfListsOfElements.get(i)) {
                    // img links
                    if ((i == 4) && (element.attr("src").length() > 0)) {
                        cellContents.append(element.attr("src")).append(System.getProperty("line.separator"));
                    }

                    if (((i != 4) || (i != 8)) /*&& (element.text().length() > 0)*/) {
                        //  System.out.println(element.text());
                        cellContents.append(element.text()).append(System.getProperty("line.separator"));
                    }
                    // else if ( (i!=4) && (element.text().length() == 0) ) cellContents.append("_").append(System.getProperty("line.separator"));

                    if ((i == 8) && (element.attr("href").length() > 0)) {
                        System.out.println("+++ 8th_element href =" + element.attr("href"));
                        cellContents = new StringBuilder();
                        cellContents.append(element.attr("href")).append(System.getProperty("line.separator"));
                    }
                }
                elementsStringArrayOneRow[i + 1] = cellContents.toString();
            }

            arrayListOfAllStringsForCSV.add(elementsStringArrayOneRow);
            // if (n>200) break;
        }

        System.out.println("CSV writing STARTED " + (System.currentTimeMillis() - start) / 1000 + " seconds"
                + "__ OR __" + ((System.currentTimeMillis() - start) / 1000 / 60) + "minutes");

        csvWriter.writeAll(arrayListOfAllStringsForCSV, false);
        try {
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

       /* try {
            writeAll(elementsStringArray);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //generate 1row for each item + /n


        //write all rows to csv at once

       /* for(String[] elementsStringArrayOneRow:arrayListOfAllStringsForCSV)
            try {
                writeAll(elementsStringArrayOneRow);
            } catch (IOException e) {
                e.printStackTrace();
            }*/

        System.out.println("CSV writing finished " + (System.currentTimeMillis() - start) / 1000 + " seconds"
                + "__ OR __" + ((System.currentTimeMillis() - start) / 1000 / 60) + "minutes");
    }

}
