package Test;

import com.yan.core.annotation.MapperInject;
import com.yan.packageAmountCalculate.controller.EntryFileController;
import com.yan.packageAmountCalculate.mapper.PerformanceMapper;
import com.yan.packageAmountCalculate.model.Performance;
import com.yan.tool.excel.util.ReadExcelUtils;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

public class TestExcel {



    public static void main(String[] args){

        String filePath="E:\\实验室学习资料\\资料\\移动项目\\录入表格.xlsx";
        EntryFileController efc=new EntryFileController();
        efc.fileUpload(filePath);

    }
}
