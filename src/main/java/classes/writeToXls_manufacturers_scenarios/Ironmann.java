package classes.writeToXls_manufacturers_scenarios;

import classes.scenarios.*;
import classes.scenarios.old_for_multithread.*;

public class Ironmann implements Runnable{
    static int filesNumberStart=0, filesNumberFinish=0;
    static String brandForFilename = "Ironmann"+"_1row";
    static String brandForXls = "Ironmann";
    static String dir="D:\\savedHtml\\savedHtml_Ironmann_key" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\cl_" + brandForFilename;
    static String writeAllCSV_fileName_Runnable = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\Runnable" + brandForFilename;
    static String domain = "";

    public static void main(String[] args) {
         writeToXls_Base_Scenario_EGOR.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
       // writeToXls_CsvAtts_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //  writeToXls_PRESTA_CsvAtts_Scenario.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
    }


    @Override
    public void run() {
        writeToXls_PRESTA_CsvAtts_Scenario_Ironmann.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
    }
}
