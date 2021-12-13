package lib;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ExcelDataConfig {
    XSSFWorkbook wb;
    XSSFSheet sheet1;

    public ExcelDataConfig(String excelpath) {
        try {
            File src = new File(excelpath);
            FileInputStream fis = new FileInputStream(src);
            wb = new XSSFWorkbook(fis);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getData(int sheetNumber, int row, int column) throws InterruptedException {
        Thread.sleep(2000);
        sheet1 = wb.getSheet(String.valueOf(sheetNumber));
        String data = sheet1.getRow(row).getCell(column).getStringCellValue();
        return data;
    }


    public int getRowCount(int sheetIndex) {
        int row = wb.getSheetAt(sheetIndex).getLastRowNum();
        row = row + 1;
        return row;
    }
}
