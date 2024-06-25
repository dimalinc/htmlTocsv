package monroe_drivparts;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class Item_Processed_Monroe_Drviparts {
    Document doc;
    Xpath_Item_MonroeDrviparts xpath_item_monroeDrviparts;
    ArrayList<Car_Item_Monroe_Drviparts> car_item_monroe_drviparts_ArrayList=new ArrayList<>();
    ArrayList<Spec> item_specArrayList = new ArrayList<>();
    ArrayList<AppObject> appObjectsArrayList=new ArrayList<>();


    public Item_Processed_Monroe_Drviparts(Xpath_Item_MonroeDrviparts xpath_item_monroeDrviparts) {
        this.xpath_item_monroeDrviparts = xpath_item_monroeDrviparts;
        this.doc = xpath_item_monroeDrviparts.doc;

        create_item_specArrayList();

        create_item_appAttrArrayList();

        create_car_item_monroe_drviparts_ArrayList();
    }

    void create_car_item_monroe_drviparts_ArrayList() {
        for (AppObject appObject:appObjectsArrayList) {
            Car_Item_Monroe_Drviparts car_item_monroe_drviparts = new Car_Item_Monroe_Drviparts(this, appObject);
            car_item_monroe_drviparts_ArrayList.add(car_item_monroe_drviparts);
            System.out.println(car_item_monroe_drviparts);
        }
    }


    void create_item_specArrayList() {
        for (int i = 1; i <= xpath_item_monroeDrviparts.xpath_Spec_jsoupElementsList.size() / 2; i++) {
            item_specArrayList.add(new Spec(doc.selectXpath(xpath_item_monroeDrviparts.final_Spec_xpath).get(i).text(), xpath_item_monroeDrviparts.doc.selectXpath(xpath_item_monroeDrviparts.final_Label_xpath).get(i).text()));
        }
        /*for (Spec spec:item_specArrayList)  System.out.println(spec.specLabel+" "+ spec.specValue);*/
    }

    void create_item_appAttrArrayList() {

        ArrayList<Element> appTable_coloumsTitleElement_arrayList = doc.selectXpath(xpath_item_monroeDrviparts.xpath_appAtt_coloumTitleElement);

        /*System.out.println("  appTable_coloumsTitleElement_arrayList = ");
        for (int i = 0; i < appTable_coloumsTitleElement_arrayList.size(); i++) {
            System.out.println(appTable_coloumsTitleElement_arrayList.get(i).text());
        }*/


        int appRowsNumber = doc.selectXpath(xpath_item_monroeDrviparts.xpath_appAtt).size() / 6;

        for (int k = 1; k <= appRowsNumber; k++) {

            ArrayList<AppAttribute> item_appAttrArrayList = new ArrayList<>();
            ArrayList<Element> appAtt_valuesElements_arrayList = doc.selectXpath("(" + xpath_item_monroeDrviparts.xpath_appAtt + ")[" + k + "]/td");
           // System.out.println("appAtt_valuesElements_arrayList.size() = "+appAtt_valuesElements_arrayList.size());

            for (int i = 0; i < appTable_coloumsTitleElement_arrayList.size(); i++) {

                item_appAttrArrayList.add(new AppAttribute(appTable_coloumsTitleElement_arrayList.get(i).text(),
                        appAtt_valuesElements_arrayList.get(i).text() ) );
            }

            appObjectsArrayList.add(new AppObject(item_appAttrArrayList));

        }

        /*for (int i = 0; i < appObjectsArrayList.size(); i++) {
            ArrayList<AppAttribute> appAttributeList = appObjectsArrayList.get(i).appAttribsList;
            for (int j = 0; j < appAttributeList.size(); j++) {
                System.out.println(appAttributeList.get(j).appName + "---" + appAttributeList.get(j).appValue);
            }
        }*/

        /* for (AppAttribute appAtt:item_appAttrArrayList)   System.out.println(appAtt.appName+" "+ appAtt.appValue);*/

    }


}
