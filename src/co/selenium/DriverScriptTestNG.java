package co.selenium;

import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class DriverScriptTestNG  
{
	public static ExtentTest logger;
	public static ExtentReports extent;
	public static boolean TestStatus = true;
	public static Exception Error;
	
	@Test
	public void DriverFunction() throws Exception
	{
		String Path = "Others\\TestScenarios.xlsx";
		Utils.setExcelFile(Path, "Sheet1");
		ExtentHtmlReporter reporter = new ExtentHtmlReporter("Reports\\Result.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		for (int iRow = 1;iRow<=12;iRow++)
		{
			String sEvents = Utils.getCellData(iRow, 4);
			String InputValue1 = Utils.getCellData(iRow, 5);
			String InputValue2= Utils.getCellData(iRow, 6);
			String Steps= Utils.getCellData(iRow, 3);
			 	 
			switch (sEvents)
			{
				case "Begin":
					logger = extent.createTest(Utils.getCellData(iRow, 2));
					break;
				case "openBrowzer": 	
					Events.openBrowzer(); 
					Utils.manageTestStatus(logger, Steps);
					break;
				case "launchURL":
					Events.launchURL(InputValue1,Integer.parseInt(InputValue2)); 
					Utils.manageTestStatus(logger, Steps);
					break;
				case "editText":
					Events.editText(InputValue1, InputValue2);
					Utils.manageTestStatus(logger, Steps);
					break;
				case "clickButton":
					Events.clickButton(InputValue1);
					Utils.manageTestStatus(logger, Steps);
					break;
				case "End":
					extent.flush(); 
					System.out.println("InsideTestNG");
					break;
			} 
			
		}
	}
	
}
