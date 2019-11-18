package co.selenium;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
 

public class Utils 
{
	
	public static XSSFSheet ExcelWSheet;
	public static XSSFWorkbook ExcelWBook;
	public static XSSFCell Cell;
	public static boolean TestStatus = true;
	public static Exception Error;
	public static WebDriver driver;
	
	public static void setExcelFile(String Path, String SheetName) throws Exception
	{
		FileInputStream ExcelFile = new FileInputStream(Path);
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		
	}
	
	public static String getCellData(int RowNum, int ColNum) throws Exception 
	{
		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		String CellData = Cell.getStringCellValue();
		return CellData;
	
	}
	
	public static void extentReportInit(String Path)
	{
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(Path);
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		
	}
	   
	public static void manageTestStatus(ExtentTest logger, String Steps) throws Exception
	{
		TestStatus = Events.TestStatus;
		Error = Events.Error;
		
		if(TestStatus == false)
		{
			logger.log(Status.FAIL, Steps);
			logger.log(Status.FAIL, Error);
			Events.captureScreenShot(Steps);
			logger.fail("details").addScreenCaptureFromPath("..\\Reports\\"+Steps+".png");
			
			//logger.addScreenCaptureFromBase64String("Reports\\Click the button.png");
			//logger.fail(MarkupHelper.createLabel(Steps, ExtentColor.RED));
			//logger.addScreenCaptureFromPath("Reports\\Click the button.png");
			
		}		
		else if (TestStatus == true)
		{
			logger.log(Status.PASS, Steps);
			//logger.pass(MarkupHelper.createLabel(Steps, ExtentColor.GREEN));
			//logger.addScreenCaptureFromPath("Reports\\Click the button.png");
		}
		TestStatus = true;
	}
	
}
