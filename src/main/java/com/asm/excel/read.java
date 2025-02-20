//package com.asm.excel;
// 
//import java.awt.print.Book;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
// 
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.BorderStyle;
//import org.apache.poi.ss.usermodel.BuiltinFormats;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.DataFormat;
//import org.apache.poi.ss.usermodel.FillPatternType;
//import org.apache.poi.ss.usermodel.Font;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.util.CellReference;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import com.asm.DAO.userDAO;
//import com.asm.bean.User;
//@SpringBootApplication
//public class read {
//	@Autowired
//    static userDAO userdao;
//    public static final int COLUMN_INDEX_ID         = 0;
//    public static final int COLUMN_INDEX_USERNAME     = 1;
//    public static final int COLUMN_INDEX_PASSWORD    = 2;
//    public static final int COLUMN_INDEX_EMAIL   = 3;
//    public static final int COLUMN_INDEX_ROLE     = 4;
//    public static final int COLUMN_INDEX_TOTAL      = 5;
//
//    private static CellStyle cellStyleFormatNumber = null;
//     
////    public static void main(String[] args) throws IOException {
////        final List<User> users = getUser();
////        final String excelFilePath = "C:\\Users\\ASUS\\OneDrive\\Tài liệu";
////        writeExcel(users, excelFilePath);
////    }
// 
//    public static List<User> readExcel(String excelFilePath) throws IOException {
//        List<User> users = new ArrayList<>();
// 
//        // Get file
//        InputStream inputStream = new FileInputStream(new File(excelFilePath));
// 
//        // Get workbook
//        Workbook workbook = getWorkbook(inputStream, excelFilePath);
// 
//        // Get sheet
//        Sheet sheet = workbook.getSheetAt(0);
// 
//        // Get all rows
//        Iterator<Row> iterator = sheet.iterator();
//        while (iterator.hasNext()) {
//            Row nextRow = iterator.next();
//            if (nextRow.getRowNum() == 0) {
//                // Ignore header
//                continue;
//            }
// 
//            // Get all cells
//            Iterator<Cell> cellIterator = nextRow.cellIterator();
// 
//            // Read cells and set value for book object
//            Book book = new Book();
//            while (cellIterator.hasNext()) {
//                //Read cell
//                Cell cell = cellIterator.next();
//                Object cellValue = getCellValue(cell);
//                if (cellValue == null || cellValue.toString().isEmpty()) {
//                    continue;
//                }
//                // Set value for book object
//                int columnIndex = cell.getColumnIndex();
//                switch (columnIndex) {
//                case COLUMN_INDEX_ID:
//                    book.setId(new BigDecimal((double) cellValue).intValue());
//                    break;
//                case COLUMN_INDEX_TITLE:
//                    book.setTitle((String) getCellValue(cell));
//                    break;
//                case COLUMN_INDEX_QUANTITY:
//                    book.setQuantity(new BigDecimal((double) cellValue).intValue());
//                    break;
//                case COLUMN_INDEX_PRICE:
//                    book.setPrice((Double) getCellValue(cell));
//                    break;
//                case COLUMN_INDEX_TOTAL:
//                    book.setTotalMoney((Double) getCellValue(cell));
//                    break;
//                default:
//                    break;
//                }
// 
//            }
//            listBooks.add(book);
//        }
// 
//        workbook.close();
//        inputStream.close();
// 
//        return listBooks;
//    }
// 
//    // Get Workbook
//    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
//        Workbook workbook = null;
//        if (excelFilePath.endsWith("xlsx")) {
//            workbook = new XSSFWorkbook(inputStream);
//        } else if (excelFilePath.endsWith("xls")) {
//            workbook = new HSSFWorkbook(inputStream);
//        } else {
//            throw new IllegalArgumentException("The specified file is not Excel file");
//        }
// 
//        return workbook;
//    }
// 
//    // Get cell value
//    private static Object getCellValue(Cell cell) {
//        CellType cellType = cell.getCellTypeEnum();
//        Object cellValue = null;
//        switch (cellType) {
//        case BOOLEAN:
//            cellValue = cell.getBooleanCellValue();
//            break;
//        case FORMULA:
//            Workbook workbook = cell.getSheet().getWorkbook();
//            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
//            cellValue = evaluator.evaluate(cell).getNumberValue();
//            break;
//        case NUMERIC:
//            cellValue = cell.getNumericCellValue();
//            break;
//        case STRING:
//            cellValue = cell.getStringCellValue();
//            break;
//        case _NONE:
//        case BLANK:
//        case ERROR:
//            break;
//        default:
//            break;
//        }
// 
//        return cellValue;
//    }
//}