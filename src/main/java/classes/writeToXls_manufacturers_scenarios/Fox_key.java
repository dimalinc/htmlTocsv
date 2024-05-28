package classes.writeToXls_manufacturers_scenarios;

import classes.scenarios.writeToXls_PRESTA_CsvAtts_Scenario;
import classes.scenarios.old_for_multithread.*;

public class Fox_key implements Runnable{
    //   static int filesNumberStart=412, filesNumberFinish=418;
     static int filesNumberStart=0, filesNumberFinish=0;
    static String brandForFilename = "Fox"+"_1row";
    static String brandForXls = "FOX";
    static String dir="D:\\savedHtml\\savedHtml_Fox_key" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\" + brandForFilename;
    static String writeAllCSV_fileName_Runnable = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\Runnable" + brandForFilename;
    static String domain = "";

    public static void main(String[] args) {
        //  writeToXls_Base_Scenario_EGOR.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        // writeToXls_CsvAtts_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //   writeToXls_Base_Scenario_ItemList.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
          writeToXls_PRESTA_CsvAtts_Scenario.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);

    }

    public void run() {
        writeToXls_PRESTA_CsvAtts_Scenario_Fox_key.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
    }
}
