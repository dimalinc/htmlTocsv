package classes.writeToXls_manufacturers_scenarios;

import classes.scenarios.writeToXls_PRESTA_CsvAtts_Scenario;

public class sky_shocks {
    //static int filesNumberStart=185, filesNumberFinish=205;
   static int filesNumberStart=0, filesNumberFinish=0;

    static String brand = "sky_Shocks"+"_1row";
    static final String brandForXls = "Skyjacker";
    static String dir="D:\\savedHtml\\2_savedHtml_sky_shocks" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\cl_" + brand;
    static String domain = "";

    public static void main(String[] args) {
     //   writeToXls_Base_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //   writeToXls_CsvAtts_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        writeToXls_PRESTA_CsvAtts_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);

    }
}