package Eibach_com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Item_DataProcssed_Eibach {
    Item_XpathParsed_Eibach item_xpathParsed_eibach;
    LiftPosition_Object_Eibach liftPosition_object_eibach;

    String liftAndFitmentAttribsString;
    ArrayList<String> carAttributesArrayList=new ArrayList<>();
    ArrayList<String> fitmentAttributesArrayList=new ArrayList<>();
    ArrayList<String> pre_driveArrayList=new ArrayList<>();
    ArrayList<String> final_DriveArrayList=new ArrayList<>();
    ArrayList<String> final_PositionArrayList=new ArrayList<>();
    ArrayList<String> pre_LiftArrayList=new ArrayList<>();

    String liftStart_front_String;     String liftFinish_rear_String;
    String liftStart_rear_String;     String liftFinish_front_String;
    String position_String;

    String drive_String;

    public Item_DataProcssed_Eibach(Item_XpathParsed_Eibach item_xpathParsed_eibach) {
        this.item_xpathParsed_eibach = item_xpathParsed_eibach;
        liftAndFitmentAttribsString = item_xpathParsed_eibach.liftAndFitmentAttribs;


        Collections.addAll(carAttributesArrayList,item_xpathParsed_eibach.carDetails.split("\\|"));
        Collections.addAll(fitmentAttributesArrayList,item_xpathParsed_eibach.liftAndFitmentAttribs.split("\\|"));

       // if (carAttributesArrayList.size()>0) System.out.println("carAttributesArrayList = "+carAttributesArrayList);
       // if (fitmentAttributesArrayList.size()>1) System.out.println("fitmentAttributesArrayList = "+fitmentAttributesArrayList);

        liftPosition_object_eibach=new LiftPosition_Object_Eibach(this);

        /*driveProcessing();
        positionProcessing();
        liftProcessing();*/

    }

    public void liftProcessing() {
        Pattern liftPattern=Pattern.compile( "[+|-]{0,1}[0-9]{1}[.]{0,1}[0-9]{0,2}[+|-]{0,1}[0-9]{1}[.]{0,1}[0-9]{0,2}[\"]{0,1}[ ]{0,1}[i]{0,1}[n]{0,1}");
        Matcher liftMatcher;

        if (liftAndFitmentAttribsString!=null)
        System.out.println("liftAndFitmentAttribsString = " +liftAndFitmentAttribsString);
        if  (liftAndFitmentAttribsString.contains("\"") || (liftAndFitmentAttribsString.contains(" in")) ) {
            for (String splitString:fitmentAttributesArrayList) {
                liftMatcher = liftPattern.matcher(splitString);
                while (liftMatcher.find()) {
                    pre_LiftArrayList.add(liftMatcher.group());
                  //  System.out.println("liftAndFitmentAttribs splitString = " + splitString);
                  //  System.out.println("liftMatcher.group() = " + liftMatcher.group());
                }
            }
          //  if (pre_LiftArrayList.size()>0)  System.out.println("pre_LiftArrayList = " + pre_LiftArrayList);
        }

    }

    public void positionProcessing() {
       if (liftAndFitmentAttribsString.contains("Front") ) final_PositionArrayList.add("Front");
       if (liftAndFitmentAttribsString.contains("Rear") ) final_PositionArrayList.add("Rear");

      // if (final_PositionArrayList.size()>0) System.out.println("final_PositionArrayList = "+final_PositionArrayList);
    }

    public void driveProcessing() {


        for (String carAttributeString:carAttributesArrayList) {

           /* if (s.contains("2WD") || (s.contains("4WD")) || (s.contains("FWD")) || (s.contains("RWD")) || (s.contains("AWD")))
                pre_driveArrayList.add(s);*/
            /*if (s.contains("2WD") ) final_DriveArrayList.add("2WD");
            if (s.contains("4WD") ) final_DriveArrayList.add("4WD");
            if (s.contains("FWD") ) final_DriveArrayList.add("FWD");
            if (s.contains("RWD") ) final_DriveArrayList.add("RWD");
            if (s.contains("AWD") ) final_DriveArrayList.add("AWD");*/


            Pattern drivePattern=Pattern.compile( "[a-zA-Z0-9]{1}[W][D]");
            Matcher driveMatcher;
            driveMatcher=drivePattern.matcher(carAttributeString);

            if (driveMatcher.find()) {
                pre_driveArrayList.add(carAttributeString);
             //   System.out.println("regex matched s = "+carAttributeString);
            }
         /*   System.out.println("pre_driveArrayList = "+pre_driveArrayList);
            System.out.println("---");
            System.out.println("s.split(\" \") = "+ Arrays.toString(carAttributeString.split(" ")));
*/


            for (String carAttributeStringSplit: carAttributeString.split(" ")) {
                driveMatcher=drivePattern.matcher(carAttributeStringSplit);

                while  (driveMatcher.find()) {
                   // System.out.println("driveMatcher.groupCount() = "+driveMatcher.groupCount());
                 //   final_DriveArrayList.add(carAttributeStringSplit);
                 //   System.out.println("driveMatcher.group()" + driveMatcher.group());
                        final_DriveArrayList.add(driveMatcher.group());

                   /* MatchResult result=driveMatcher.toMatchResult();
                    System.out.println("MatchResult result = "+result);
                    System.out.println("MatchResult groupCount = "+result.groupCount());
                    System.out.println("MatchResult  group()= "+result.group());*/

                 //   System.out.println("regex matched SS = "+carAttributeStringSplit);
                }
            }
          //  System.out.println("final_DriveArrayList = "+final_DriveArrayList);

            // https://www.baeldung.com/java-regex-text-after-match

        }






    }
}
