package com.yan.tool.excel.util;

import com.yan.tool.excel.model.ContentModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ReadExcelUtils {
        public static ArrayList<ArrayList<String>> readExcelFilePath(String filePath) {
            ArrayList<ArrayList<String>> contentList = new ArrayList<ArrayList<String>>();
            try {
                if (ReadExcelUtils.isExcel2003(filePath)) {
                    contentList = readExcel2003(new HSSFWorkbook(new FileInputStream(filePath)), filePath);

                } else if (ReadExcelUtils.isExcel2007(filePath)) {
                    contentList = readExcel2007(new XSSFWorkbook(new FileInputStream(filePath)), filePath);

                } else {

                    ArrayList<String> c = new ArrayList<>();
                    c.add("文件名：" + filePath + "，Excel文件格式错误，请更正后再尝试!");
                    contentList.add(c);
                }

            } catch (Exception e) {
                ArrayList<String> c = new ArrayList<>();
                c.add("文件名：" + filePath + "，文件路径错误，请更正后再尝试!");
                contentList.add(c);
                e.printStackTrace();
            }
            return contentList;
        }

        public static ArrayList<ArrayList<String>> readExcelInputStream(InputStream inputStream, String fileName) {
            ArrayList<ArrayList<String>> contentList = new ArrayList<ArrayList<String>>();
//        String content = "";
            try {
                if (ReadExcelUtils.isExcel2003(fileName)) {
                    contentList = readExcel2003(new HSSFWorkbook(inputStream), fileName);

                } else if (ReadExcelUtils.isExcel2007(fileName)) {
                    contentList = readExcel2007(new XSSFWorkbook(inputStream), fileName);

                } else {
                    ArrayList<String> c = new ArrayList<>();
                    c.add("文件名：" + fileName + "，Excel文件格式错误，请更正后再尝试!");
                    contentList.add(c);
                }

            } catch (Exception e) {
                ArrayList<String> c = new ArrayList<>();
                c.add("文件名：" + fileName + "，文件路径错误，请更正后再尝试!");
                contentList.add(c);
                e.printStackTrace();
            }
            return contentList;
        }

        public static ArrayList<ArrayList<String>> readExcel2003(HSSFWorkbook workbook, String fileName) throws IOException {
            ArrayList<ArrayList<String>> contentList = new ArrayList<ArrayList<String>>();
//        StringBuilder content = new StringBuilder();
//        // 创建对Excel工作簿文件的引用
//        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filePath));

            //获取一个单元格对象，用于保存每个单元格的信息
            ContentModel contentModle = new ContentModel();
            //保存文件路径
            contentModle.setFilePath(fileName);

            System.out.println("Sheet总数量：" + workbook.getNumberOfSheets());
            //遍历每个sheet
            for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) {
                //保存Sheet的名字
                contentModle.setSheetName(workbook.getSheetName(numSheets));

                if (null != workbook.getSheetAt(numSheets)) {
                    // 获得一个sheet
                    HSSFSheet aSheet = workbook.getSheetAt(numSheets);
                    //行的数量必须包含等于，否则最后一行没有读取，因为getLastRowNum()方法表示获取表单中最后一行的索引（而不是总行数）
//                System.out.println(contentModle.getSheetName() + "总行数：" + aSheet.getLastRowNum() + 1);
                    for (int rowNumOfSheet = 0; rowNumOfSheet <= aSheet.getLastRowNum(); rowNumOfSheet++) {
                        // 遍历每一行
                        if (null != aSheet.getRow(rowNumOfSheet)) {
                            // 获得一行
                            HSSFRow aRow = aSheet.getRow(rowNumOfSheet);

                            //行
                            ArrayList<String> row = new ArrayList<String>();

                            //getLastCellNum()获取此行中包含的最后一个单元格的总列数(而不是索引)
//                        System.out.print("总列数：" + aRow.getLastCellNum());
                            for (int cellNumOfRow = 0; cellNumOfRow < aRow.getLastCellNum(); cellNumOfRow++) {
                                //遍历每个单元格
                                if (null != aRow.getCell(cellNumOfRow)) {

                                    //获取单元格的值
                                    HSSFCell aCell = aRow.getCell(cellNumOfRow);

                                    //单元格行索引+1
                                    contentModle.setRowNum(aCell.getRowIndex() + 1);
                                    //单元格列索引+1
                                    contentModle.setCellNumOfRow(aCell.getColumnIndex() + 1);
                                    //单元格内容
                                    contentModle.setContent(convert(aCell));

                                } else {
                                    contentModle.setRowNum(rowNumOfSheet + 1);
                                    contentModle.setCellNumOfRow(cellNumOfRow + 1);
                                    contentModle.setContent("");
                                }
                                //单元格
                                row.add(contentModle.getContent());

//                            System.out.print("["+""+contentModle.getFilePath()+","+contentModle.getSheetName()+",("+contentModle.getRowNum()+","+contentModle.getCellNumOfRow()+")]"+contentModle.getContent());
//                            System.out.print("(" + contentModle.getRowNum() + "," + contentModle.getCellNumOfRow() + ")" + contentModle.getContent());
//                            System.out.print(" ");
//                            content.append(contentModle.getContent());
//                            content.append("\t");

                            }
                            //添加一行
                            contentList.add(row);
//                        System.out.println();
//                        content.append("\n");
                        }
                    }
                }
            }
//        return content.toString();
            return contentList;

        }

        public static ArrayList<ArrayList<String>> readExcel2007(XSSFWorkbook workbook, String fileName) throws IOException {
            ArrayList<ArrayList<String>> contentList = new ArrayList<ArrayList<String>>();
            //        StringBuilder content = new StringBuilder();
//        // 创建对Excel工作簿文件的引用
//
//        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(filePath));

            //获取一个单元格对象，用于保存每个单元格的信息
            ContentModel contentModle = new ContentModel();
            //保存文件路径
            contentModle.setFilePath(fileName);

            System.out.println("Sheet总数量：" + workbook.getNumberOfSheets());
            //遍历每个sheet
            for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) {
                //保存Sheet的名字
                contentModle.setSheetName(workbook.getSheetName(numSheets));

                if (null != workbook.getSheetAt(numSheets)) {
                    // 获得一个sheet
                    XSSFSheet aSheet = workbook.getSheetAt(numSheets);
                    //行的数量必须包含等于，否则最后一行没有读取，因为getLastRowNum()方法表示获取表单中最后一行的索引（而不是总行数）
//                System.out.println("总行数：" + aSheet.getLastRowNum());
                    for (int rowNumOfSheet = 0; rowNumOfSheet <= aSheet.getLastRowNum(); rowNumOfSheet++) {
                        // 遍历每一行
                        if (null != aSheet.getRow(rowNumOfSheet)) {
                            // 获得一行
                            XSSFRow aRow = aSheet.getRow(rowNumOfSheet);

                            //行
                            ArrayList<String> row = new ArrayList<String>();

                            //getLastCellNum()获取此行中包含的最后一个单元格的总列数(而不是索引)
//                        System.out.print("总列数：" + aRow.getLastCellNum());
                            for (int cellNumOfRow = 0; cellNumOfRow < aRow.getLastCellNum(); cellNumOfRow++) {
                                //遍历每个单元格
                                if (null != aRow.getCell(cellNumOfRow)) {

                                    //获取单元格的值
                                    XSSFCell aCell = aRow.getCell(cellNumOfRow);

                                    //单元格行索引+1
                                    contentModle.setRowNum(aCell.getRowIndex() + 1);
                                    //单元格列索引+1
                                    contentModle.setCellNumOfRow(aCell.getColumnIndex() + 1);
                                    //单元格内容
                                    contentModle.setContent(convert(aCell));

                                } else {
                                    contentModle.setRowNum(rowNumOfSheet + 1);
                                    contentModle.setCellNumOfRow(cellNumOfRow + 1);
                                    contentModle.setContent("");
                                }
                                //单元格
                                row.add(contentModle.getContent());

//                            System.out.print("["+""+contentModle.getFilePath()+","+contentModle.getSheetName()+",("+contentModle.getRowNum()+","+contentModle.getCellNumOfRow()+")]"+contentModle.getContent());
//                            System.out.print("(" + contentModle.getRowNum() + "," + contentModle.getCellNumOfRow() + ")" + contentModle.getContent());
//                            System.out.print(" ");
//                            content.append(contentModle.getContent());
//                            content.append("\t");
                            }
                            //添加一行
                            contentList.add(row);
//                        System.out.println();
//                        content.append("\n");
                        }
                    }
                }
            }

//        return content.toString();
            return contentList;

        }


        private static String convert(Cell cell) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String cellValue = "";
            if (cell == null) {
                return cellValue;
            }
            switch (cell.getCellType()) {
                //文本
                case Cell.CELL_TYPE_STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                //布尔类型
                case Cell.CELL_TYPE_BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                //数字，日期
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        //日期类型
                        cellValue = format.format(cell.getDateCellValue());
                    } else {
                        //数字类型
                        double value = cell.getNumericCellValue();
                        int intValue = (int) value;
                        cellValue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);
                    }
                    break;
                //空白
                case Cell.CELL_TYPE_BLANK:
                    cellValue = cell.getStringCellValue();
                    break;
                //公式
                case Cell.CELL_TYPE_FORMULA:
                    cellValue = cell.getCellFormula();
                    break;
                //错误
                case Cell.CELL_TYPE_ERROR:
//                cellValue = String.valueOf(cell.getErrorCellValue());
                    cellValue = "";
                    break;
                default:
                    cellValue = "";

            }
            return cellValue;
        }

        /**
         * 描述：判断是否是Excel2003，返回true表示是Excel2003
         */
        public static boolean isExcel2003(String filePath) {
            return filePath.matches("^.+\\.(?i)(xls)$");
        }

        /**
         * 描述：判断是否是Excel2007，返回true表示是Excel2007
         */
        public static boolean isExcel2007(String filePath) {
            return filePath.matches("^.+\\.(?i)(xlsx)$");
        }

        /**
         * 描述：验证是否是EXCEL文件
         */
        public static boolean validateExcel(String filePath) {
            if ((isExcel2003(filePath)) || (isExcel2007(filePath))) {
                return true;
            } else {
                return false;
            }
        }
}
