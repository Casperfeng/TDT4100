package patterns.observable;
import java.util.*;
public class StockIndex implements StockListener{

	private List<Stock> stocks = new ArrayList<>();
	private final String ticker;
	
	public StockIndex(String ticker, Stock...stocks){
		this.ticker = ticker;
		for (Stock stock : stocks) {
			this.stocks.add(stock);
		}
	}
	
	@Override
	public void StockPriceChanged(Stock stock, double oldValue, double newValue) {
		// TODO Auto-generated method stub
		for (Stock s : stocks) {
			if (s.equals(stock)) {
				s.setPrice(newValue);
			}
		}
	}
	
	public double getIndex() {
		if (stocks.isEmpty()) {
			
		}
	}
	
}
