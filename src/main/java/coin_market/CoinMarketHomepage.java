package coin_market;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * POM that contains homepage related selectors and methods
 */
public class CoinMarketHomepage {
	
	private WebDriver driver;
	private String homepageURL = "https://coinmarketcap.com/";
	private By coinMarketTabs = By.cssSelector("div.cmc-cryptocurrency-listing-tabs ul.cmc-tabs__header li");
	private By cryptoMenu = By.cssSelector("span");
	private By top100option = By.xpath("//a[text()='Top 100']");
	private By results = By.className("cmc-table-row");
	private By buttonFilters = By.cssSelector("[data-qa-id='table-listing-filters-toggle']");
	private By priceFilter = By.cssSelector("[data-qa-id='filter-dd-toggle']");
	private By priceMinimum = By.cssSelector("[data-qa-id='range-filter-input-min']");
	private By priceMaximum = By.cssSelector("[data-qa-id='range-filter-input-max']");
	private By applyPriceFilterBtn = By.cssSelector("[data-qa-id='filter-dd-button-apply']");
	private By elipsisBtn = By.className("fa-ellipsis-h");
	private By addToWatchlistOption = By.xpath(".//span[text()='Add to Watchlist']");
    private By popupChat = By.cssSelector("[class*='popup']");
    private By closePopupBtn = By.xpath(".//button[text()='No, thanks']");
    private By bannerClose = By.className("cmc-cookie-policy-banner__close");
    private By cellsData = By.tagName("td");
	

    public CoinMarketHomepage(WebDriver driver){
        this.driver = driver;
    }
	
	public List<WebElement> getCoinMarketTabs() {
		return this.driver.findElements(coinMarketTabs);
	}
	
	public WebElement getCryptoTab() {
		return this.getCoinMarketTabs().get(0);
	}
	
	public WebElement getWatchlistTab() {
		return this.getCoinMarketTabs().get(2);
	}
	
	public WebElement getCryptoDropdown() {
		return this.getCryptoTab().findElement(cryptoMenu);
	}
	
	public WebElement geTop100Option() {
		return this.getCryptoDropdown().findElement(top100option);
	}
	
	public WebElement getButtonFilters() {
		return this.driver.findElement(buttonFilters);
	}
	
	public WebElement getPriceFilter() {
		return this.driver.findElement(priceFilter);
	}
	
	public WebElement getPriceMinimum() {
		return this.driver.findElement(priceMinimum);
	}
	
	public WebElement getPriceMaximum() {
		return this.driver.findElement(priceMaximum);
	}
	
	public WebElement getApplyPriceFilterBtn() {
		return this.driver.findElement(applyPriceFilterBtn);
	}
	
	public WebElement getElipsisBtn(WebElement parent) {
		return parent.findElement(elipsisBtn);
	}
	
	public WebElement getWatchlistOption(WebElement parent) {
		return parent.findElement(addToWatchlistOption);
	}
	
	public List<WebElement> getResults() {
		return this.driver.findElements(results);
	}
	
	public int getResultsSize() {
		return this.getResults().size();
	}	
	
	public List<WebElement> getRowData(WebElement row) {
		return row.findElements(cellsData);
	}
	
	public void closePopup() throws InterruptedException {
		Thread.sleep(2000);
		try {
			this.driver.findElement(popupChat).findElement(closePopupBtn).click();
		} catch(NoSuchElementException e) {
		}

	}
	
	public void closeBanner() {
		try {
			this.driver.findElement(bannerClose).click();
		} catch(NoSuchElementException e) {
		}
	}
	
	public void openHomepage() {
		this.driver.get(homepageURL);
	}
	
	public void openCryptoDropdown() {
		this.getCryptoDropdown().click();
	}
	
	public void selectTop100() {
		this.geTop100Option().click();
	}
	
	public void openFilters() {
		this.getButtonFilters().click();
	}
	
	public void openPriceFilter() {
		this.getPriceFilter().click();
	}

	public void setMinimumPrice() {
		this.getPriceMinimum().sendKeys("2000");
	}
	
	public void setMaximumPrice() {
		this.getPriceMaximum().sendKeys("99999");
	}
	
	public void applyPriceFilter() {
		this.getApplyPriceFilterBtn().click();
	}

	public void openItemOptions(WebElement parent) {
		this.getElipsisBtn(parent).click();
		
	}

	public void addToWatchlist(WebElement parent) {
		this.getWatchlistOption(parent).click();
		
	}
	
	public void openWatchlistTab() {
		this.getWatchlistTab().click();
	}
}

