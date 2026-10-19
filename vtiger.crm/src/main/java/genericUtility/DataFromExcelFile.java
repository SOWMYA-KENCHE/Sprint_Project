package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DataFromExcelFile {

    public String getDataFromExcel(String sheetName, int rowNum, int cellNum) throws IOException {

        FileInputStream file1 = new FileInputStream("./src/test/resources/testcase.xlsx");
        Workbook workb= WorkbookFactory.create(file1);
        String data = workb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
        workb.close();
        file1.close();
        return data;
    }
} 