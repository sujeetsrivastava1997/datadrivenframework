package lib;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelDataConfig {
    XSSFWorkbook wb;

    public ExcelDataConfig(String excelpath) {
        try {
            FileInputStream fis = new FileInputStream(excelpath);
            wb = new XSSFWorkbook(fis);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getData(int sheetNumber, int row, int column) {
        XSSFSheet sheet1 = wb.getSheetAt(sheetNumber);
        if (sheet1.getRow(row).getCell(column).getCellType() == CellType.STRING)
            return sheet1.getRow(row).getCell(column).getStringCellValue();
        else if (sheet1.getRow(row).getCell(column).getCellType() == CellType.NUMERIC)
            return String.valueOf(sheet1.getRow(row).getCell(column).getNumericCellValue());
        else
            throw new RuntimeException("no value found");
    }
    public int getRowCount(int sheetIndex) {
        int row = wb.getSheetAt(sheetIndex).getLastRowNum();
        row = row + 1;
        return row;
    }
}
