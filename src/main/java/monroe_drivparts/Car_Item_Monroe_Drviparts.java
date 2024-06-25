package monroe_drivparts;

public class Car_Item_Monroe_Drviparts {

    Item_Processed_Monroe_Drviparts item_processed_monroe_drviparts;
    AppObject appObject;

    String car_Item_title;
    String car_Item_short_desc;
    String car_Item_long_desc;

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
