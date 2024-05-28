package classes.scenarios;

import com.opencsv.CSVWriter;
import classes.Item;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static classes.utils.file_utils.listFilesUsingJavaIO;
import static classes.utils.file_utils.writeExcel;

public class writeToXls_Double_PrEG__Scenario {

    static String brand /*= "writeToXls_Base_Scenario_EGOR"*/;
    static String brandForXls /*= "writeToXls_Base_Scenario_EGOR"*/ ;
    static String dir /*= "D:\\savedHtml\\savedHtml_sky_lev_kits" + "\\"*/;
    static String writeAllCSV_fileName /*= "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\cl_" + brand*/;
    static String domain = "";
    static ArrayList<String> filesArrayList = new ArrayList<>();
    static ArrayList<String[]> arrayListOfAllStringsForCSV_EGOR = new ArrayList<String[]>();
    static ArrayList<String[]> arrayListOfAllStringsForCSV_Presta = new ArrayList<String[]>();
    static ArrayList<String[]> arrayListOfAllStringsForCSV_noCars_EGOR = new ArrayList<String[]>();
    static ArrayList<String[]> arrayListOfAllStringsForCSV_noCars_Presta = new ArrayList<String[]>();



    public static void main(String inputBrand, String inputBrandForXls, String inputDir, String inputWriteAllCSV_fileName, String inputDomain,int inputFilesNumberStart, int inputFilesNumberFinish) {

        brand=inputBrand; brandForXls=inputBrandForXls;dir=inputDir; writeAllCSV_fileName=inputWriteAllCSV_fileName; domain=inputDomain;
        Long start = System.currentTimeMillis();
        Long startNewItemsCreating = System.currentTimeMillis();
        filesArrayList = listFilesUsingJavaIO(dir);
        System.out.println(filesArrayList);


        int n = 1;
        for (String fileString : filesArrayList) {

              if ( (inputFilesNumberStart!=0) && (n<inputFilesNumberStart) ) { n++; continue; }

            Long startItem = System.currentTimeMillis();
            // Scenario_EGOR starts here
            Item item = new Item(new File(dir + fileString));

            Scenario_EGOR.main(item,arrayListOfAllStringsForCSV_EGOR,arrayListOfAllStringsForCSV_noCars_EGOR);
             Scenario_PRESTA.main(item,brandForXls,arrayListOfAllStringsForCSV_Presta,arrayListOfAllStringsForCSV_noCars_Presta);
            // Scenario_EGOR ends here

            Long finishItem = System.currentTimeMillis();
            System.out.println(n++ + " Item WITH cars processed for s=" + ((finishItem - startItem) / 1000));
            if ( (inputFilesNumberFinish!=0) && (n>inputFilesNumberFinish) ) break;
        }

            // write to EXCEL
        try {
            writeExcel(arrayListOfAllStringsForCSV_EGOR, brand+"_EGOR");
            writeExcel(arrayListOfAllStringsForCSV_Presta, brand+"_Presta");
            writeExcel(arrayListOfAllStringsForCSV_noCars_EGOR, brand+"_noCars"+"_EGOR");
            writeExcel(arrayListOfAllStringsForCSV_noCars_Presta, brand+"_noCars"+"_Presta");
        } catch (IOException e) { e.printStackTrace(); }

        // write to CSV
        /*CSVWriter csvWriter = csvWriterInit();
        csvWriter.writeAll(arrayListOfAllStringsForCSV, true);
        csvWriter.writeAll(arrayListOfAllStringsForCSV_noCars, true);
        try {
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        Long finish = System.currentTimeMillis();
        System.out.println(filesArrayList.size() + " files processed in " + ((finish - start) / 1000) + " seconds");
    }


    private static CSVWriter csvWriterInit() {
        CSVWriter csvWriter = null;
        try {
            //  csvWriter = new CSVWriter(new FileWriter(writeAllCSV_fileName/*,true*/));
            csvWriter = new CSVWriter(new FileWriter(writeAllCSV_fileName + ".csv"), '^');
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvWriter;
    }


}
