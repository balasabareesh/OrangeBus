package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData 
{
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public String wbpath;
	
	public void setExcelpath(String workbookpath, String worksheet)
	{
		try {
			fis = new FileInputStream(new File(workbookpath));
			wbpath = workbookpath;
			wb = new XSSFWorkbook(fis);
			ws= wb.getSheet(worksheet);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	public void setExcelpath(String workbookpath, int worksheet)
	{
		try {
			fis = new FileInputStream(new File(workbookpath));
			wbpath = workbookpath;
			wb = new XSSFWorkbook(fis);
			ws= wb.getSheetAt(worksheet);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	public String readData(int row, int column)
	{
		return ws.getRow(row).getCell(column).getRichStringCellValue().toString();
		
	}
	public void writeData(String value, int row, int column)
	{
		XSSFCell c;
		c= ws.getRow(row).createCell(column);
		c.setCellValue(value);
		try {
			fos = new FileOutputStream(new File(wbpath));
			wb.write(fos);
			fos.flush();
			fos.close();
		} 
		catch (IOException e) 
		{
			System.err.println(e.getMessage());
		}
	}

}
