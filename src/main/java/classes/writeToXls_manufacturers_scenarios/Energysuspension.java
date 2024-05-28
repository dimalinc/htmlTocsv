package classes.writeToXls_manufacturers_scenarios;

import classes.scenarios.writeToXls_Base_Scenario_EGOR;
import classes.scenarios.old_for_multithread.*;

public class Energysuspension implements Runnable{
    static int filesNumberStart=0, filesNumberFinish=0;
    static String brandForFilename = "Energysuspension"+"_1row";
    static String brandForXls = "Energysuspension";
    static String dir="D:\\savedHtml\\savedHtml_energysuspension_KEYSTONE" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\cl_" + brandForFilename;
    static String writeAllCSV_fileName_Runnable = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\Runnable" + brandForFilename;
    static String domain = "";

    public static void main(String[] args) {
        writeToXls_Base_Scenario_EGOR.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //  writeToXls_CsvAtts_Scenario.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //  writeToXls_PRESTA_CsvAtts_Scenario.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
    }

    public void run() {
        writeToXls_PRESTA_CsvAtts_Scenario_Energysuspension.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
    }
}
