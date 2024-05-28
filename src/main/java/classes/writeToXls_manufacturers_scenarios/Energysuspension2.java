package classes.writeToXls_manufacturers_scenarios;

import classes.scenarios.writeToXls_Double_PrEG__Scenario;


public class Energysuspension2 implements Runnable{
    static int filesNumberStart=0, filesNumberFinish=0;
    static String brandForFilename = "Energysuspension2"+"_1row";
    static String brandForXls = "Energysuspension2";
    static String dir="D:\\savedHtml\\savedHtml_energysuspension_KEYSTONE" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\cl_" + brandForFilename;
    static String writeAllCSV_fileName_Runnable = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\Runnable" + brandForFilename;
    static String domain = "";

    public static void main(String[] args) {
        writeToXls_Double_PrEG__Scenario.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //  writeToXls_CsvAtts_Scenario.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //  writeToXls_PRESTA_CsvAtts_Scenario.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
    }

    @Override
    public void run() {
        writeToXls_Double_PrEG__Scenario.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
    }
}
