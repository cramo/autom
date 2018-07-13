package petstoretest;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PremierTestSelenium {

	@Test
	public void afficherPageAcceuil() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\formation\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			driver.get("http://localhost:8180/jpetstore-1.0.5-env2/");

			// driver.findElement(By.xpath("//a[@href = \"shop/index.do\"]")).click();
			// WebElement lienEntree = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/p[5]/a"));
			/*
			 * WebElement lienEntree = driver.findElement(By.linkText("Enter the Store"));
			 * assertEquals("text du lien", "Enter the Store", lienEntree.getText());
			 * List<WebElement> lienEntree2 =
			 * driver.findElements(By.xpath("(//a[@href = 'shop/index.do'])[2]"));
			 * List<WebElement> lienEntree3 =
			 * driver.findElements(By.xpath("//a[@href = 'shop/index.do']"));
			 * assertEquals(2, lienEntree3.size()); assertEquals("", "Enter the Store",
			 * lienEntree3.get(1).getText());
			 */

			// WebElement lienEntree4 = driver.findElement(By.xpath("(//a[@href =
			// 'shop/index.do'])[2]"));
			// WebElement imageCentrale = driver.findElement(By.xpath("//img[@src =
			// '../images/splash.gif']"));
			// Wait wait = new FluentWait(driver).withTimeout(30,
			// TimeUnit.SECONDS).pollingEvery(5,
			// TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
			wait.withTimeout(Duration.ofSeconds(30));
			wait.pollingEvery(Duration.ofSeconds(5));
			wait.ignoring(NoSuchElementException.class);

			WebElement lienEntree4 = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.xpath("(//a[@href = 'shop/index.do'])[2]"));
				}
			});
			lienEntree4.click();
			WebElement image = driver.findElement(By.xpath("//area[@alt = 'Birds']"));
			assertEquals(350, image.getSize().width);

			WebDriverWait wait2 = new WebDriverWait(driver, 10);

			WebElement element1 = wait2.until(ExpectedConditions.elementToBeClickable(By.linkText("Enter the Store")));

			Boolean b = wait2.until(
					ExpectedConditions.and(ExpectedConditions.elementToBeClickable(By.linkText("Enter the Store")),
							ExpectedConditions.visibilityOfElementLocated(By.linkText("Enter the Store"))));

			if(b == true)
			{
				WebElement element2 = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Enter the Store")));
				assertEquals("texteDuLien2", "Enter the Store", element2.getText());
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}
}
