package classes.writeToXls_manufacturers_scenarios;

import classes.scenarios.writeToXls_Base_Scenario_EGOR;
import classes.scenarios.writeToXls_Base_Scenario_ItemList;

public class sky_Lift_kits {
    static int filesNumberStart=0, filesNumberFinish=0;
    static String brand = "sky_Lift_Kits"+"_1row";
    static String brandForXls = "Skyjacker";
    static String dir="D:\\savedHtml\\savedHtml_sky_lift_kits" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\cl_" + brand;
    static String domain = "";

    public static void main(String[] args) {
        writeToXls_Base_Scenario_EGOR.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
    }
}
