package coin_market;

import java.util.List;

import org.openqa.selenium.WebElement;

/*Class used to store properties of a row 
 * in the cryptocurrencies table*/
public class CryptoRow {
	
	private String name;
	private String marketCap;
	private String price;
	private String volume;
	private String circulatingSupply;
	private String change;
	
	public CryptoRow() {
		
	}
	
	public CryptoRow(String name, String marketCap, String price, String volume, String circulatingSupply,
			String change) {
		super();
		this.name = name;
		this.marketCap = marketCap;
		this.price = price;
		this.volume = volume;
		this.circulatingSupply = circulatingSupply;
		this.change = change;
	}
	
	/*Constructor that sets row properties 
	 * through a list of properties proceeded as a parameter*/
	public CryptoRow(List<WebElement> list) {
		this.name = list.get(1).getText();
		this.marketCap = list.get(2).getText();
		this.price = list.get(3).getText();
		this.volume = list.get(4).getText();
		this.circulatingSupply = list.get(5).getText();
		this.change = list.get(6).getText();
	}

	/*Method that checks if rows in different lists contain 
	 * the same properties*/
	public boolean isSameRow(CryptoRow row) {
		return this.name.equals(row.name)
				&& this.marketCap.equals(row.marketCap)
				&& this.price.equals(row.price)
				&& this.volume.equals(row.volume)
				&& this.change.equals(row.change);	
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMarketCap() {
		return marketCap;
	}
	
	public void setMarketCap(String marketCap) {
		this.marketCap = marketCap;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getVolume() {
		return volume;
	}
	
	public void setVolume(String volume) {
		this.volume = volume;
	}
	
	public String getCirculatingSupply() {
		return circulatingSupply;
	}
	
	public void setCirculatingSupply(String circulatingSupply) {
		this.circulatingSupply = circulatingSupply;
	}
	
	public String getChange() {
		return change;
	}
	
	public void setChange(String change) {
		this.change = change;
	}
	
	

}
