package crown;

public class SaveToXls_Crown {
    // static int filesNumberStart=160, filesNumberFinish=170;
      static int filesNumberStart=0, filesNumberFinish=0;
    static String brandForFilename = "CROWNAUTOMOTIVE.NET" ;
    static String brandForXls = "CROWN";
    static String dir = "D:\\savedHtml\\savedHtml_crownautomotive.net" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\" + brandForFilename;
    static String domain = "https://www.crownautomotive.net/";

    public static void main(String[] args) {
        //  writeToXls_Base_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //  writeToXls_CsvAtts_Scenario.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //  writeToXls_Base_Scenario_ItemList.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        Crown_main.main(brandForFilename, brandForXls, dir, writeAllCSV_fileName, domain, filesNumberStart, filesNumberFinish);
    }
}