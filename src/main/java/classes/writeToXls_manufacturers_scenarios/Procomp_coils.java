package classes.writeToXls_manufacturers_scenarios;

import classes.scenarios.writeToXls_PRESTA_CsvAtts_Scenario;

public class Procomp_coils {
    static int filesNumberStart=0, filesNumberFinish=0;
    static String brand = "Procomp_coils"+"_1row";
    static String brandForXls = "Procomp";
    static String dir="D:\\savedHtml\\savedHtml_Procomp_coils" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\" + brand;
    static String domain = "";

    public static void main(String[] args) {
      //  writeToXls_Base_Scenario_ItemList.main(brand,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
      //  writeToXls_CsvAtts_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
       // writeToXls_Base_Scenario_ItemList.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        writeToXls_PRESTA_CsvAtts_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);

    }
}