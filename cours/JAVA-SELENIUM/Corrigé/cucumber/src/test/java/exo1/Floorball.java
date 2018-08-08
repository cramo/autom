package exo1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Floorball {

	WebDriver driver = null;
	
	@Given("^I have opened a browser$")
	public void i_have_opened_a_browser() throws Throwable {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\formation\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.google.fr");
	}
	
	@Given("^I search for \"([^\"]*)\"$")
	public void i_search_for(String arg1) throws Throwable {
	    driver.findElement(By.id("lst-ib")).sendKeys(arg1);
	    driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
	}


	@When("^I click on the French floorball federation website$")
	public void i_click_on_the_French_floorball_federation_website() throws Throwable {
		driver.findElement(By.xpath("//a[contains(@href,'www.floorball.fr')]")).click();
	}

	@Then("^the menu \"([^\"]*)\" is clickable$")
	public void the_menu_is_clickable(String arg1) throws Throwable {
		driver.findElement(By.xpath("//div[@id='colgauche']/div[2]/p[7]/a/img")).click();
	}

}



