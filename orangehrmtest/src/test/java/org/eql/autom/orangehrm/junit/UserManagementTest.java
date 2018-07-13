package org.eql.autom.orangehrm.junit;

import java.util.concurrent.TimeUnit;

import org.eql.autom.orangehrm.pageobject.HomePage;
import org.eql.autom.orangehrm.pageobject.LoginPage;
import org.eql.autom.orangehrm.pageobject.SystemUserPage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class UserManagementTest {

	private WebDriver driver;
	private LoginPage loginPage;

	@Before
	public void beforeTest() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\formation\\geckodriver.exe");
		this.driver = new FirefoxDriver();
		this.loginPage = PageFactory.initElements(this.driver, LoginPage.class);
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.driver.get("http://127.0.0.1/orangehrm-4.0/symfony/web/index.php/auth/login");
	}

	@Test
	public void displayHomePageTest() {
		HomePage homePage= this.loginPage.Connection("admin", "admin");
		SystemUserPage sup = homePage.goAdmin();
		sup.selectUserRole("ESS");
		//sup.clickSearch();
	}

	@After
	public void afterTest() {
		//this.driver.quit();
	}

}
