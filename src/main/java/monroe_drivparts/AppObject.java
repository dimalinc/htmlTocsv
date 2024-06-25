package monroe_drivparts;

import java.util.ArrayList;
import java.util.HashSet;

public class AppObject {
     static ArrayList<String> standardAppAttribsNamesList = new ArrayList<>();
    static void standardAppAttribsNames_init() {
        standardAppAttribsNamesList.add("Make");
        standardAppAttribsNamesList.add("Model");
        standardAppAttribsNamesList.add("Year Range");
        standardAppAttribsNamesList.add("Position");
        standardAppAttribsNamesList.add("Drive Wheel");
        standardAppAttribsNamesList.add("Veh. Qty.");
        standardAppAttribsNamesList.add("Description");
    }

    ArrayList<AppAttribute> appAttribsList=new ArrayList<>();
    HashSet<AppAttribute> allOtherCarAttribsList=new HashSet<>();

    public AppObject(ArrayList<AppAttribute> appAttribsList) {
        this.appAttribsList = appAttribsList;
        standardAppAttribsNames_init();
    }



    String getMake() {
        System.out.println(getAppAttrByName("Make") );
       return getAppAttrByName("Make")  ;
    }

    String getModel() {
        System.out.println(getAppAttrByName("Model") );
        return getAppAttrByName("Model")  ;
    }
    String getYearRange() {
        System.out.println(getAppAttrByName("Year Range") );
        return getAppAttrByName("Year Range")  ;
    }
    String getPosition() {
        System.out.println(getAppAttrByName("Position") );
        return getAppAttrByName("Position")  ;
    }
    String getDrive() {
        System.out.println(getAppAttrByName("Drive Wheel") );
        return getAppAttrByName("Drive Wheel")  ;
    }
    String getQty() {
        System.out.println(getAppAttrByName("Veh. Qty.") );
        return getAppAttrByName("Veh. Qty.")  ;
    }

    String getDesc() {
        System.out.println(getAppAttrByName("Description") );
        return getAppAttrByName("Description")  ;
    }

    String getallOtherCarParams() {
        String result="";

        for (AppAttribute appAttribute:appAttribsList) {
            boolean isInStdAttrList = true;
            if (! standardAppAttribsNamesList.contains(appAttribute.appName))
                    allOtherCarAttribsList.add(appAttribute);
        }

        for (AppAttribute appAttribute:allOtherCarAttribsList)
            result=result+  appAttribute.appName + " : " + appAttribute.appValue + System.lineSeparator() ;

        //  result=replaceLastOccurrence(result,System.lineSeparator(),"");

        System.out.println(result);
        return result  ;
    }



    String getAppAttrByName(String appObjName) {

        for (AppAttribute appAttribute:appAttribsList)
        if (appAttribute.appName.equals(appObjName) )
            return appAttribute.appValue;

        return "";
    }

    public static String replaceLastOccurrence(String original, String target, String replacement) {
        int lastIndex = original.lastIndexOf(target);

        if (lastIndex == -1) {
            // Target substring not found
            return original;
        }

        String before = original.substring(0, lastIndex);
        String after = original.substring(lastIndex + target.length());

        return after;
    }


}
