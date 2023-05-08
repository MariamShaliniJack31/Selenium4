package dataDrivenFW;

public class XLDemo {

	public XLDemo() throws Exception {
		ExcelUtils dd = new ExcelUtils ("C:\\Users\\mrufu\\OneDrive\\Desktop\\DataDrivenXL.xlsx","Sheet3");
		System.out.println("The Row count is " + dd.excel_get_rows());
		
		dd.getCellDataasnumber(0,0);
		dd.getCellDataasstring(0,1);
	    dd.getCellDataasnumber(0,2);
	    dd.getCellDataasnumber(1,0);
	    dd.getCellDataasstring(1,1);
	    dd.getCellDataasnumber(1,2);
	    dd.getCellDataasnumber(2,0);
	    dd.getCellDataasstring(2,1);
	    dd.getCellDataasnumber(2,2);
	}
}
