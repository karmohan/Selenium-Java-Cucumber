package screenshot.GL_legacy_android;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class base
{
    public static AndroidDriver<AndroidElement> Capabilities() throws MalformedURLException
    {
    	
    	File f1=new File("src/main/java/screenshot/GL_legacy_android");
		File fe=new File(f1,"com.discovery.ahcgo_2.14.5-1571772705.apk");
		
    	DesiredCapabilities dc=new DesiredCapabilities();
    	dc.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
    	dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android device");
    	dc.setCapability(MobileCapabilityType.APP, fe.getAbsolutePath());
    	AndroidDriver<AndroidElement> driver=new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),dc);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
    }
}
