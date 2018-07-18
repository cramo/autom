package org.eql.autom.orangehrm.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageDetailUtilisateur extends MenuPage {

	@FindBy(id = "systemUser_userType")
	private WebElement selectUserRole;

	@FindBy(id = "systemUser_employeeName")
	private WebElement inputEmployeeName;

	@FindBy(id = "systemUser_uerName")
	private WebElement inputUserName;

	@FindBy(id = "systemUsr_status")
	private WebElement selectStatus;

	public PageDetailUtilisateur(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean formulaireInactif() {
		return (!this.selectUserRole.isEnabled() && !this.inputEmployeeName.isEnabled()
				&& !this.inputUserName.isEnabled() && !this.selectStatus.isEnabled());
	}
}
