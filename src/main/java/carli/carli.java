package carli;

import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class carli {

    static ArrayList<String[]> arrayListOfAllStringsForCSV = new ArrayList<String[]>();
    static ArrayList<String> xpathStringsList = new ArrayList<>();
    static String[] elementsStringArrayOneRow = new String[11];
    static StringBuilder cellContents;
    static File input = null;
    static int filesCount = 0;

    private static ArrayList<String> xpathStringsListInit() {
        xpathStringsList.add("//h1");
        xpathStringsList.add("(//span[@class='woocommerce-Price-amount amount'])[1]");
        xpathStringsList.add("//div[@class='short-description']");
        xpathStringsList.add("//div[@class='summary-container']");
        xpathStringsList.add("//div[@class='woocommerce-product-details__short-description']");
        // check table import col4
        xpathStringsList.add("//div[@class='table-1']");
        xpathStringsList.add("//div[@class='details component_data']");
        xpathStringsList.add("//div[@id='tab-description']");
        xpathStringsList.add("//div[@class='woocommerce-Tabs-panel woocommerce-Tabs-panel--inst_tab panel entry-content wc-tab']");
        xpathStringsList.add("//div[@class='woocommerce-Tabs-panel woocommerce-Tabs-panel--inst_tab panel entry-content wc-tab']//@href");

        xpathStringsList.add("//div[@id='tab-additional_information']//descendant::table");
        xpathStringsList.add("//div[@id='tab-media_tab']");
        xpathStringsList.add("//div[@class='details component_data']");
        xpathStringsList.add("//div[@class='component_title_wrapper']");
        // /@src
       // xpathStringsList.add("//img[@alt='' and starts-with(@src,'https://www.carlisuspension.com/wp-content/uploads/')]");
        xpathStringsList.add("//img[@class='zoomImg' and contains(@src,'https://www.carlisuspension.com/wp-content/uploads/')]");
        xpathStringsList.add("//ol/li/img[@draggable='false' and contains(@src,'https://www.carlisuspension.com/wp-content/uploads/')]");
        //xpathStringsList.add("");
        return xpathStringsList;
    }

    static String filesFolder = "D:\\savedHtml\\savedHtml_carli_2024" + "\\";
    static String domain = "https://carlisuspension.com/";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\writeAll_CARLI_2024.csv";
    static CSVWriter csvWriter;
    static Document doc = null;
    static ArrayList<ArrayList<Element>> jsoup_listOfListsOfElements;

    public static ArrayList<String> listFilesUsingJavaIO(String dir) {
        return (ArrayList<String>) Stream.of(new File(dir).listFiles())
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

        xpathStringsList = xpathStringsListInit();
        long start = System.currentTimeMillis();
        // reading files from folder into List<String>
        ArrayList<String> filesStringsListInDir = listFilesUsingJavaIO(filesFolder);
        /*System.out.println("filesStringsListInDir " + filesStringsListInDir.size());
        System.out.println("--------");*/

        for (String fileString : filesStringsListInDir) {
          /*  for (int ti = 0; ti < 1; ti++) {

                MultithreadingDemo object
                        = new MultithreadingDemo(fileString);
                object.start();

                try {
                    object.join(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            System.out.println("fileString = " + fileString);
            filesCount = filesCount + 1;
            input = new File(filesFolder + fileString);
            System.out.println(filesCount + "___" + fileString);
            doc = docInit(input);

            //jsoup elements list init
            jsoup_listOfListsOfElements = new ArrayList<>();
            for (String xpathString : xpathStringsList) {
                ArrayList<Element> jsoupElementsList=doc.selectXpath(xpathString);
                jsoup_listOfListsOfElements.add(jsoupElementsList);
            }

            String[] elementsStringArrayOneRow = new String[xpathStringsList.size()+1];
            //page link
            elementsStringArrayOneRow[0] = domain + fileString;
            for (int i = 0; i < jsoup_listOfListsOfElements.size(); i++) {
                cellContents = new StringBuilder();
                for (Element element : jsoup_listOfListsOfElements.get(i)) {
                    cellContents.append(element.text());



                    if (element.text().length()==0)
                        cellContents.append(element.attr("href"));

                   /* if ((element.text().length()==0) && (element.attr("href").length()==0 ) ) {
                        cellContents.append(element.attr("src")).append(System.getProperty("line.separator"));
                    }*/

                    // img links
                    if ((14<=i) && (i<=18) && (element.attr("src").length() > 0)) {
                        cellContents.append(element.attr("src")).append(System.getProperty("line.separator"));
                        System.out.println("+++  src =" + element.attr("src"));
                    }
                    else cellContents.append("_").append(System.getProperty("line.separator"));

                    /*if (((i != 11) || (i != 8)) && (element.text().length() > 0)) {
                        //  System.out.println(element.text());
                        cellContents.append(element.text()).append(System.getProperty("line.separator"));
                    }*/

                    // if ( (i!=11) && (element.text().length() == 0) ) cellContents.append("_").append(System.getProperty("line.separator"));

                   /* if ((i == 11) && (element.attr("href").length() > 0)) {
                          System.out.println("+++ 11th_element href =" + element.attr("href"));
                        cellContents = new StringBuilder();
                        cellContents.append(element.attr("href")).append(System.getProperty("line.separator"));
                    }*/
                }
                elementsStringArrayOneRow[i + 1] = cellContents.toString();
            }
            arrayListOfAllStringsForCSV.add(elementsStringArrayOneRow);
            //  System.out.println("Added line: " + Arrays.toString(elementsStringArrayOneRow));
            //if (n > 10) break;
        }

       // System.out.println("arrayListOfAllStringsForCSV SIZE= " + arrayListOfAllStringsForCSV.size());
        System.out.println("CSV writing STARTED " + (System.currentTimeMillis() - start) / 1000 + " seconds"
                + "__ OR __" + ((System.currentTimeMillis() - start) / 1000 / 60) + "minutes");

/*
        for (String[] stringArray:arrayListOfAllStringsForCSV ) {
            for (int i = 0; i <stringArray.length; i++) {
                System.out.print(stringArray[i] + " /// ");
            }
            System.out.println("----------");
        }
*/
        csvWriter = csvWriterInit();
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
