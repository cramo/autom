package org.eql.autom.orangehrm.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	protected final WebDriver driver;
	
	@FindBy(xpath = "//*[@id=\"txtUsername\"]")
	private WebElement champLogin;
	
	@FindBy(xpath = "//*[@id=\"txtPassword\"]")
	private WebElement champPassword;
	
	@FindBy(xpath = "//*[@id=\"btnLogin\"]")
	private WebElement boutonConnection;
	
	public LoginPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public HomePage Connection(String login, String password) {
		this.champLogin.clear();
		this.champLogin.sendKeys(login);
		this.champPassword.clear();
		this.champPassword.sendKeys(password);
		this.boutonConnection.click();
		return (PageFactory.initElements(driver, HomePage.class));
	}
}
