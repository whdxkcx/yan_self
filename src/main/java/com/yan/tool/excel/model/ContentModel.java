package com.yan.tool.excel.model;

public class ContentModel {

    /*描述单元格的信息*/

    private String filePath;
    private String sheetName;
    private int rowNum;
    private int cellNumOfRow;
    private String cellType;
    private String content;
    public ContentModel() {
        super();
    }
    public ContentModel(String filePath, String sheetName, int rowNum, int cellNumOfRow, String cellType, String content) {
        this.filePath = filePath;
        this.sheetName = sheetName;
        this.rowNum = rowNum;
        this.cellNumOfRow = cellNumOfRow;
        this.cellType = cellType;
        this.content = content;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getCellNumOfRow() {
        return cellNumOfRow;
    }

    public void setCellNumOfRow(int cellNumOfRow) {
        this.cellNumOfRow = cellNumOfRow;
    }

    public String getCellType() {
        return cellType;
    }

    public void setCellType(String cellType) {
        this.cellType = cellType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ContentModle{" +
                "filePath='" + filePath + '\'' +
                ", sheetName='" + sheetName + '\'' +
                ", rowNum=" + rowNum +
                ", cellNumOfRow=" + cellNumOfRow +
                ", cellType='" + cellType + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
