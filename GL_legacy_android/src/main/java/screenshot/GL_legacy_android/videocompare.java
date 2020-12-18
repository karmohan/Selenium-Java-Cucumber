package screenshot.GL_legacy_android;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class videocompare extends base {
	public static JavascriptExecutor JavaScript_Executor;
	public static AndroidDriver<AndroidElement> driver;
	 public static image_compare compare;

	public static void main(String[] args) throws InterruptedException, IOException {
		
		driver = Capabilities();
		WebDriverWait wait=new WebDriverWait(driver,30);
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='ON LAST NIGHT']"))); 
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"UNLOCKED EPISODES\"));");
		driver.findElementById("img_video_thumbnail").click();
		Thread.sleep(25000);
		screenshot("image1");
		Thread.sleep(15000);
		screenshot("image2");
		compare= new image_compare();
		compare.comparision();
		Thread.sleep(5000);
		driver.quit();
		
		}
	public static void screenshot(String image) throws IOException {
		//Date d = new Date();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");         // Your each screenshot will be taken as this format "Year-Month-Date-Hours-Minutes-Seconds"
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,  new File("Desktop"+image+".png"));  
	}
	
	


}
