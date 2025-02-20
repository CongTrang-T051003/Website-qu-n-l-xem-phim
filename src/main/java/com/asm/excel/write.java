package com.asm.excel;
 
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.asm.DAO.userDAO;
import com.asm.bean.User;
@SpringBootApplication
public class write {
	@Autowired
    static userDAO userdao;
    public static final int COLUMN_INDEX_ID         = 0;
    public static final int COLUMN_INDEX_USERNAME     = 1;
    public static final int COLUMN_INDEX_PASSWORD    = 2;
    public static final int COLUMN_INDEX_EMAIL   = 3;
    public static final int COLUMN_INDEX_ROLE     = 4;
    public static final int COLUMN_INDEX_TOTAL      = 5;

    private static CellStyle cellStyleFormatNumber = null;
     
//    public static void main(String[] args) throws IOException {
//        final List<User> users = getUser();
//        final String excelFilePath = "C:\\Users\\ASUS\\OneDrive\\Tài liệu";
//        writeExcel(users, excelFilePath);
//    }
 
    public void writeExcel(List<User> users, String excelFilePath) throws IOException {
        // Create Workbook
        Workbook workbook = getWorkbook(excelFilePath);
 
        // Create sheet
        Sheet sheet = workbook.createSheet("Users"); // Create sheet with sheet name
 
        int rowIndex = 0;
         
        // Write header
        writeHeader(sheet, rowIndex);
 
        // Write data
        rowIndex++;
        for (User user : users) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeBook(user, row);
            rowIndex++;
        }
         
        // Write footer
        writeFooter(sheet, rowIndex);
 
        // Auto resize column witdth
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);
 
        // Create file excel
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }
 
    // Create dummy data
    private static List<User> getUser() {

        List<User> listuser = userdao.findAll();	

        return listuser;
    }
 
    // Create workbook
    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;
 
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
 
        return workbook;
    }
 
    // Write header with format
    private static void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);
         
        // Create row
        Row row = sheet.createRow(rowIndex);
         
        // Create cells
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Id");
 
        cell = row.createCell(COLUMN_INDEX_USERNAME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Username");
 
        cell = row.createCell(COLUMN_INDEX_PASSWORD);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Password");
 
        cell = row.createCell(COLUMN_INDEX_EMAIL);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Email");
 
        cell = row.createCell(COLUMN_INDEX_ROLE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Role");
    }
 
    // Write data
    private static void writeBook(User user, Row row) {
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short)BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");
             
            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }
         
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellValue(user.getId());
 
        cell = row.createCell((COLUMN_INDEX_USERNAME));
        cell.setCellValue(user.getUsername());
 
        cell = row.createCell((COLUMN_INDEX_PASSWORD));
        cell.setCellValue(user.getPassword());
 
        cell = row.createCell((COLUMN_INDEX_EMAIL));
        cell.setCellValue(user.getEmail());
        
        cell = row.createCell((COLUMN_INDEX_ROLE));
        cell.setCellValue(user.getRole());
         
        // Create cell formula
        // totalMoney = price * quantity
        cell = row.createCell(COLUMN_INDEX_TOTAL, CellType.FORMULA);
        int currentRow = row.getRowNum() + 1;
  
    }
 
    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman"); 
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color
 
        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }
     
    // Write footer
    private static void writeFooter(Sheet sheet, int rowIndex) {
        // Create row
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(COLUMN_INDEX_TOTAL, CellType.FORMULA);
        cell.setCellFormula("SUM(E2:E6)");
    }
     
    // Auto resize column width
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }
     
    // Create output file
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
 
}
