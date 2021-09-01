package Utilities;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestUtil {

	public static HashMap<String, String> storeValues = new HashMap<String, String>();

	public static List<HashMap<String, String>> readExcelDatafromFile(String filePath, String sheetName) {

		// create Java List to store Hashmaps
		List<HashMap<String, String>> excelData = new ArrayList();

		try {
			FileInputStream fs = new FileInputStream(filePath);
			XSSFWorkbook wb = new XSSFWorkbook(fs);
			XSSFSheet sheet = wb.getSheet("Credentials");

			Row HeaderRow = sheet.getRow(0);

			// for(int r = 1; r<=sheet.getPhysicalNumberOfRows();r++){
			for (int r = 1; r <= 3; r++) {
				Row CurrentRow = sheet.getRow(r);
				// each row of data is stored in new hashmap
				HashMap<String, String> currentRowMap = new HashMap<String, String>();

				// for(int c=0;c<CurrentRow.getPhysicalNumberOfCells();c++){
				for (int c = 0; c < 2; c++) {
					Cell CurrentCell = CurrentRow.getCell(c);
					System.out.print(CurrentCell.getStringCellValue() + "\t");
					currentRowMap.put(HeaderRow.getCell(c).getStringCellValue(), CurrentCell.getStringCellValue());
				}
				excelData.add(currentRowMap);
			}
			wb.close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return excelData;

	}
}