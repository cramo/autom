package org.eql.autom.orangehrm.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class MenuPage {

	protected final WebDriver driver;

	@FindBy(xpath = "/html/body/div[1]/div[2]/ul/li[1]/a/b")
	protected WebElement admin;

	@FindBy(xpath = "/html/body/div[1]/div[2]/ul/li[2]/a/b")
	protected WebElement pim;

	@FindBy(xpath = "/html/body/div[1]/div[2]/ul/li[3]/a/b")
	protected WebElement leave;

	@FindBy(xpath = "/html/body/div[1]/div[2]/ul/li[4]/a/b")
	protected WebElement time;

	@FindBy(xpath = "/html/body/div[1]/div[2]/ul/li[5]/a/b")
	protected WebElement recrutement;

	@FindBy(xpath = "/html/body/div[1]/div[2]/ul/li[6]/a/b")
	protected WebElement performance;

	@FindBy(xpath = "/html/body/div[1]/div[2]/ul/li[7]/a/b")
	protected WebElement dashboard;

	@FindBy(xpath = "/html/body/div[1]/div[2]/ul/li[8]/a/b")
	protected WebElement directory;
	
	@FindBy(id = "menu_admin_viewAdminModule")
	private WebElement menuAdmin;
	
	@FindBy(id = "menu_admin_UserManagement")
	private WebElement menuUserManagement;
	
	@FindBy(id = "menu_admin_viewSystemUsers")
	private WebElement menuViewSystemUsers;
	
	public SystemUserPage cliquerMenuViewSystemUsers() {
		Actions actions = new Actions(driver);
		actions.moveToElement(menuAdmin).moveToElement(menuUserManagement).build().perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		//wait.until(ExpectedConditions.and(ExpectedConditions.visibilityOfAllElements(menuViewSystemUsers), ExpectedConditions.elementToBeClickable(menuViewSystemUsers)));		
		menuViewSystemUsers.click();
		return PageFactory.initElements(driver, SystemUserPage.class);
	}

	public MenuPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public SystemUserPage goAdmin() {
		this.admin.click();
		return (PageFactory.initElements(driver, SystemUserPage.class));
	}

	public void goPIM() {
		this.pim.click();
	}

	public void goLeave() {
		this.leave.click();
	}

	public void goTime() {
		this.time.click();
	}

	public void goRecrutment() {
		this.recrutement.click();
	}

	public void goPerformance() {
		this.performance.click();
	}

	public void goDashboard() {
		this.dashboard.click();
	}

	public void goDirectory() {
		this.directory.click();
	}

}
