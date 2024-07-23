package monroe_drivparts;

import java.util.ArrayList;

public class Car_Item_Monroe_Drviparts {

    Item_Processed_Monroe_Drviparts item_processed_monroe_drviparts;
    AppObject appObject;

    String car_Item_title;
    String car_Item_short_desc;
    String car_Item_long_desc;
    String carLine="CarLine ";
    ArrayList<Integer> yearsArrayList=new ArrayList<>();
    ArrayList<Presta_Attrib> presta_attribArrayList = new ArrayList<>();


    public Car_Item_Monroe_Drviparts(Item_Processed_Monroe_Drviparts item_processed_monroe_drviparts,AppObject appObject) {
        this.item_processed_monroe_drviparts = item_processed_monroe_drviparts;
        this.appObject=appObject;
        /*appObject.getMake();
        appObject.getModel();
        appObject.getDrive();
        appObject.getYearRange();
        appObject.getDesc();
        appObject.getPosition();
        appObject.getQty();*/
        appObject.getallOtherCarParams();

        car_item_fieldsInit();
        yearsArrayListInit();


        presta_attribArrayList_init();


    }

    void yearsArrayListInit() {
        String yearsRangeString=appObject.getYearRange();
        int yearStart=Integer.parseInt(yearsRangeString.split("-")[0]);
        int yearFinish=Integer.parseInt(yearsRangeString.split("-")[1]);
        System.out.println("yearStart = " + yearStart + " yearFinish = " + yearFinish);

        for (int year = yearStart; year <= yearFinish ; year++)
            yearsArrayList.add(year);


    }

    void presta_attribArrayList_init() {
        // прописать алгоритм нумерации атрибутов
        int n=0;
        for (AppAttribute appAttribute:appObject.appAttribsList) {

            presta_attribArrayList.add(new Presta_Attrib(carLine+appAttribute.appName,appAttribute.appValue,n)) ;
        }

        for (AppAttribute appAttribute:appObject.allOtherCarAttribsList) {
            presta_attribArrayList.add(new Presta_Attrib(carLine+appAttribute.appName,appAttribute.appValue,n)) ;
        }

        for (Integer year:yearsArrayList) {
            presta_attribArrayList.add(new Presta_Attrib(carLine + "Year", year.toString(), n++));
        }

        n=0;


        System.out.println(" prestaAttribsArrayList = "+presta_attribArrayList.toString());;
    }

    void car_item_fieldsInit() {
        car_Item_title=item_processed_monroe_drviparts.xpath_item_monroeDrviparts.name+" "+
                appObject.getMake()+" "+appObject.getModel()+" "+appObject.getDrive()+" "+appObject.getYearRange();
    }

    @Override
    public String toString() {
        return "Car_Item_title = "+car_Item_title;
    }
}
