package co.selenium;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Events 
{
	
	public static WebDriver driver;
	public static boolean TestStatus;
	public static Exception Error;
	
	public static void openBrowzer()
	{
		try
		{
			TestStatus = true;
			Error = null;
			System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		catch(Exception e)
		{
			TestStatus = false;
			Error = e;
			return;
		}
	}
     
	public static void launchURL(String URL, Integer Wait) throws Exception
	{ 
		try
		{
			TestStatus = true;
			Error = null;
			driver.manage().timeouts().implicitlyWait(Wait, TimeUnit.SECONDS);
			driver.navigate().to(URL);
		}
		catch(Exception e)
		{
			TestStatus = false;
			Error = e;
			return;
		}
	}
	  
	public static void editText(String ID, String Value) throws Exception
	{
		try
		{
			TestStatus = true;
			Error = null;
			//driver.findElement(By.id(ID)).sendKeys(Value);
			driver.findElement(By.xpath(ID)).sendKeys(Value);;
		}
		catch(Exception e)
		{
			TestStatus = false;
			Error = e;
			return;
		}
		 
	}
	
	public static void clickButton(String Class) throws Exception
	{
		try
		{	
			TestStatus = true;
			Error = null;
			driver.findElement(By.className(Class)).click();
		}
		catch(Exception e)
		{
			System.out.println(e);	
			TestStatus = false;
			Error = e;
			//System.out.println(TestStatus);
			return;
		}
		
	} 
	
	public static void captureScreenShot(String ScreenShotName)
	{
		try
		{
			
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("Reports\\"+ScreenShotName+".png"));
		}
		catch(Exception e)
		{
			 return;
		}
	}
}
