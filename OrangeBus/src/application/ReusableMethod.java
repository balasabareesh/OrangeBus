package application;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

public class ReusableMethod 
{
	static WebDriver driver;
	static WebElement element;
	public void openBrowser(String Browsername, String URL)
	{
		if(Browsername.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\Supportfiles\\chrome\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get(URL);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);			
		}
		else if(Browsername.equalsIgnoreCase("InternetExplorer"))
		{
			System.setProperty("webdriver.ie.driver", "D:\\Selenium\\Supportfiles\\IEDriverServer_Win32_2.53.0\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.get(URL);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
		else
		{
			System.out.println("Invalid Entry");
		}
		
	}
	public void enterData(By locator, String Data)
	{
		element = driver.findElement(locator);
		element.sendKeys(Data);
	}
	public void performClick(By locator)
	{
		try {
			Thread.sleep(10000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		element= driver.findElement(locator);
		element.click();
	}
	public String readData(By locator)
	{
		element = driver.findElement(locator);
		return element.getText();		
	}
	public void closeTabs()
	{
		driver.quit();
	}
	public String hoverText(By locator)
	{
		element = driver.findElement(locator);
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		return element.getAttribute("data-type");
		
	}
	public void captureScreenshot()
	{
		TakesScreenshot scr = (TakesScreenshot)driver;
		File srcfile = scr.getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("DDMMYYYYHHMMSS");
		String Timestamp = sf.format(d);
		String Outputfile = "479488_"+Timestamp;
		try {
			FileUtils.copyFile(srcfile, new File("Report\\"+Outputfile+".png"));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}	
	}
}
