package classes.utils;

import com.opencsv.CSVWriter;
import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class file_utils {

    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\writeAll_";
    static String domain = "";
    static CSVWriter csvWriter;
    static Document doc = null;

    public static ArrayList<String> listFilesUsingJavaIO(String dir) {
        return (ArrayList<String>) Stream.of(new File(dir).listFiles()).sorted(Comparator.comparingLong(File::lastModified))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }

    private static CSVWriter csvWriterInit(String brand) {
        CSVWriter csvWriter = null;
        try {
            //  csvWriter = new CSVWriter(new FileWriter(writeAllCSV_fileName/*,true*/));
            csvWriter = new CSVWriter(new FileWriter(writeAllCSV_fileName +brand+ ".csv"), '^');
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

    public static void writeExcel(List<String[]> allLinesStringArray, String brand) throws IOException {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + brand + ".xlsx";

        try (OutputStream os = Files.newOutputStream(Paths.get(fileLocation));
             Workbook wb = new Workbook(os, "MyApplication", "1.0"))
        {
            Worksheet ws = wb.newWorksheet("Sheet1");
            ws.width(0, 15);
            ws.width(1, 15);
            // header
            ws.range(0, 0, 0, 5).style().fontName("Arial").fontSize(12).bold().fillColor("3366FF").set();

            ArrayList<String> coloumNamesArrayList=new ArrayList<String>();
            /*coloumNamesArrayList= new ArrayList<String>(
                    Arrays.asList("SKU_n", "years+make+model", "Category", "Make", "Model", "Drive_AttributesList", "Drive_Range", "Years_List", "Years_Attribute_Range", "Item_Lift_Attributes_Range", "Title_Final", "Title_Item (temp)", "Title_Car (temp)", "CarAttributes (for description)", "Position_String", "Position_List", "Position_Range", "Lift_String", "Lifts_Att_Range", "Lift_Start", "Lift_Finish", "SKU_Manufacturer", "Item_brand", "Item_general_description",
                            "Item_attributes_for_description", "Brand_general_description", "Features_and_benefits_for_description", "Other_attribs_for_description", "Instructions_PDF_links", "Images_JPG_links", "", "", "", "", "", "", "", "", "","","Car_Position_Range", "Car_Lift_Range", "Car_Lift_Attribute_List"));
            */
            int colNumber=1;
            for (String s:coloumNamesArrayList) {
                ws.value(0, colNumber++,s);
            }
            /* ws.value(0, 0, "SKU");
            ws.value(0, 1, "SKU_n");
            ws.value(0, 2, "years+make+model");
            ws.value(0, 3, "Category");
            ws.value(0, 4, "Make");
            ws.value(0, 5, "Model");
            ws.value(0, 6, "Drive_AttributesList");
            ws.value(0, 7, "Drive_Range");
            ws.value(0, 8, "Years_List");
            ws.value(0, 9, "Years_Attribute_Range");
            ws.value(0, 10, "Item_Lift_Attributes_Range");
            ws.value(0, 11, "Title_Final");
            ws.value(0, 12, "Title_Item (temp)");
            ws.value(0, 13, "Title_Car (temp)");
            ws.value(0, 14, "CarAttributes (for description)");
            ws.value(0, 15, "Position_String");
            ws.value(0, 16, "Position_List");
            ws.value(0, 17, "Position_Range");
            ws.value(0, 18, "Lift_String");
            ws.value(0, 19, "Lifts_Att_Range");
            ws.value(0, 20, "Lift_Start");
            ws.value(0, 21, "Lift_Finish");
            ws.value(0, 22, "Lifts_List");
            ws.value(0, 23, "SKU_Manufacturer");
            ws.value(0, 24, "Item_brand");
            ws.value(0, 25, "Item_general_description");
            ws.value(0, 26, "Item_attributes_for_description");
            ws.value(0, 27, "Brand_general_description");
            ws.value(0, 28, "Features_and_benefits_for_description");
            ws.value(0, 29, "Other_attribs_for_description");
            ws.value(0, 30, "Other_attribs_for_description2\n");
            ws.value(0, 31, "Instructions_PDF_links");
            ws.value(0, 32, "Images_JPG_links");
            ws.value(0, 33, "");
            ws.value(0, 34, "");
            ws.value(0, 35, "");
            ws.value(0, 36, "");
            ws.value(0, 37, "");
            ws.value(0, 38, "");
            ws.value(0, 39, "");
            ws.value(0, 40, "");
            ws.value(0, 41, "");
            ws.value(0, 42, "");
            ws.value(0, 43, "Car_Position_Range");
            ws.value(0, 44, "Car_Lift_Range");
            ws.value(0, 45, "Car_Lift_Attribute_List");*/

            {
               // StringBuilder sb = new StringBuilder();
                int rowNumber=1;
                for (String[] line : allLinesStringArray) {
                   // if (line!=null)
                    { for (int i = 0; i < line.length; i++) { ws.value(rowNumber, i, line[i]); }
                    rowNumber++; }
                  //  sb.setLength(0);
                }

            }
            // writing test line content
            /*ws.range(2, 0, 2, 1).style().wrapText(true).set();
            ws.value(2, 0, "John Smith");
            ws.value(2, 1, 20L);*/
        }
    }
}
