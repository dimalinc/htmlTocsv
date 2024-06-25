package monroe_drivparts;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class Spec {


    static ArrayList<Element> xpath_Spec_Label_jsoupElementsList=new ArrayList<>();
    static ArrayList<Element> xpath_Spec_Value_jsoupElementsList=new ArrayList<>();


    String specLabel;
    String specValue;


    public Spec(String specName, String specValue) {
        this.specLabel = specName;
        this.specValue = specValue;
    }

}
