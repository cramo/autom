package exo1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Floorball {
	WebDriver driver = null;
	
	@Given("^I have opened a browser$")
	public void i_have_opened_a_browser() throws Throwable {
	    System.setProperty("webdriver.gecko.driver", "C:\\dev\\geckodriver.exe");
	    driver = new FirefoxDriver();
	    driver.get("https://www.google.fr");
	}

	@Given("^I search for \"([^\"]*)\"$")
	public void i_search_for(String arg1) throws Throwable {
	    driver.findElement(By.id("lst-ib")).sendKeys("France Floorball");
	    driver.findElement(By.xpath("//form[@id='tsf']/div[2]/div[3]/center/input[1]")).click();
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
