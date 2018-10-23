package patterns.observable;

public interface StockListener {
	void StockPriceChanged(Stock stock, double oldValue, double newValue);
}
