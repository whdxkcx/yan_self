package com.yan.packageAmountCalculate.controller;

import com.yan.core.annotation.MapperInject;
import com.yan.core.controller.BaseController;
import com.yan.packageAmountCalculate.mapper.PerformanceMapper;
import com.yan.packageAmountCalculate.model.Performance;
import com.yan.performanceAmountQuery.mapper.PerformanceamountMapper;
import com.yan.performanceAmountQuery.model.Performanceamount;
import com.yan.performanceAmountQuery.model.PerformanceamountKey;
import com.yan.tool.excel.util.ReadExcelUtils;
import com.yan.tool.packageCalculate.PackageAmountCalculate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

@Controller
@RequestMapping("/package")
public class EntryFileController extends BaseController {


    //录入的表格对应的mapper。
    @MapperInject(PerformanceMapper.class)
    private PerformanceMapper mapper;


    //存储个人应该获得的报酬的mapper
    @MapperInject(PerformanceamountMapper.class)
    private PerformanceamountMapper aMapper;


    @RequestMapping("/fileUploadIndex")
    public String fileUploadIndex(){
        return  "/package/fileUpload";
    }
    @RequestMapping("/deleteFileIndex")
    public String fileDelete(){
        return "/package/delete";
    }

    @RequestMapping("/fileUpload")
    public void fileUpload(HttpServletRequest request){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String file2=this.getSessionUser().getUserName()+sdf.format(new Date())+".txt";
        try{
              //创建一个通用的多部分解析器
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            // 判断 request 是否有文件上传,即多部分请求
            if (multipartResolver.isMultipart(request)){
                // 转换成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 取得request中的所有文件名
                Iterator<String> iterator = multiRequest.getFileNames();
                while(iterator.hasNext()){
                    // 通过<input>标签中的name取得上传文件，注意不能在iterator.hasNext()之前两次iterator.next()，否则越界报错
                    String name = iterator.next();
                    System.out.println(name);
                    MultipartFile file=multiRequest.getFile(name);
                    if (file != null) {
                        String originalFileName = file.getOriginalFilename();// 获取当前上传文件的文件名称
                        // 如果名称不为"",说明该文件存在，否则说明该文件不存在
                        if ("" != originalFileName.trim()) {

                            //解析Excel
                            ArrayList<ArrayList<String>> list = ReadExcelUtils.readExcelInputStream(file.getInputStream(), originalFileName);
                            System.out.println("excel解析结果：");
//                            System.out.println(list);

                            for(ArrayList<String> valList:list){
                                if(valList==null||valList.size()<5) continue;
                                System.out.println("list.size:"+list.size());
                                if(valList.get(3).equals("套餐金额")) continue;
                                String agentName=valList.get(4);
                                String packageType=valList.get(2);
                                int packageAmount=Integer.parseInt(valList.get(3));
                                String month=valList.get(6);
                               //把表格数据存入数据库
                                ecxcelInsert(agentName,packageType,packageAmount,month,"");
                                //把对应的报酬存入数据;
                               AmountCalculate(agentName,packageType,packageAmount,month,"");
                            }
                            }
                        }
                    }
            }
        }catch (IllegalStateException | IOException e) {
             e.printStackTrace();
        }
     }


     //把表格数据存入数据库
    public void ecxcelInsert(String agentName,String packageType,int packageAmount,String month,String year){


        //完成数据的格式转换
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
        if(year.equals(""))
            year=sdf.format(new Date());

        //把表格数据存入数据库
        Performance performance=new Performance();
        performance.setAgentId(agentName);
        performance.setPackagetype(packageType);
        performance.setPackageamount(packageAmount);
        performance.setMonth(month);
        performance.setYear(year);
        if(mapper==null) System.out.println("mapper is null");
        mapper.insert(performance);
    }

    //把对应的报酬存入数据库
     public void AmountCalculate(String agentName,String packageType,int packageAmount,String month,String year){
         Performanceamount performanceamount;
         PerformanceamountKey performanceamountKey=new PerformanceamountKey();
         PackageAmountCalculate packageAmountCalculate=new PackageAmountCalculate();

        //完成数据的格式转换
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
         if(year.equals(""))
             year=sdf.format(new Date());
         int realMonth=Integer.parseInt(month);
         int realYear=Integer.parseInt(year);
         if(realMonth<1||realMonth>12){
             //抛出异常
         }

         //计算当前套餐的收益
         ArrayList<Double> alist=packageAmountCalculate.packageAmoutCalculate(packageType,packageAmount);

         //把当前套餐的收益依次添加到每个月中
         for(double val:alist){
             if(realMonth<12) realMonth++;
             else{
                 realMonth=1;
                 realYear++;
             }
             performanceamountKey.setAgentname(agentName);
             performanceamountKey.setMonth(String.valueOf(realMonth));
             performanceamountKey.setYear(String.valueOf(realYear));
             performanceamount=aMapper.selectByPrimaryKey(performanceamountKey);
             //如果当前数据不存在，则插入一条数据。
             if(performanceamount==null){
                 performanceamount=new Performanceamount();
                 performanceamount.setAgentname(agentName);
                 performanceamount.setMonth(String.valueOf(realMonth));
                 performanceamount.setYear(String.valueOf(realYear));
                 performanceamount.setAmount(val);
                 aMapper.insert(performanceamount);
             }else{
                 //若存在更新数据会即可。
                 performanceamount.setAmount(performanceamount.getAmount()+val);
                 aMapper.updateByPrimaryKey(performanceamount);
             }
         }
    }



    @RequestMapping("/deleteFile")
    public ModelAndView  deleteExcelFile(HttpServletRequest request){
        try{
            //创建一个通用的多部分解析器
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            // 判断 request 是否有文件上传,即多部分请求
            if (multipartResolver.isMultipart(request)) {
                // 转换成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 取得request中的所有文件名
                Iterator<String> iterator = multiRequest.getFileNames();
                while(iterator.hasNext()) {
                    // 通过<input>标签中的name取得上传文件，注意不能在iterator.hasNext()之前两次iterator.next()，否则越界报错
                    String name = iterator.next();
                    System.out.println("delete  "+name);
                    MultipartFile file = multiRequest.getFile(name);
                    if (file != null) {
                        String originalFileName = file.getOriginalFilename();// 获取当前上传文件的文件名称
                        // 如果名称不为"",说明该文件存在，否则说明该文件不存在
                        if ("" != originalFileName.trim()) {

                            //解析Excel
                            ArrayList<ArrayList<String>> list = ReadExcelUtils.readExcelInputStream(file.getInputStream(), originalFileName);
                            System.out.println("excel解析结果：");
                            for(ArrayList<String> valList:list){
                                if(valList==null||valList.size()<6) continue;
                                if(valList.get(3).equals("套餐金额")) continue;
                                String agentName=valList.get(4);
                                String packageType=valList.get(2);
                                int packageAmount=Integer.parseInt(valList.get(3));
                                String month=valList.get(6);
                                AmountDelete(agentName,packageType,packageAmount,month,"");
                            }
                        }
                    }
                }


            }
        }catch (Exception e){
                 e.printStackTrace();
        }

        ModelAndView  modelAndView=new ModelAndView();
        modelAndView.setViewName("/package/delete");
        return modelAndView;
    }


    //删除文件对应的金额加成。
    public void AmountDelete(String agentName,String packageType,int packageAmount,String month,String year){
        Performanceamount performanceamount;
        PerformanceamountKey performanceamountKey=new PerformanceamountKey();
        PackageAmountCalculate packageAmountCalculate=new PackageAmountCalculate();

        //完成数据的格式转换
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
        if(year.equals(""))
            year=sdf.format(new Date());
        int realMonth=Integer.parseInt(month);
        int realYear=Integer.parseInt(year);
        if(realMonth<1||realMonth>12){
            //抛出异常
            return;
        }

        //计算当前套餐的收益
        ArrayList<Double> alist=packageAmountCalculate.packageAmoutCalculate(packageType,packageAmount);

        //把当前套餐的收益依次添加到每个月中
        for(double val:alist){
            if(realMonth<12) realMonth++;
            else{
                realMonth=1;
                realYear++;
            }
            performanceamountKey.setAgentname(agentName);
            performanceamountKey.setMonth(String.valueOf(realMonth));
            performanceamountKey.setYear(String.valueOf(realYear));
            performanceamount=aMapper.selectByPrimaryKey(performanceamountKey);
            //如果当前数据不存在，则跳过。
            if(performanceamount==null){
                continue;
            }else{
                //若存在更新数据会即可。
                performanceamount.setAmount(performanceamount.getAmount()-val);
                aMapper.updateByPrimaryKey(performanceamount);
            }
        }
    }


}
