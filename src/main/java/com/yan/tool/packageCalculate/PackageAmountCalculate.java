package com.yan.tool.packageCalculate;

import java.util.ArrayList;

public class PackageAmountCalculate {



    public ArrayList<Double>  packageAmoutCalculate(String packageType,int packageAmount){
        ArrayList<Double>  list=new ArrayList<>();
       if(packageType.equals("专线"))
           list=privateLinePackage(packageAmount);
       else if(packageType.equals("酒店"))
           list=hotelPackage(packageAmount);
       else if(packageType.equals("商动"))
           list=commercePackage(packageAmount);
       else{
           //抛出异常
       }
        return list;
    }



    public ArrayList<Double>  privateLinePackage(int packageAmount){
        ArrayList<Double>  list=new ArrayList<>();
        double firstMonth=packageAmount*1.0;
        double secondMonth=packageAmount*0.15;
        double thirdMonth=packageAmount*0.15;
        double fourthMonth=packageAmount*0.15;
        double fifthMonth=packageAmount*0.15;
        double sixthMonth=packageAmount*0.15;
        list.add(fifthMonth);
        list.add(secondMonth);
        list.add(thirdMonth);
        list.add(fourthMonth);
        list.add(fifthMonth);
        list.add(sixthMonth);
        return list;
    }



    public ArrayList<Double>  hotelPackage(int packageAmount){
        ArrayList<Double>  list=new ArrayList<>();
        double firstMonth=0.0;
        double secondMonth=packageAmount*1.0;
        double thirdMonth=packageAmount*0.2;
        double fourthMonth=packageAmount*0.2;
        double fifthMonth=packageAmount*0.2;
        double sixthMonth=packageAmount*0.2;
        double seventhMonth=packageAmount*0.2;
        list.add(fifthMonth);
        list.add(secondMonth);
        list.add(thirdMonth);
        list.add(fourthMonth);
        list.add(fifthMonth);
        list.add(sixthMonth);
        list.add(seventhMonth);
        return list;
    }




    public ArrayList<Double>  commercePackage(int packageAmount){
        ArrayList<Double>  list=new ArrayList<>();
        double firstMonth=packageAmount*0.75+packageAmount*0.15;
        double secondMonth=packageAmount*0.15+packageAmount*0.15;
        double thirdMonth=packageAmount*0.15+packageAmount*0.15;
        list.add(firstMonth);
        list.add(secondMonth);
        list.add(thirdMonth);
        return list;
    }

}
