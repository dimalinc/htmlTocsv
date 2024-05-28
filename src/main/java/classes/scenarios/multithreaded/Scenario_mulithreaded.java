package classes.scenarios.multithreaded;

import classes.writeToXls_manufacturers_scenarios.Energysuspension2;
import classes.writeToXls_manufacturers_scenarios.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Scenario_mulithreaded {

    static ArrayList<String> filesArrayList = new ArrayList<>();

    static String brand /*= "writeToXls_Base_Scenario_EGOR"*/;
    static String brandForXls /*= "writeToXls_Base_Scenario_EGOR"*/ ;
    static String dir = "D:\\savedHtml\\savedHtml_sky_lev_kits" + "\\";
    static String writeAllCSV_fileName /*= "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\cl_" + brand*/;
    static String domain = "";
    static ArrayList<String[]> arrayListOfAllStringsForCSV = new ArrayList<String[]>();
    static ArrayList<String[]> arrayListOfAllStringsForCSV_noCars = new ArrayList<String[]>();

    public static void main(String[] args) throws InterruptedException {
    ExecutorService pool = Executors.newFixedThreadPool(5);
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

        /*filesArrayList = listFilesUsingJavaIO(dir);
        System.out.println(filesArrayList);*/

        pool.submit(Scenario_mulithreaded::execute_Ironmann);
        pool.submit(Scenario_mulithreaded::execute_HNR_coils);
        pool.submit(Scenario_mulithreaded::execute_GenY_key);

        pool.submit(Scenario_mulithreaded::execute_aFe);
        pool.submit(Scenario_mulithreaded::execute_bak);
        pool.submit(Scenario_mulithreaded::execute_Belltech_coils);
        pool.submit(Scenario_mulithreaded::execute_bw_trailer_hitches);
        pool.submit(Scenario_mulithreaded::execute_Daystar_key);
        pool.submit(Scenario_mulithreaded::execute_DeeZee);
        pool.submit(Scenario_mulithreaded::execute_Eibach);
        pool.submit(Scenario_mulithreaded::execute_Energysuspension);
        pool.submit(Scenario_mulithreaded::execute_Energysuspension2);
        pool.submit(Scenario_mulithreaded::execute_Fabtech_kits);
        pool.submit(Scenario_mulithreaded::execute_Fabtech_other);
        pool.submit(Scenario_mulithreaded::execute_Fox_key);

      //  pool.submit(Scenario_mulithreaded::executeIronmann);
        pool.shutdown();


    }

    static void foo() { /* долгая задача 1 */ }
    static void bar() { /* долгая задача 2 */ }
    static void baz() { /* долгая задача 3 */ }

   // static void execute_Item_Runnable(Item item) {new Ironmann_Runnable().run();}


    static void execute_aFe() {new aFe().run();}
    static void execute_bak() {new bak().run();}
    static void execute_Belltech_coils() {new Belltech_coils().run();}
    static void execute_bw_trailer_hitches() {new bw_trailer_hitches().run();}
    static void execute_Daystar_key() {new Daystar_key().run();}
    static void execute_DeeZee() {new DeeZee().run();}
    static void execute_Eibach() {new Eibach().run();}
    static void execute_Energysuspension2() {new Energysuspension2().run();}
    static void execute_Energysuspension() {new Energysuspension().run();}
    static void execute_Fabtech_kits() {new Fabtech_kits().run();}
    static void execute_Fabtech_other() {new Fabtech_other().run();}
    static void execute_Fox_key() {new Fox_key().run();}
    static void execute_GenY_key() {new GenY_key().run();}
    static void execute_HNR_coils() {new HNR_coils().run();}
    static void execute_Ironmann() {new Ironmann().run();}
  //  static void execute_() {new ().run();}



}
