package tests;


import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import coin_market.CoinMarketHomepage;
import coin_market.CommonMethods;
import coin_market.CryptoRow;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

enum Browser {
	FIREFOX,
	CHROME
}

public class ExcersizesTest {
	
	 private WebDriver driver;
	
	 /**
	  * The method initializes a driver based on the specified browser
	  * @param browser for which driver will be initialized
	  * @return Nothing is returned
	  */
	 public void initializeDriver(Browser browser) {
		if(browser == Browser.FIREFOX) {
			System.setProperty("webdriver.gecko.driver","src/test/resources/drivers/geckodriver");
			 this.driver = new FirefoxDriver(); 
		} else {
			 System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver");
			 this.driver = new ChromeDriver();
		}
	 }
	 

	 /**
	  * Test which verifies that 100 results are displayed
	  * upon selecting Top 100 filter
	  * */
	 @Test
	 public void getTop100Results() throws InterruptedException {
		 
		 this.initializeDriver(Browser.FIREFOX);
		 CommonMethods common = new CommonMethods(this.driver);
		 CoinMarketHomepage homepage = new CoinMarketHomepage(this.driver);
		
		 homepage.openHomepage();
		 common.waitPageLoad();
		 common.maximizeWindow();
		  
		 homepage.openCryptoDropdown();
		 common.waitElClickable(homepage.geTop100Option());
		 
		 homepage.selectTop100();
// 		 waitPageLoad doesn't work 
//		 due to the race condition where we manage to execute the script in the old page, 
//		 before the browser has started processing the click, 
//		 and it returns true straight away.
		 
//		 common.waitPageLoad();

//		 therefore using explicit wait
		 Thread.sleep(3000);
		 int results =  homepage.getResultsSize();
		 Assert.assertEquals(100, results);
				 
		 this.driver.close();
	 }
	 
	/**
	 * Test which verifies that selected cryptocurrencies are shown in the watchlist
	 * */
	@Test
	public void filterTest() throws InterruptedException {

	    this.initializeDriver(Browser.FIREFOX);
	    CommonMethods common = new CommonMethods(this.driver);
		CoinMarketHomepage homepage = new CoinMarketHomepage(this.driver);
		
		List<CryptoRow> storedResults = new ArrayList<CryptoRow>();
		List<CryptoRow> watchlistResults= new ArrayList<CryptoRow>();
		
	    homepage.openHomepage();
		common.waitPageLoad();
	    common.maximizeWindow();
	    homepage.closePopup();
	    homepage.closeBanner();
		
		homepage.openFilters();
		common.waitElClickable(homepage.getPriceFilter());
		
		homepage.openPriceFilter();
		common.waitElVisbile(homepage.getPriceMinimum());
		
		homepage.setMinimumPrice();
		homepage.setMaximumPrice();
		homepage.applyPriceFilter();
//		common.waitPageLoad();
		Thread.sleep(3000);
		
		for(int i=0; i<5; i++) {
			WebElement el = homepage.getResults().get(i);
			homepage.openItemOptions(el);
			homepage.addToWatchlist(el);
			CryptoRow row = new CryptoRow(homepage.getRowData(el));
			storedResults.add(row);
		}
		
		homepage.openWatchlistTab();
		common.waitPageLoad();
		Thread.sleep(3000);
		
		List<WebElement> watchlistResultsEl = homepage.getResults();
		
		for(WebElement watchlistEl : watchlistResultsEl) {
			watchlistResults.add(new CryptoRow(homepage.getRowData(watchlistEl)));
		}
		
		Assert.assertEquals(storedResults.size(), watchlistResults.size());

		for(CryptoRow result : storedResults) {

			boolean containsRow = watchlistResults.stream().anyMatch(el -> el.isSameRow(result));
			Assert.assertTrue(containsRow);

		}
		 
	    this.driver.close();

		
	}

}


