package coin_market;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** 
 * Contains common methods to be
 * used on any page
 */
public class CommonMethods {

	private WebDriver driver;
	private JavascriptExecutor js;
	
	public CommonMethods(WebDriver driver) {
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
	}
	 
	 public void maximizeWindow() {
		 
		 this.driver.manage().window().maximize();
	 }
	 
	public void waitPageLoad() {
		 WebDriverWait wait = new WebDriverWait(this.driver, 10);
		 wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
	}
	
	public void waitElVisbile(WebElement el) {
		WebDriverWait waitElVisible = new WebDriverWait(this.driver,10);
		waitElVisible.until(ExpectedConditions.visibilityOf(el));	
	}
	
	public void waitElClickable(WebElement el) {
		 WebDriverWait waitElClickable = new WebDriverWait(this.driver, 10);
		 waitElClickable.until(ExpectedConditions.elementToBeClickable(el));
	}
	
	public void scrollElIntoView(WebElement el) {
		this.js.executeScript("arguments[0].scrollIntoView(true);", el);
	}

}
