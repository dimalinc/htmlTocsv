package pages;

import com.opencsv.CSVWriter;
import net.minidev.json.JSONUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class wheelersoffroad {

    static String filesFolder = "D:\\Wheelersoffroad_BACKUP\\savedHtml_Replaced"+"\\";

    // getting list of files from folder
    public static Set<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

    public static void writeAll(String[] args) throws IOException {

        /*String[] items1 = {"book", "coin", "pencil"};
        String[] items2 = {"pen", "chair", "lamp"};
        String[] items3 = {"ball", "bowl", "spectacles"};*/

        List<String[]> entries = new ArrayList<>();
        entries.addAll(Collections.singleton(args));
        /*entries.add(items1);
        entries.add(items2);
        entries.add(items3);*/


        try (var fos = new FileOutputStream(writeAllCSV_fileName);
             var osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             var writer = new CSVWriter(osw)) {

            writer.writeAll(entries);
        }
    }

    public static Element getElementByXPath(String xpath, Document doc) {

        String[] tags = xpath.split("/");
        Element clt = doc.body();

        int index;
        int divc = 0;
        boolean done = false;
        for (String tag : tags) {
            if (tag.startsWith("div"))
                divc++;//  ww w  .java  2 s. c o m

            index = 0;
            int bindex = tag.indexOf('[');
            int eindex = tag.indexOf(']');
            if (bindex != -1) {
                index = Integer.parseInt(tag.substring(bindex + 1, eindex)) - 1;
                tag = tag.substring(0, bindex);
            }

            try {
                clt = clt.select(">" + tag).get(index);
            } catch (IndexOutOfBoundsException ex) {
                System.out.println(xpath + " is not valid.");
                clt = null;
                break;
            }

            if (tag.equals("table"))
                clt = clt.select(">tbody").get(0);

        }

        return clt;
    }

    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\writeAll_WHEELERSOFFROAD.csv";

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

       /* HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = null;
        request = HttpRequest.newBuilder()
                .uri(uri)
                .timeout(Duration.of(10, SECONDS))
                .GET()
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // file save via httpclient
        String responseBody = response.body();
        // System.out.println(responseBody);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(FILE_NAME_RESPONSE_BODY));
            writer.write(responseBody);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        // file save via stream w/o httpclient
      /* try (BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL_SAVE_WITH_STREAM).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME_SAVE_FILE_WITH_STREAM)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        /*Document doc = null;
        try {
            doc = Jsoup.connect(JSOUP_GET_URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        // reading links from file
        //  File linksFile = new File("C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\links.txt");
     /*   BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\links.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String currentLine = "1";
        ArrayList<String> linksList = new ArrayList<>();
        try {
          //  System.out.println(currentLine);
            while ((currentLine=reader.readLine())!=null) {
                linksList.add(currentLine);}
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(linksList);*/

       /* for(String linkString:linksList) {
            // file save via stream w/o httpclient
           *//* Path newFilePath = Paths.get("C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\"+"savedHtml\\"
                    +linkString.split("/")[linkString.split("/").length-1]+".html");
            File newFile = new File("C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\"+"savedHtml\\"
                    +linkString.split("/")[linkString.split("/").length-1]+".html");
*//*
            try (BufferedInputStream in = new BufferedInputStream(new URL(linkString).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream("D:\\Wheelersoffroad_BACKUP\\savedHtml"
                         +linkString.split("/")[linkString.split("/").length-1]*//*+".html"*//*);
             )
            {
                System.out.println("D:\\Wheelersoffroad_BACKUP\\savedHtml"
                        +linkString.split("/")[linkString.split("/").length-1]) ;
                byte dataBuffer[] = new byte[8192];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 8192)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        // reading files from folder into Set<String>
        Set<String> filesStringsListInDir = listFilesUsingJavaIO(filesFolder);
        System.out.println("filesStringsListInDir " + filesStringsListInDir.size());
        System.out.println("--------");


        ArrayList<String[]> arrayListOfAllStringsForCSV = new ArrayList<>();

        int n = 0;
        CSVWriter csvWriter = null;
        try {
            csvWriter = new CSVWriter(new FileWriter(writeAllCSV_fileName/*,true*/));
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (String fileString : filesStringsListInDir) {
            n=n+1;
            long creatingFile = System.currentTimeMillis();
            File input = new File(filesFolder + fileString);
        //    System.out.println("creatingFile in " + (System.currentTimeMillis() - creatingFile) + " mili_seconds");
                    System.out.println(n + "___" + fileString);
            Document doc = null;
            try {
                long jsoupParseStart = System.currentTimeMillis();
                doc = Jsoup.parse(input, "UTF-8", "https://wheelersoffroad.com/");

                System.out.println("Jsoup.parsed in " + (System.currentTimeMillis() - jsoupParseStart) + " mili_seconds"
                       /* + "__ OR __" + ((System.currentTimeMillis() - start) / 1000 / 60) + "minutes"*/);
            } catch (IOException e) {
                e.printStackTrace();
            }
           /* Elements links = doc.select("a");
            for (Element element : links) {
                // System.out.println(element.html());
                // System.out.println(element.attr("href"));
                //System.out.println(element.attr("src"));
            }*/
            // get Jsoup element by xpath = XSoup
            // XElements xElements =  Xsoup.compile("//*/h1").evaluate(doc);

            ArrayList<ArrayList<Element>> listOfListsOfElements = new ArrayList<>();
            long findingXpathElements = System.currentTimeMillis();

            ArrayList<Element> jsoupElementsList9 = Xsoup.compile("//h1").evaluate(doc).getElements();
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
            listOfListsOfElements.add(jsoupElementsList8);


        /* for (int i = 0; i <jsoupElementsList1.size() ; i++) {
            System.out.println(jsoupElementsList1.size());
            System.out.println(jsoupElementsList1.get(i).text());
        }*/
//*[@

       /* ArrayList<Element> jsoupElementsList = Xsoup.compile("//*[@class="+'\"'+"woocommerce-breadcrumb"+'\"'+"]").evaluate(doc).getElements();
        System.out.println(jsoupElementsList.size()*//*.get(0).text()*//*);
        ArrayList<Element> jsoupElementsList2 = Xsoup.compile("//*[@class=\"product_title entry-title\"]/text()").evaluate(doc).getElements();
        System.out.println(jsoupElementsList2.size()*//*.get(0).text()*//*);
        ArrayList<Element> jsoupElementsList3 = Xsoup.compile("//bdi").evaluate(doc).getElements();
        System.out.println(jsoupElementsList3.size()*//*.get(0).text()*//*);
        ArrayList<Element> jsoupElementsList4 = Xsoup.compile("//*[@class=\"sku\"]").evaluate(doc).getElements();
        System.out.println(jsoupElementsList4.size()*//*.get(0).text()*//*);
        ArrayList<Element> jsoupElementsList5 = Xsoup.compile("//*[@class=\"woocommerce-product-details__short-description\"]").evaluate(doc).getElements();
        System.out.println(jsoupElementsList5.size()*//*.get(0).text()*//*);
        ArrayList<Element> jsoupElementsList6 = Xsoup.compile("//*[@class=\"woocommerce-product-gallery__image\"]/a/@href").evaluate(doc).getElements();
        System.out.println(jsoupElementsList6.size()*//*.get(0).text()*//*);
        ArrayList<Element> jsoupElementsList7 = Xsoup.compile("//*[@id=\"tab-description\"]/h2/following-sibling").evaluate(doc).getElements();
        System.out.println(jsoupElementsList7.size()*//*.get(0).text()*//*);
        ArrayList<Element> jsoupElementsList8 = Xsoup.compile("//*[@id=\"tab-fitment\"]/h2/following-sibling").evaluate(doc).getElements();
        System.out.println(jsoupElementsList8.size()*//*.get(0).text()*//*);
        ArrayList<Element> jsoupElementsList9 = Xsoup.compile("//*[@id=\"tab-optional-add-ons\"]/h2/following-sibling").evaluate(doc).getElements();
        System.out.println(jsoupElementsList9.size()*//*.get(0).text()*//*);
        ArrayList<Element> jsoupElementsList10 = Xsoup.compile("//*[@id=\"tab-instructions-info\"]/h2/following-sibling").evaluate(doc).getElements();
        System.out.println(jsoupElementsList10.size()*//*.get(0).text()*//*);
        ArrayList<Element> jsoupElementsList11 = Xsoup.compile("//*[@id=\"tab-instructions-info\"]/h2/following-sibling/a/@href").evaluate(doc).getElements();
        System.out.println(jsoupElementsList11.size()*//*.get(0).text()*//*);*/

            // saving file via Jsoup.get
       /* String docHtml = doc.html();
        try {
            writer = new BufferedWriter(new FileWriter(FILE_NAME_JSOUP_DOC_HTML));
            writer.write(docHtml);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

            // doc = Jsoup.parse(responseBody);

            // write one row - string array to csv
            // String[] elementsStringArray = new String[jsoupElementsList9.size()];

// для каждого элемента из подсписка получать текст
            String[] elementsStringArrayOneRow = new String[11];
            //page link
            elementsStringArrayOneRow[0] = "https://wheelersoffroad.com/" + fileString;
            for (int i = 0; i < listOfListsOfElements.size(); i++) {
                StringBuilder cellContents = new StringBuilder();
                for (Element element : listOfListsOfElements.get(i)) {
                    // img links
                    if ((i == 4) && (element.attr("src").length() > 0)) {
                      //  System.out.println(element.attr("src"));
                        cellContents.append(element.attr("src")).append(System.getProperty("line.separator"));
                    }

                    if ( ((i != 4) || (i!=8) ) /*&& (element.text().length() > 0)*/ ) {
                      //  System.out.println(element.text());
                        cellContents.append(element.text()).append(System.getProperty("line.separator"));
                    }
                      // else if ( (i!=4) && (element.text().length() == 0) ) cellContents.append("_").append(System.getProperty("line.separator"));

                    if ((i == 8) && (element.attr("href").length() > 0)) {
                        System.out.println("+++ 8th_element href =" + element.attr("href"));
                        cellContents = new StringBuilder();
                        cellContents.append(element.attr("href")).append(System.getProperty("line.separator"));
                    }

                   /* if ((i != 7) && (element.text().length() > 0)) {
                        //  System.out.println(element.text());
                        cellContents.append(element.text()).append(System.getProperty("line.separator"));
                    }*/
                   // else if ( (i!=8) && (element.text().length() == 0) ) cellContents.append("_").append(System.getProperty("line.separator"));

                }
                elementsStringArrayOneRow[i + 1] = cellContents.toString();
            }

            arrayListOfAllStringsForCSV.add(elementsStringArrayOneRow);
            //  writing each row separately
         //   csvWriter.writeNext(elementsStringArrayOneRow, false);
          /*  if (n%500==0) {
                try {
                    csvWriter.flush();
                    csvWriter.close();
                    csvWriter=new CSVWriter(new FileWriter(writeAllCSV_fileName,true));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
           // if (n>200) break;
        }

        //Create record
        /*for (String[] stringArray : arrayListOfAllStringsForCSV) {
            //Write the record to file
            csvWriter.writeNext(stringArray, false);
        }*/
        // TODO: вернуть назад запись по одному, сравнить производительность с writeAll

        System.out.println("CSV writing STARTED " + (System.currentTimeMillis() - start) / 1000 + " seconds"
                + "__ OR __" + ((System.currentTimeMillis() - start) / 1000 / 60) + "minutes");

       csvWriter.writeAll(arrayListOfAllStringsForCSV, false);
        //close the writer
        try {
           // csvWriter.flush();
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