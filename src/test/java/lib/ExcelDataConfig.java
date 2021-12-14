package lib;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelDataConfig {

    //Making Global Variable for our workbook//
    XSSFWorkbook wb;

    //Accepting the Excel path through parameter and loading the Excel file//
    public ExcelDataConfig(String excelpath) {
        try {
            FileInputStream fis = new FileInputStream(excelpath);
            wb = new XSSFWorkbook(fis);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Fetching data from Excel file//
    public String getData(int sheetNumber, int row, int column) {
        XSSFSheet sheet1 = wb.getSheetAt(sheetNumber);
        if (sheet1.getRow(row).getCell(column).getCellType() == CellType.STRING)
            return sheet1.getRow(row).getCell(column).getStringCellValue();
        else if (sheet1.getRow(row).getCell(column).getCellType() == CellType.NUMERIC)
            return String.valueOf(sheet1.getRow(row).getCell(column).getNumericCellValue());
        else
            throw new RuntimeException("no value found");
    }

    //Reading number of rows in Excel file//
    public int getRowCount(int sheetIndex) {
        int row = wb.getSheetAt(sheetIndex).getLastRowNum();
        row = row + 1;
        return row;
    }
}
