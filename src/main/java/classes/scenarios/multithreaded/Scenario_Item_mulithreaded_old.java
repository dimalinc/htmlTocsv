package classes.scenarios.multithreaded;

import classes.Item;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static classes.utils.file_utils.listFilesUsingJavaIO;
import static classes.utils.file_utils.writeExcel;


public class Scenario_Item_mulithreaded_old {
  // static ArrayList<manufacturer_DTO> manufacturerDtosArrayList= new ArrayList<>();
  /*  static {
        manufacturerDtosArrayList.add(new manufacturer_DTO("Ironmann_19","Ironmann","D:\\savedHtml\\savedHtml_Ironmann_key"));
        manufacturerDtosArrayList.add(new manufacturer_DTO("ProcompCoils_39","Procomp","D:\\savedHtml\\savedHtml_Procomp_coils"));
       // manufacturerDtosArrayList.add(new manufacturer_DTO("","",""));
    }*/

    static int filesAmountLeft;
    static ArrayList<String> filesArrayList = new ArrayList<>();

    static String brand = "Moog_3597";
    static String brandForXls = "Moog" ;
    static String dir = "D:\\savedHtml\\savedhtml_moog_all"+"\\";
    static String writeAllCSV_fileName /*= "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\cl_" + brand*/;
    static String domain = "";
    static ArrayList<String[]> arrayListOfAllStringsForCSV_EGOR = new ArrayList<String[]>();
    static ArrayList<String[]> arrayListOfAllStringsForCSV_Presta = new ArrayList<String[]>();
    static ArrayList<String[]> arrayListOfAllStringsForCSV_noCars_EGOR = new ArrayList<String[]>();
    static ArrayList<String[]> arrayListOfAllStringsForCSV_noCars_Presta = new ArrayList<String[]>();

    public static void main(String[] args) throws InterruptedException {
        Long start = System.currentTimeMillis();

        ExecutorService pool = Executors.newFixedThreadPool(6);
    /*pool.submit(Foo::foo);
    pool.submit(() -> bar());
    pool.submit(new Runnable() {
        @Override
        public void run() {
            baz();
        }
    });*/

        /*pool.submit(new Runnable() {
            @Override
            public void run() {
                executeIronmann();
            }
        });*/

        filesArrayList = listFilesUsingJavaIO(dir);
        filesAmountLeft=filesArrayList.size()-1;
        System.out.println(filesArrayList);

        for (String fileString:filesArrayList) {
            pool.submit(() -> item_processing(fileString,
                    arrayListOfAllStringsForCSV_EGOR, arrayListOfAllStringsForCSV_noCars_EGOR,
                    arrayListOfAllStringsForCSV_Presta,arrayListOfAllStringsForCSV_noCars_Presta)); }

        pool.shutdown();

        if (pool.awaitTermination(9000000, TimeUnit.SECONDS) )
        try {
            System.out.println(arrayListOfAllStringsForCSV_Presta.size());
            System.out.println(arrayListOfAllStringsForCSV_noCars_Presta.size());

          /*  writeExcel(arrayListOfAllStringsForCSV_EGOR, brand+"_EGOR_multi");
            writeExcel(arrayListOfAllStringsForCSV_noCars_EGOR, brand+"_noCars_EGOR_multi");

            Thread.sleep(50000);
            writeExcel(arrayListOfAllStringsForCSV_noCars_Presta, brand+"_noCars_Presta_multi");

            Thread.sleep(50000);*/
            writeExcel(arrayListOfAllStringsForCSV_Presta, brand+"_Presta_multi");
        } catch (IOException e) { e.printStackTrace(); }

        //  pool.submit(Scenario_mulithreaded::executeIronmann);

        Long finish = System.currentTimeMillis();
        System.out.println(filesArrayList.size() + " files processed in " + ((finish - start) / 1000) + " seconds");

    }

    static void foo() { /* долгая задача 1 */ }
    static void bar() { /* долгая задача 2 */ }
    static void baz() { /* долгая задача 3 */ }

   // static void execute_Item_Runnable(Item item) {new Ironmann_Runnable().run();}

    static void item_processing(String fileString, ArrayList<String[]> arrayListOfAllStringsForCSV_EGOR,
    ArrayList<String[]> arrayListOfAllStringsForCSV_noCars_EGOR,
    ArrayList<String[]> arrayListOfAllStringsForCSV_Presta,
    ArrayList<String[]> arrayListOfAllStringsForCSV_noCars_Presta) {
        System.out.print("Processing "+fileString+" -> ");
       Item item= new  Item(new File(dir + fileString),brandForXls,arrayListOfAllStringsForCSV_EGOR,arrayListOfAllStringsForCSV_noCars_EGOR,arrayListOfAllStringsForCSV_Presta,arrayListOfAllStringsForCSV_noCars_Presta);
        System.out.println("filesAmountLeft = "+(filesAmountLeft--) );
    }


  //  static void execute_() {new ().run();}



}
