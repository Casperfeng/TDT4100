package patterns.observable;
import java.util.*;
public class Stock {
	
	private final String ticker;
	private double price;
	private List<StockListener> listeners = new ArrayList<>();
	public Stock(String ticker, double price) {
		if (price <= 0) {
			throw new IllegalArgumentException("Invalid price");
		}
		this.ticker = ticker;
		this.price = price;
	}
	
	public void setPrice(double price) {
		double oldPrice = this.price;
		if (price <= 0) {
			throw new IllegalArgumentException("Invalid price");
		}
		for (StockListener l : listeners) {
			l.StockPriceChanged(this, oldPrice, price);
		}
		this.price = price;
	}
	
	public String getTicker() {
		return this.ticker;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void addStockListener(StockListener listener) {
		this.listeners.add(listener);
	}
	
	public void removeStackListener(StockListener listener) {
		this.listeners.remove(listener);
	}
	
	
}