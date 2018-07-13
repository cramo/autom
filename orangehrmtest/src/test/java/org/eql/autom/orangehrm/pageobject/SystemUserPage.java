package org.eql.autom.orangehrm.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class SystemUserPage extends MenuPage {
	
	@FindBy(xpath = "//*[@id=\"searchSystemUser_userType\"]")
	protected WebElement userRole;
	
	@FindBy(xpath = "//*[@id=\"searchBtn\"]")
	protected WebElement search;
	
	public SystemUserPage(WebDriver driver) {
		super(driver);
	}
	
	public void selectUserRole(String text) {
		Select select =  new Select(this.userRole);
		select.selectByVisibleText(text);
	}
	
	public void clickSearch() {
		search.click();
	}
	
	public void getUserNameFromEmployeeName(String employeeName) {
		
	}
	
	public void getUserbyEmployeeName(String employeeName) {
		List<WebElement> lignes = driver.findElements(By.xpath("//*[@id=\"resultTable\"]/tbody/tr"));
		for(WebElement ligne : lignes){
			System.out.println("lignes = " + lignes.size());
			List<WebElement> colonne = ligne.findElements(By.xpath("td"));
			System.out.println("colonnes = " + colonne.size());
			if(employeeName.equals(colonne.get(3).getText())){
				WebElement el = colonne.get(1);
				WebElement el2 = el.findElement(By.xpath("a"));
				System.out.println(el.getText());
				el2.click();
			}
			else
				System.out.println("Nope");
			
		}
	}
	
	public boolean isDisable()
	{
		WebElement el = driver.findElement(By.xpath("//*[@id=\"systemUser_userType\"]"));
		System.out.println("tagname = " + el.getTagName());
		System.out.println("class = " + el.getClass());
		return (!el.isEnabled());
	}
	
}
