package classes.writeToXls_manufacturers_scenarios;

import classes.scenarios.writeToXls_PRESTA_CsvAtts_Scenario;
import classes.scenarios.old_for_multithread.*;

public class HNR_coils implements Runnable{
    static int filesNumberStart=0, filesNumberFinish=0;
    static String brandForFilename = "HNR_coils"+"_1row";
    static String brandForXls = "HNR";
    static String dir="D:\\savedHtml\\savedHtml_H-n-r_spring" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\" + brandForFilename;
    static String writeAllCSV_fileName_Runnable = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\Runnable" + brandForFilename;
    static String domain = "";

    public static void main(String[] args) {
        // writeToXls_Base_Scenario_EGOR.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
      //  writeToXls_CsvAtts_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        writeToXls_PRESTA_CsvAtts_Scenario.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
    }

    public void run() {
        writeToXls_PRESTA_CsvAtts_Scenario_HNR_coils.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName_Runnable,domain,filesNumberStart,filesNumberFinish);
    }
}
