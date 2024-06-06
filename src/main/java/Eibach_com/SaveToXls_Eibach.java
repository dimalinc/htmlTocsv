package Eibach_com;

public class SaveToXls_Eibach {
    //210 269
    //  static int filesNumberStart=210, filesNumberFinish=269;
      static int filesNumberStart=0, filesNumberFinish=0;
    static String brandForFilename = "eibach.com" ;
    static String brandForXls = "Eibach";
    static String dir = "D:\\savedHtml\\savedHtml_Eibach.com_fitment" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\" + brandForFilename;
    static String domain = "https://eibach.com/";

    public static void main(String[] args) {
        //  writeToXls_Base_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //  writeToXls_CsvAtts_Scenario.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //  writeToXls_Base_Scenario_ItemList.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        Eibach_main.main(brandForFilename, brandForXls, dir, writeAllCSV_fileName, domain, filesNumberStart, filesNumberFinish);
    }
}