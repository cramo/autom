package org.eql.autom.orangehrm.junit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.eql.autom.orangehrm.pageobject.HomePage;
import org.eql.autom.orangehrm.pageobject.LoginPage;
import org.eql.autom.orangehrm.pageobject.SystemUserPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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
		
		/*boolean isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
		try {
		    if (isDebug)
		        Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
		} catch (IOException e) {
		    e.printStackTrace();
		}*/

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
		//sup.getUsernameTable();
		sup.getUserbyEmployeeName("E E E");
		boolean b = sup.isDisable();
		Assert.assertTrue(b);
	}

	@After
	public void afterTest() throws InterruptedException {
		//kill les processus geckodriver puis quit le navigateur
	    try {
	        Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
	        Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe");
	        Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		this.driver.quit();
	}

}
