package classes.writeToXls_manufacturers_scenarios;

import classes.scenarios.writeToXls_Base_Scenario_EGOR;
import classes.scenarios.writeToXls_PRESTA_CsvAtts_Scenario;


public class Rancho {
    //  static int filesNumberStart=412, filesNumberFinish=418;
      static int filesNumberStart=0, filesNumberFinish=0;
    static String brand = "Rancho"+"_1row";
    static String brandForXls = "Rancho";
    static String dir="D:\\savedHtml\\savedHtml_Rancho_key" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\" + brand;
    static String domain = "";

    public static void main(String[] args) {
      //  writeToXls_Base_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //    writeToXls_CsvAtts_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //  writeToXls_Base_Scenario_ItemList.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //  writeToXls_PRESTA_CsvAtts_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        writeToXls_Base_Scenario_EGOR.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
    }
}
