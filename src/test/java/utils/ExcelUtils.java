package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ExcelUtils {
	public static void excel( String PrincipalAmount,String InterestAmount,List<String> Heaader, List<String> Data) throws IOException {
		FileOutputStream file=new FileOutputStream(System.getProperty("user.dir")+"\\Excel\\Hackathon.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook();
		
		//Storing Employee Name and ID
		XSSFSheet sheet1=workbook.createSheet("UserInfo");
		XSSFRow row=sheet1.createRow(0);
		
		row.createCell(0).setCellValue("Principal Amount:");
		row.createCell(1).setCellValue(PrincipalAmount);
		XSSFRow row1=sheet1.createRow(1);
		row1.createCell(0).setCellValue("Interest Amount:");
		row1.createCell(1).setCellValue(InterestAmount);
		
		

		XSSFRow row3=sheet1.createRow(3);
		row3.createCell(0).setCellValue("Home Loan Table");
		
		XSSFRow row4=sheet1.createRow(4);
		for(int i=0;i<Heaader.size();i++) {
				row4.createCell(i).setCellValue(Heaader.get(i));
		}
		int z=0;
		int x=4;
		int y=0;
		XSSFRow row5 = null;
		
		while(z<Data.size()) {
			if(z%7==0) {
				x=x+1;
				y=0;
				row5 = sheet1.createRow(x);
			}
			row5.createCell(y).setCellValue(Data.get(z));
			y=y+1;
			z=z+1;
		}
		
		
		
		workbook.write(file);
		workbook.close();
		file.close();
	}
}
