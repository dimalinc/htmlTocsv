package classes.writeToXls_manufacturers_scenarios;

import classes.scenarios.writeToXls_CsvAtts_Scenario;
import classes.scenarios.writeToXls_PRESTA_CsvAtts_Scenario;
import classes.scenarios.old_for_multithread.*;

public class Eibach implements Runnable{
    static int filesNumberStart=0, filesNumberFinish=0;
    static String brand = "Eibach"+"_1row";
    static String brandForXls = "Eibach";
    static String dir="D:\\savedHtml\\savedHtml_eibach" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\" + brand;
    static String writeAllCSV_fileName_Runnable = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\Runnable" + brand;
    static String domain = "";

    public static void main(String[] args) {
        // writeToXls_Base_Scenario_EGOR.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        writeToXls_CsvAtts_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
         writeToXls_PRESTA_CsvAtts_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
    }

    public void run() {
        writeToXls_PRESTA_CsvAtts_Scenario_Eibach.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
    }
}
