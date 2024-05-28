package timberen;

import com.opencsv.CSVWriter;
import elements.XpathElementContainer;
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

public class timberen {

    static ArrayList<String[]> arrayListOfAllStringsForCSV = new ArrayList<String[]>();
    static ArrayList<String> xpathStringsList = new ArrayList<>();
    static String[] elementsStringArrayOneRow = new String[11];
    static StringBuilder cellContents;
    static File input = null;
    static int filesCount = 0;

    private static ArrayList<String> xpathStringsListInit() {
       // xpathStringsList.add(" ");
        xpathStringsList.add("//div[@class='fitment-cell__value']/text()");
        xpathStringsList.add("//img[@class='srcset']/@src");
        xpathStringsList.add("//div[@class='media__thumb']/img/@src");
        /*xpathStringsList.add("//div[@class='wsm-prod-summary']");
        xpathStringsList.add("//span[@class='wsm-cat-ship-remarks-value']");
        xpathStringsList.add("//div[@itemprop='description']");
        xpathStringsList.add("//div[@id='wsm-prod-tab-details']/*");
        xpathStringsList.add("//li[@class='wsm-file-pdf-small']/a/@href");
        xpathStringsList.add("//img[@itemprop='image']/@src");
        xpathStringsList.add("//img[@class='wsm_product_thumb_zoom']/@src");*/
        //xpathStringsList.add("");
        return xpathStringsList;
    }

    static String brand = "Timbren";
    static String filesFolder = "D:\\savedHtml\\savedHtml_timberen"+ "\\";
    static String domain = "https://timbren.com";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\writeAll_"+brand+".csv";
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

        csvWriter = csvWriterInit();

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
            /*for (String xpathString : xpathStringsList) {
                ArrayList<Element> jsoupElementsList=doc.selectXpath(xpathString);
                jsoup_listOfListsOfElements.add(jsoupElementsList);
            }*/

            /*String[] elementsStringArrayOneRow = new String[jsoup_listOfListsOfElements.size()+1];
            //page link
            elementsStringArrayOneRow[0] = (domain +"/" +fileString).replace(".html","");*/
            /*for (int i = 0; i < jsoup_listOfListsOfElements.size(); i++) {
                cellContents = new StringBuilder();
                for (Element element : jsoup_listOfListsOfElements.get(i)) {
                    cellContents.append(element.text()).append(System.getProperty("line.separator"));

                   *//* if ((i == 4) && (element.attr("src").length() > 0)) {
                        cellContents.append(element.attr("src")).append(System.getProperty("line.separator"));
                    }

                    if (((i != 4) || (i != 8)) *//**//*&& (element.text().length() > 0)*//**//*) {
                        cellContents.append(element.text()).append(System.getProperty("line.separator"));
                    }*//*

                    if ((i == 5) && (element.attr("href").length() > 0)) {
                        //      System.out.println("+++ 5th_element href =" + element.attr("href"));
                        cellContents = new StringBuilder();
                        cellContents.append(domain).append(element.attr("href")).append(System.getProperty("line.separator"));
                    }
                    if ((i == 6) && (element.attr("src").length() > 0)) {
                        //      System.out.println("+++ 6th_element src =" + element.attr("src"));
                        //   cellContents = new StringBuilder();
                        cellContents.append(element.attr("src")).append(System.getProperty("line.separator"));
                    }
                    if ((i == 7) && (element.attr("src").length() > 0)) {
                        //    System.out.println("+++ 7th_element src =" + element.attr("src"));
                        //  cellContents = new StringBuilder();
                        cellContents.append(element.attr("src")).append(System.getProperty("line.separator"));
                    }
                }
                elementsStringArrayOneRow[i + 1] = cellContents.toString();
            }*/

            String[] elementsStringArrayOneRow = new String[xpathStringsList.size()+1];
            //page link
            elementsStringArrayOneRow[0] = (domain +"/" +fileString).replace(".html","");
            int i=1;
            for (String xpath:xpathStringsList) {
                XpathElementContainer xpathElementContainer = new XpathElementContainer(xpath,doc,i);
                String cell =xpathElementContainer.getCsvCellValue().replace("\n","");

                elementsStringArrayOneRow[i] = cell;
                i++;
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
