
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.XElements;
import us.codecraft.xsoup.Xsoup;

import java.io.*;
import java.net.*;
import java.net.http.*;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;

public class main {

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

    private static URI uri = URI.create("https://metaltech4x4.com/ford-bronco/");
    private static final String FILE_URL_SAVE_WITH_STREAM =
            "https://metaltech4x4.com/ford-bronco/";
    private static final String FILE_NAME_SAVE_FILE_WITH_STREAM = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\savedFileWstream.html";
    private static final String JSOUP_GET_URL =
            "https://metaltech4x4.com/100-series-land-cruiser-lx470-raw-sliders/";
    private static final String FILE_NAME_RESPONSE_BODY = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\savedResponse.html";
    private static final String FILE_NAME_JSOUP_DOC_HTML = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\JSOUP_DOC_HTML.html";

    public static void main(String[] args) {

        HttpClient client = HttpClient.newHttpClient();
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
        }

        // file save via stream w/o httpclient
        try (BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL_SAVE_WITH_STREAM).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME_SAVE_FILE_WITH_STREAM)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        Document doc = null;
        try {
            doc = Jsoup.connect(JSOUP_GET_URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements links = doc.select("a");
        for(Element element:links){
            //System.out.println(element.html());
            //System.out.println(element.attr("href"));
            //System.out.println(element.attr("src"));
        }

        // get Jsoup element by xpath = XSoup
        XElements xElements =  Xsoup.compile("//*/h1").evaluate(doc);
        ArrayList<Element> jsoupElementsList = xElements.getElements();
        System.out.println(jsoupElementsList.get(0).text());




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

    }
}
