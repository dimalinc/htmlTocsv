package super_springs;

public class SaveToXls_SuperSprings {
    // static int filesNumberStart=160, filesNumberFinish=170;
      static int filesNumberStart=0, filesNumberFinish=0;
    static String brandForFilename = "superspringsinternational.com" ;
    static String brandForXls = "SuperSprings";
    static String dir = "D:\\savedHtml\\savedHtml_.superspringsinternational.com" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\" + brandForFilename;
    static String domain = "https://www.superspringsinternational.com/";

    public static void main(String[] args) {
        //  writeToXls_Base_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //  writeToXls_CsvAtts_Scenario.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //  writeToXls_Base_Scenario_ItemList.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        SuperSprings_main.main(brandForFilename, brandForXls, dir, writeAllCSV_fileName, domain, filesNumberStart, filesNumberFinish);
    }
}