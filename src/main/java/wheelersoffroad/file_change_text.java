package wheelersoffroad;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class file_change_text {

    public static void writeFile(String fileContents, String fileString) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("D:\\htmlTocsv\\" + "savedHtml_replaced_wheelers0ffroad\\" + fileString);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.write(fileContents);
       // System.out.println("written file number " + i + "___" + fileString);
        writer.flush();
        writer.close();
    }

    public static Set<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        boolean fileNeedsResaving = false;

        String[][] stringReplacementCheck = new String[6][2];
        stringReplacementCheck[0][0] = "<!--<h2>Specifications";
        stringReplacementCheck[0][1] = "<h2>Specifications";
        stringReplacementCheck[1][0] = "Specifications</h2>-->";
        stringReplacementCheck[1][1] = "Specifications</h2";
        stringReplacementCheck[2][0] = "<!--<h2>Application Data";
        stringReplacementCheck[2][1] = "<h2>Application Data";
        stringReplacementCheck[3][0] = "Application Data</h2>-->";
        stringReplacementCheck[3][1] = "Application Data</h2>-->";
        stringReplacementCheck[4][0] = "<!--<h2>Warranty";
        stringReplacementCheck[4][1] = "<h2>Warranty";
        stringReplacementCheck[5][0] = "Warranty</h2>-->";
        stringReplacementCheck[5][1] = "Warranty</h2>";

        ArrayList<String> stringsNeedRepalcement = new ArrayList<>();
        stringsNeedRepalcement.add("<!--<h2>Specifications");
        stringsNeedRepalcement.add("Specifications</h2>-->");
        stringsNeedRepalcement.add("<!--<h2>Application Data");
        stringsNeedRepalcement.add("Application Data</h2>-->");
        stringsNeedRepalcement.add("<!--<h2>Warranty");
        stringsNeedRepalcement.add("Warranty</h2>-->");

        ArrayList<String> replacementsStrings = new ArrayList<>();
        replacementsStrings.add("<h2>Specifications");
        replacementsStrings.add("Specifications</h2>");
        replacementsStrings.add("<h2>Application Data");
        replacementsStrings.add("Application Data</h2>");
        replacementsStrings.add("<h2>Warranty");
        replacementsStrings.add("Warranty</h2>");

        int i = 0;
        // reading files from folder into Set<String>
        Set<String> filesStringsListInDir = listFilesUsingJavaIO("C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\" + "savedHtml\\");
        System.out.println("filesStringsListInDir.size()=" + filesStringsListInDir.size());
        System.out.println("--------");

        for (String fileString : filesStringsListInDir) {

            File input = new File("C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\" + "savedHtml\\" + fileString);
            //   System.out.println("input.length()"+input.length());
            Document doc = null;
            try {
                doc = Jsoup.parse(input, "UTF-8", "https://wheelersoffroad.com/");
                //  System.out.println("doc parsed file number =" + i++);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String docHtmlReplaced=doc.html();
            if (docHtmlReplaced.contains("<!--<h2>")) docHtmlReplaced=doc.html().replace("<!--<h2>","<h2>");
            if (docHtmlReplaced.contains("</h2>-->")) docHtmlReplaced=docHtmlReplaced.replace("</h2>-->","</h2>");

          //  for (String s : stringsNeedRepalcement) {
              //  if (doc.html().contains(s)) {
                    //  doc.body().text().replace(s, replacementsStrings.get(stringsNeedRepalcement.indexOf(s)));
                  //  doc.html().replace(s, replacementsStrings.get(stringsNeedRepalcement.indexOf(s)));
                //   doc.html().replace("<!--<h2>Specifications","<h2>Specifications");
                    // doc.html().replace("Specifications</h2>-->","Specifications</h2>");
                    // doc.html().replace("<!--<h2>Application Data","<h2>Application Data");
                    //doc.html().replace("Application Data</h2>-->","Application Data</h2>");
                    //doc.html().replace("<!--<h2>Warranty","<h2>Warranty");
                    //doc.html().replace("Warranty</h2>-->","Warranty</h2>");*/

                    fileNeedsResaving = true;

                    //break;
              //  }
        //    }

          //  if (fileNeedsResaving) {
                /*PrintWriter writer = null;
                try {
                    writer = new PrintWriter("D:\\htmlTocsv\\" + "savedHtml_replaced_wheelers0ffroad\\" + fileString);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                writer.write(doc.html());
                System.out.println("written file number " + i + "___" + fileString);
                writer.flush();
                writer.close();*/

                writeFile(docHtmlReplaced,fileString);

                fileNeedsResaving = false;
           // }

        }

        System.out.println((System.currentTimeMillis() - start) / 1000 + " seconds"
                + "__ OR __" + ((System.currentTimeMillis() - start) / 1000 / 60) + "minutes");

    }
}
