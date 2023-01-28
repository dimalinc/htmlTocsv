import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.SECONDS;

public class downloadHtml {

    // saving file via Jsoup.get
       /* String docHtml = doc.html();
        try {
            writer = new BufferedWriter(new FileWriter(FILE_NAME_JSOUP_DOC_HTML));
            writer.write(docHtml);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    static final int delay = 750;
    static String linksFilePath = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\src\\main\\java\\stage3motorsports\\stage3motorsports_links.txt";
    static String htmlDir = "D:\\savedHtml\\savedHtml_stage3motorsports\\";

    public static void downloadViaHttpClient(ArrayList<String> linksList) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request;

        for (String linkString : linksList) {
            request = HttpRequest.newBuilder().uri(URI.create(linkString)).timeout(Duration.of(10, SECONDS)).GET().build();
            long downloadingStart = System.currentTimeMillis();

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
                writer = new BufferedWriter(new FileWriter(htmlDir
                        + linkString.split("/")[linkString.split("/").length - 1]/*+".html"*/));
                writer.write(responseBody);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(linksList.indexOf(linkString) + " " + linkString + "downloading finished " + (System.currentTimeMillis() - downloadingStart) + "milllli seconds");
            System.out.println("----");
            System.out.print("Sleep start _____ ");
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Sleep finished");
        }
    }

    public static void fileSaveViaStreamWOhttpclient(ArrayList<String> linksList) {
        for (String linkString : linksList) {
            try (BufferedInputStream in = new BufferedInputStream(new URL(linkString).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(htmlDir
                         + linkString.split("/")[linkString.split("/").length - 1]/*+".html"*/);
            ) {
                System.out.println(htmlDir
                        + linkString.split("/")[linkString.split("/").length - 1].replace('|', '_'));
                long downloadingStart = System.currentTimeMillis();
                byte dataBuffer[] = new byte[8192];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 8192)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
                System.out.println(linksList.indexOf(linkString) + " " + linkString + "downloading finished " + (System.currentTimeMillis() - downloadingStart) + "milllli seconds");
                System.out.println("----");
                System.out.print("Sleep start _____ ");
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Sleep finished");


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // reading links from file
        File linksFile = new File(linksFilePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(linksFilePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String currentLine;
        ArrayList<String> linksList = new ArrayList<>();
        try {
            //  System.out.println(currentLine);
            while ((currentLine = reader.readLine()) != null) {
                linksList.add(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(linksList);

       // fileSaveViaStreamWOhttpclient(linksList);

        downloadViaHttpClient(linksList);

        System.out.println("downloading finished " + (System.currentTimeMillis() - start) / 1000 + " seconds"
                + "__ OR __" + ((System.currentTimeMillis() - start) / 1000 / 60) + "minutes");

    }
}
