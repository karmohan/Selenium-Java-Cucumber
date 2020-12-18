package stepdefinations;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.junit.Cucumber;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

@RunWith(Cucumber.class)
public class stepdefination {
	public static WebDriver driver;
	public static JavascriptExecutor JavaScript_Executor;
	public static String favorite_list_item1 = null;
	public static String favorite_list_item2 = null;
	public static String myVideosItem1text = null;
	public static String myVideosItem2text = null;
	public static Set<String> Wishlist = new TreeSet<String>();

	@Given("^Creating a chrome driver instance and Navigating to discovery home page$")
	public void creating_a_chrome_driver_instance_and_navigating_to_discovery_home_page() {

		// To create a chrome driver instance
                // If needed, Please edit the following line to point to your local chrome driver excuatable
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\91805\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("A chrome instance has been initialized");

		// Navigating to discovery.com home page
		driver.get("https://www.discovery.com/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println("In home page of discovery.com");

	}

	@When("^To look into the videos recommended for me$")
	public void to_look_into_the_videos_recommended_for_me() {

		// To look into the videos recommended for me
		JavaScript_Executor = (JavascriptExecutor) driver;
		WebElement episdoes_element = driver.findElement(By.cssSelector("div[class*='fullWidthPromo section']"));
		JavaScript_Executor.executeScript("arguments[0].scrollIntoView(true);", episdoes_element);
		System.out.println("Identified the section to look into the videos recommended for me ");

	}

	@And("^To create a favourite list$")
	public void to_create_a_favourite_list() {

		// To create a favourite list

		favorite_list_item1 = driver.findElement(By.xpath("//span[@class='m-Card__a-HeadlineText']")).getText();
		Wishlist.add(favorite_list_item1);
		driver.findElement(By.xpath("//*[@id='mod-card-1']/div[1]/a[1]/div/img")).click();
		System.out.println("1/2 of the recommendations has been identified");
		System.out.println("The video 1 is " + favorite_list_item1);
		driver.findElement(By.cssSelector("i[class*='flipIconCore__icon']")).click();
		driver.navigate().back();

		favorite_list_item2 = driver.findElement(By.xpath("(//span[@class='m-Card__a-HeadlineText'])[2]")).getText();
		Wishlist.add(favorite_list_item2);
		driver.findElement(By.xpath("//*[@id='mod-card-2']/div[1]/a[1]/div/img")).click();
		System.out.println("2/2 of the recommendations has been identified");
		System.out.println("The video 2 is " + favorite_list_item2);
		driver.findElement(By.cssSelector("i[class*='flipIconCore__icon']")).click();
		System.out.println("The favorites list is created");

	}

	@And("^To open my videos page to ensure my videos section is not empty$")
	public void to_open_my_videos_page_to_ensure_my_videos_section_is_not_empty() throws InterruptedException {

		// To open my videos page
		driver.findElement(By.xpath("//*[@class='dscHeaderMain__iconMenu']")).click();

		// To ensure my videos section is not empty
		List<WebElement> VideoCount=driver.findElements(By.xpath("//a[span[text() = 'My Videos']]/span/span"));
		Assert.assertNotEquals(VideoCount.size(), 0);
		System.out.println("Verified My videos contains elements");

		// Ensured my videos section is not empty. To proceed into my videos
		driver.findElement(By.xpath("//a[span[text() = 'My Videos']]")).click();
		System.out.println("Currently in the my videos page");

	}

	@Then("^To validate the title and description displayed in the page matches the favorites list$")
	public void to_validate_the_title_and_description_displayed_in_the_page_matches_the_favorites_list() throws InterruptedException {

		// To validate the title and description displayed in the page matches the favorites list
		System.out.println("Looking into the first element in my videos");
		WebElement myVideosItem1 = driver.findElement(By.xpath(
				"//*[@id='react-root']/div/div[1]/div[3]/main/div/div[1]/section[3]/div/div/div/div[1]/div/div[1]/div/div[1]/div[2]/div/a/div/h4/div"));
		JavaScript_Executor.executeScript("arguments[0].scrollIntoView(true);", myVideosItem1);
		myVideosItem1text = myVideosItem1.getText();
		System.out.println("The first video in the list is " + myVideosItem1text);

		if (Wishlist.contains(myVideosItem1text)) {
			Wishlist.remove(myVideosItem1text);
			System.out.println(myVideosItem1text + " is present in the fav list");
		}

		System.out.println("Looking into the second element in my videos");
		WebElement myVideosItem2 = driver.findElement(By.xpath(
				"//*[@id='react-root']/div/div[1]/div[3]/main/div/div[1]/section[3]/div/div/div/div[1]/div/div[2]/div/div[1]/div[2]/div/a/div/h4/div"));
		JavaScript_Executor.executeScript("arguments[0].scrollIntoView(true);", myVideosItem2);
		myVideosItem2text = myVideosItem2.getText();
		System.out.println("The second video in the list is " + myVideosItem2text);

		if (Wishlist.contains(myVideosItem2text)) {
			Wishlist.remove(myVideosItem2text);
			System.out.println(myVideosItem2text + " is present in the fav list");
		}

		// Empty Wishlist indicates both the identified items and wishlist match each other else otherwise
		Assert.assertEquals(Wishlist.size(), 0);

		driver.quit();
	}
	
}
