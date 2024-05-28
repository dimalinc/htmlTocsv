package classes.writeToXls_manufacturers_scenarios;

import classes.scenarios.*;

public class sky_ALL_BIG {
     static int filesNumberStart=0, filesNumberFinish=0;
    // static int filesNumberStart=0, filesNumberFinish=2;
    static String brand = "sky_ALL_BIG"+"_1row";
    static String brandForXls = "Skyjacker";
    static String dir="D:\\savedHtml\\savedHtml_skyALL_BIG" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\cl_" + brand;
    static String domain = "";

    public static void main(String[] args) {
        // writeToXls_CsvAtts_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        // writeToXls_PRESTA_CsvAtts_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        writeToXls_Base_Scenario_EGOR.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
    }
}
