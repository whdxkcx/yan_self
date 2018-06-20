package com.yan.packageAmountCalculate.controller;

import com.yan.core.annotation.MapperInject;
import com.yan.core.controller.BaseController;
import com.yan.packageAmountCalculate.mapper.PerformanceMapper;
import com.yan.packageAmountCalculate.model.Performance;
import com.yan.tool.excel.util.ReadExcelUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
@RequestMapping("/package")
public class EntryFileController extends BaseController {

    @MapperInject(PerformanceMapper.class)
    private PerformanceMapper mapper;

    @RequestMapping("/fileUploadIndex")
    public String fileUploadIndex(){
        return  "/package/fileUpload";
    }
    @RequestMapping("/fileUpload")
    public void fileUpload(){
        String filePath="E:\\实验室学习资料\\资料\\移动项目\\"+getRequest().getParameter("filePath");

        System.out.println("访问成功");
        System.out.println(filePath);
        ArrayList<ArrayList<String>> list= ReadExcelUtils.readExcelFilePath(filePath);
        for(ArrayList<String> valList:list){
            if(valList.get(3).equals("套餐金额")) continue;
            Performance performance=new Performance();
            performance.setAgentId(valList.get(4));
            performance.setPackagetype(valList.get(2));
            performance.setPackageamount(Integer.parseInt(valList.get(3)));
            performance.setMonth(valList.get(6));
            if(mapper==null) System.out.println("mapper is null");
            mapper.insert(performance);
        }
     }

}
