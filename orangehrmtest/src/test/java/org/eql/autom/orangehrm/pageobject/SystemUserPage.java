package org.eql.autom.orangehrm.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class SystemUserPage extends MenuPage {
	
	@FindBy(xpath = "//*[@id=\"searchSystemUser_userType\"]")
	protected WebElement userRole;
	
	@FindBy(xpath = "//*[@id=\"searchBtn\"]")
	protected WebElement search;

	//private Select select;
	
	public SystemUserPage(WebDriver driver) {
		super(driver);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//select = new Select(userRole);
	}
	
	public void selectUserRole(String text) {
		Select select =  new Select(this.userRole);
		select.selectByVisibleText(text);
	}
	
	/*public void selectUserRoleAll() {
		select.selectByVisibleText("All");
	}
	
	public void selectUserRoleESS() {
		this.select = new Select(userRole);
		this.select.selectByValue("2");
		//select.selectByVisibleText("ESS");
	}
	
	public void selectUserRoleAdmin() {
		select.selectByVisibleText("Admin");
	}*/
	
	public void clickSearch() {
		search.click();
	}
}
