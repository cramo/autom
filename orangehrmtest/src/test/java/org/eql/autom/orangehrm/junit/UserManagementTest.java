package org.eql.autom.orangehrm.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.eql.autom.orangehrm.pageobject.HomePage;
import org.eql.autom.orangehrm.pageobject.InvalidTableRowNumberException;
import org.eql.autom.orangehrm.pageobject.LoginPage;
import org.eql.autom.orangehrm.pageobject.PageDetailUtilisateur;
import org.eql.autom.orangehrm.pageobject.SystemUserPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class UserManagementTest {

	private WebDriver driver;
	private LoginPage loginPage;

	@Before
	public void beforeTest() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\formation\\geckodriver.exe");

		/*
		 * boolean isDebug =
		 * java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments()
		 * .toString().indexOf("-agentlib:jdwp") > 0; try { if (isDebug)
		 * Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe"); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */

		/*this.driver = new FirefoxDriver();
		this.loginPage = PageFactory.initElements(this.driver, LoginPage.class);
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.driver.get("http://127.0.0.1/orangehrm-4.0/symfony/web/index.php/auth/login");*/
	}

	@Ignore
	public void displayHomePageTest() throws InvalidTableRowNumberException {
		HomePage homePage = this.loginPage.Connection("admin", "admin");
		SystemUserPage sup = homePage.goAdmin();
		sup.selectUserRole("ESS");
		// sup.clickSearch();
		// sup.getUsernameTable();
		PageDetailUtilisateur detail = sup.getUserbyEmployeeName("E E E");
		boolean b = sup.isDisable();
		Assert.assertTrue(b);
		//assertTrue(detail.formulaireInactif());
	}

	@Ignore
	public void getTextIframeTest() {
		this.driver.get("https://www.xul.fr/html5/iframe.php");
		this.driver.switchTo().frame(0);
		WebElement iframe = this.driver.findElement(By.xpath("/html/body/p[1]/em/strong"));
		System.out.println("iframe text = " + iframe.getText());
		assertEquals("", "Exemple de contenu inclut dans la balise.", iframe.getText());
		this.driver.switchTo().defaultContent();
		WebElement iframe2 = this.driver.findElement(By.xpath("//*[@id=\"content\"]/iframe"));
		System.out.println("hahihi " + iframe2.getAttribute("src"));
	}
	
	@Ignore
	public void getAlertClicJSTest() {
		this.driver.get("https://www.startyourdev.com/html/evenement-html-onclick");
		WebElement button = this.driver.findElement(By.xpath("//*[@id=\"div_code_rendu\"]/input"));
		button.click();
		this.driver.switchTo().alert().dismiss();
	}
	
	@Ignore
	public void getTabs() {
		this.driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_link_target");
		this.driver.switchTo().frame(0);
		WebElement button = this.driver.findElement(By.xpath("/html/body/a"));
		
		Set<String> hanldesAvantClic = driver.getWindowHandles();
		String pointDeDepart = driver.getWindowHandle();
		button.click();
		driver.switchTo().defaultContent();
		Set<String> hanldesApresClic = driver.getWindowHandles();
		//this.driver.switchTo().window("https://www.w3schools.com/");
		hanldesApresClic.removeAll(hanldesAvantClic);
		String nouveauHandle = hanldesApresClic.iterator().next();
		driver.switchTo().window(nouveauHandle);
		WebElement titre = driver.findElement(By.xpath("//h1/following-sibling::p"));
		assertEquals("The language for building web pages", titre.getText());
		driver.switchTo().window(pointDeDepart);
		//driver.close();
		
	}
	
	@Ignore
	public void transformationDate() {
		//passer d'un texte à une date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate parseDate = LocalDate.parse("01/01/2000", formatter);
		
		//passer d'une date à un texte
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yy");
		String date = format.format(formatter2);
		
		//comparaison
		assertEquals("comparaison des dates", "01-01-00", date);	
	}
	
	@Ignore
	public void transformationDate2() {
		String dateDepart = "FEB 3rd 2018";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
		formatter.withLocale(Locale.US);
		
		String mois = dateDepart.substring(1, 3).toLowerCase();
		
		String dateAvantPretraitement = "FEB 3rd 2018";
		dateAvantPretraitement = dateAvantPretraitement.replaceAll("(\\d+) (st|nd|rd|th)", "$1");
		
		System.out.println("Date depart = " + dateDepart);
		LocalDate parsedDate = LocalDate.parse("Feb 3 2018", formatter);
		
		
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MMM-dd",  Locale.FRENCH);
		String date = parsedDate.format(formatter2);
		
		assertEquals("comparaison des dates", "2018-FEV-03", date);
	}
	
	
	@Ignore
	public void insertDataTest() throws SQLException, Exception {
		//jdbc:postgresql://hist:port/database
			IDatabaseTester databaseTester = new JdbcDatabaseTester("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/jpetstore", "postgres", "admin");
			//IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
			//ITable actualTable = databaseDataSet.getTable("category");
			QueryDataSet databaseDataSet = new QueryDataSet(databaseTester.getConnection());
			databaseDataSet.addTable("category", "INSERT INTO category VALUES ('LION','Lion','<image src=\"../images/lion_icon.gif\"><font size=\"5\" color=\"blue\"> Lion</font>');");
			databaseTester.setSetUpOperation(DatabaseOperation.INSERT);
			databaseTester.setDataSet(databaseDataSet);
			databaseTester.onSetup();
	}
	
	@Ignore
	public void deleteDataTest() throws SQLException, Exception {
		//jdbc:postgresql://hist:port/database
			IDatabaseTester databaseTester = new JdbcDatabaseTester("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/jpetstore", "postgres", "admin");
			//IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
			//ITable actualTable = databaseDataSet.getTable("category");
			QueryDataSet databaseDataSet = new QueryDataSet(databaseTester.getConnection());
			databaseDataSet.addTable("category", "DELETE FROM category WHERE catid='LION';");
			databaseTester.setSetUpOperation(DatabaseOperation.DELETE);
			databaseTester.setDataSet(databaseDataSet);
			databaseTester.onSetup();
	}
	
	private IDataSet readDataSet(String fileName) throws Exception{
		return new FlatXmlDataSetBuilder().build(new File(fileName));
	}
	
	
	@Test
	public void comareDataTest() throws SQLException, Exception {
		//jdbc:postgresql://hist:port/database
			IDatabaseTester databaseTester = new JdbcDatabaseTester("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/jpetstore", "postgres", "admin");
			IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
			ITable actualTable = databaseDataSet.getTable("category");
			
			IDataSet expectedDataSet = readDataSet("src/main/resources/default-dataset.xml");
			ITable expectedTable = expectedDataSet.getTable("category");
			Assertion.assertEquals(expectedTable, actualTable);
	}
	
	
	@After
	public void afterTest() throws InterruptedException {
		// kill les processus geckodriver puis quit le navigateur/driver
		/*try {
			Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe");
			Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("Pas de task kill");
		}*/
		//this.driver.quit();
	}

}
