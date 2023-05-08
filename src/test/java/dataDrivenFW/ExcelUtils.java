package dataDrivenFW;

import java.io.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelUtils {
	
	private XSSFWorkbook ExcelWBook;
	private XSSFSheet ExcelWSheet;
	   
   	//Constructor to connect to the Excel with sheetname and Path
	public ExcelUtils(String Path, String SheetName) throws Exception {
		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);
         
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			
		} catch (Exception e) {
			throw (e);
		}
	}
    
	@Test
	//This method is to set the rowcount of the excel.
	public int excel_get_rows() throws Exception {
   
		try {
			return ExcelWSheet.getPhysicalNumberOfRows();
		} catch (Exception e) {
			throw (e);
		}
	}
   
	@Test
	//	This method to get the data and get the value as strings.
	public String getCellDataasstring(int RowNum, int ColNum) throws Exception {
   
		try {
			String CellData = ExcelWSheet.getRow(RowNum).getCell(ColNum).getStringCellValue();
			System.out.println("The value of CellData as String in " + RowNum +" and " + ColNum + " is : " + CellData);
			return CellData;
		} catch (Exception e) {
			return "Errors in Getting Cell Data";
		}
	}
   
	@Test
	//This method to get the data and get the value as number.
	public double getCellDataasnumber(int RowNum, int ColNum) throws Exception {
   
		try {
			double CellData = ExcelWSheet.getRow(RowNum).getCell(ColNum).getNumericCellValue();
			System.out.println("The value of CellData as Numeric is : " + CellData);
			return CellData;
		} catch (Exception e) {
			return 000.00;
		}
	}
	
	////Run the Class as Java Application
//	public static void main(String[] args) throws Exception {
//		ExcelUtils dd = new ExcelUtils ("C:\\Users\\mrufu\\OneDrive\\Desktop\\DataDrivenXL.xlsx","Sheet3");
//		System.out.println("The Row count is " + dd.excel_get_rows());
//
//	    dd.getCellDataasnumber(0,0);
//	    dd.getCellDataasstring(0,1);
//	    dd.getCellDataasnumber(0,2);
//	    dd.getCellDataasnumber(1,0);
//	    dd.getCellDataasstring(1,1);
//	    dd.getCellDataasnumber(1,2);
//	    dd.getCellDataasnumber(2,0);
//	    dd.getCellDataasstring(2,1);
//	    dd.getCellDataasnumber(2,2);
//	}
}