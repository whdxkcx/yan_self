package com.yan.packageAmountCalculate.controller;

import com.yan.core.annotation.MapperInject;
import com.yan.core.controller.BaseController;
import com.yan.packageAmountCalculate.mapper.PerformanceMapper;
import com.yan.packageAmountCalculate.model.Performance;
import com.yan.tool.excel.util.ReadExcelUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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
                            System.out.println(list);

                            for(ArrayList<String> valList:list){
                                if(list==null||list.size()<6) continue;
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
                    }

            }
        }catch (IllegalStateException | IOException e) {
             e.printStackTrace();
        }
     }

}
