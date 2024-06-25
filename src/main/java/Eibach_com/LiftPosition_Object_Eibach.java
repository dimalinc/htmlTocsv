package Eibach_com;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LiftPosition_Object_Eibach {

    String liftRegex = "[+|-]{0,1}[0-9]{1}[.]{0,1}[0-9]{0,2}[-]{0,1}[0-9]{1}[.]{0,1}[0-9]{0,2}[\"]{0,1}[ ]{0,1}[i]{0,1}[n]{0,1}";
    String liftDashRegex =
            "[+|-]{0,1}[0-9]{1}[.]{0,1}[0-9]{0,2}[-]{1}[0-9]{1}[.]{0,1}[0-9]{0,2}";
    String driveRegex="[a-zA-Z0-9]{1}[W][D]";
    Pattern liftPattern = Pattern.compile("[+|-]{0,1}[0-9]{1}[.]{0,1}[0-9]{0,2}[+|-]{0,1}[0-9]{1}[.]{0,1}[0-9]{0,2}[\"]{0,1}[ ]{0,1}[i]{0,1}[n]{0,1}");
    Pattern liftDashPattern = Pattern.compile(liftDashRegex);
    Pattern drivePattern = Pattern.compile("[a-zA-Z0-9]{1}[W][D]");
    Matcher liftMatcher;   Matcher driveMatcher;
    Matcher liftDashMatcher;
    Item_DataProcssed_Eibach item_dataProcssed_eibach;
    HashSet<String> positionHashSet=new HashSet<>();
     HashSet<String> driveHashSet =new HashSet<>();
    HashSet<String> pre_LiftHashSet =new HashSet<>(); HashSet<String> final_LiftHashSet=new HashSet<>();
    String frontLiftStartString; String frontLiftFinishString;
    String rearLiftStartString; String rearLiftFinishString;
    double frontLiftStartDouble=-100.0; double frontLiftFinishDouble=-100.0;
    double rearLiftStartDouble=-100.0; double rearLiftFinishDouble=-100.0;
    ArrayList<Double> frontLiftRange = new ArrayList<>(); ArrayList<Double> rearLiftRange = new ArrayList<>();
    ArrayList<Double> finalLiftRangeArrayList = new ArrayList<>();

    public LiftPosition_Object_Eibach(Item_DataProcssed_Eibach item_dataProcssed_eibach) {
        this.item_dataProcssed_eibach=item_dataProcssed_eibach;

        positionProcessing();
        driveProcessing();
        liftProcessing();

    }

    public void positionProcessing() {
        ArrayList<String> positionOptionsArrayList = new ArrayList<>();
        positionOptionsArrayList.add("Front");
        positionOptionsArrayList.add("Rear");

        for (String pos:positionOptionsArrayList)
        if (item_dataProcssed_eibach.liftAndFitmentAttribsString.contains(pos)) positionHashSet.add(pos);

        if (positionHashSet.size() > 0) System.out.println("positionHashSet = " + positionHashSet);
    }

    public void driveProcessing() {
        for (String carAttributeString : item_dataProcssed_eibach.carAttributesArrayList) {

           /* if (s.contains("2WD") || (s.contains("4WD")) || (s.contains("FWD")) || (s.contains("RWD")) || (s.contains("AWD")))
                pre_driveArrayList.add(s);*/
            /*if (s.contains("2WD") ) final_DriveArrayList.add("2WD");
            if (s.contains("4WD") ) final_DriveArrayList.add("4WD");
            if (s.contains("FWD") ) final_DriveArrayList.add("FWD");
            if (s.contains("RWD") ) final_DriveArrayList.add("RWD");
            if (s.contains("AWD") ) final_DriveArrayList.add("AWD");*/


           /* driveMatcher = drivePattern.matcher(carAttributeString);
            if (driveMatcher.find()) {
                pre_DriveHashSet.add(carAttributeString);
            }*/


            for (String carAttributeStringSplit : carAttributeString.split(" ")) {
                driveMatcher = drivePattern.matcher(carAttributeStringSplit);
                while (driveMatcher.find()) {
                    driveHashSet.add(driveMatcher.group());
                }
            }

            if (driveHashSet.size()>0) System.out.println("final_DriveHashSet = "+ driveHashSet);


        }
    }

    public void liftProcessing() {

        if (item_dataProcssed_eibach.liftAndFitmentAttribsString.length()>0) {
            System.out.println("Lift processing -------");
           if (item_dataProcssed_eibach.liftAndFitmentAttribsString.length() > 1 )
            System.out.println("liftAndFitmentAttribsString = " + item_dataProcssed_eibach.liftAndFitmentAttribsString);
        }

        if (item_dataProcssed_eibach.liftAndFitmentAttribsString.contains("\"") || (item_dataProcssed_eibach.liftAndFitmentAttribsString.contains(" in"))) {
            for (String splitString : item_dataProcssed_eibach.fitmentAttributesArrayList) {
                liftMatcher = liftPattern.matcher(splitString);
                while (liftMatcher.find()) {
                 //   pre_LiftHashSet.add(liftMatcher.group());
                    pre_LiftHashSet.add(splitString);
                }
            }
            if (pre_LiftHashSet.size() > 0) {
                System.out.println("pre_LiftHashSet = " + pre_LiftHashSet);
                System.out.println("Lift processing finished -----------");
                System.out.println(" ");
            }
        }

        // pre_LiftHashSet processing
        for (String preLiftString:pre_LiftHashSet) {

                 liftMatcher = liftPattern.matcher(preLiftString);

                 String liftStart; String liftFinish;

                 if (liftMatcher.find()) {
                     String liftNumberString=preLiftString.replace("in","").replace("\"","").replace("Front","").replace("Rear","").replace("RHA","").replace("+ ","+").replace(" to ","-").replace(" TO ","-").replace("-+","-").replace("'","").replace("+0.2.0","+0.0-2.0").replace("+0.2.2","+0.0-2.2").replace("+0.1.5","+0.0-1.5").replace(" - ","-");
                     liftDashMatcher = liftDashPattern.matcher(liftNumberString);

                     // split by -
                    if ( liftDashMatcher.find() ) {
                        liftStart = liftNumberString.split("-")[0];
                        liftFinish = liftNumberString.split("-")[1];
                    }
                    else {
                        liftStart = liftNumberString;
                        liftFinish = liftNumberString;
                    }

                   /* System.out.println("liftStart = "+liftStart);
                    System.out.println("liftFinish = "+liftFinish);*/


                    if ( preLiftString.contains("Front") )
                    {
                        frontLiftStartString=liftStart;
                        frontLiftFinishString=liftFinish;
                    }
                    if ( preLiftString.contains("Rear") )
                    {
                        rearLiftStartString=liftStart;
                        rearLiftFinishString=liftFinish;
                    }

                }

            if ( !(frontLiftStartString==null) ) frontLiftStartDouble=Double.parseDouble(frontLiftStartString);
            if ( !(frontLiftFinishString==null) ) frontLiftFinishDouble=Double.parseDouble(frontLiftFinishString);
            if ( !(rearLiftStartString==null) ) rearLiftStartDouble=Double.parseDouble(rearLiftStartString);
            if ( !(rearLiftFinishString==null) ) rearLiftFinishDouble=Double.parseDouble(rearLiftFinishString);

             System.out.println("frontLiftStartDouble "+frontLiftStartDouble );
             System.out.println("frontLiftFinishDouble "+frontLiftFinishDouble );
             System.out.println("rearLiftStartDouble "+rearLiftStartDouble );
             System.out.println("rearLiftFinishDouble "+rearLiftFinishDouble );

        }

        generateLiftRange(frontLiftStartDouble,frontLiftFinishDouble,frontLiftRange);
        generateLiftRange(rearLiftStartDouble,rearLiftFinishDouble,rearLiftRange);
        generateFinalLiftRange(frontLiftRange,rearLiftRange,finalLiftRangeArrayList);
        System.out.println(finalLiftRangeArrayList);


    }

    public ArrayList<Double> generateLiftRange(Double liftStart, Double liftFinish, ArrayList<Double> resultArrayList) {
       if ( (liftStart!=-100.0) || (liftFinish!=-100.0) )
        for (double d = liftStart; d <= liftFinish; d+=0.25) {
            resultArrayList.add(d);
          //  System.out.print(d+" ");
        }
      //  System.out.println();
        return resultArrayList;
    }

    public ArrayList<Double> generateFinalLiftRange(ArrayList<Double> frontLiftRange, ArrayList<Double> rearLiftRange, ArrayList<Double> resultLiftRangeArrayList) {
        if(frontLiftRange!=null) resultLiftRangeArrayList.addAll(frontLiftRange);
        if(rearLiftRange!=null) resultLiftRangeArrayList.addAll(rearLiftRange);
        return resultLiftRangeArrayList;
    }



}
