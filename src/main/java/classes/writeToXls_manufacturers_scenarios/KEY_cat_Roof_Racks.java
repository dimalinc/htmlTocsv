package classes.writeToXls_manufacturers_scenarios;

import classes.scenarios.writeToXls_PRESTA_CsvAtts_Scenario;

public class KEY_cat_Roof_Racks {
    static int filesNumberStart=0, filesNumberFinish=0;
    static String brand = "Roof_Racks"+"_1row";
    static String brandForXls = "";
    static String dir="D:\\savedHtml\\savedHtml_KEY_cat_Roof_Racks" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\" + brand;
    static String domain = "";

    public static void main(String[] args) {
        //  writeToXls_Base_Scenario_EGOR.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
     //   writeToXls_CsvAtts_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
          writeToXls_PRESTA_CsvAtts_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);

    }
}
