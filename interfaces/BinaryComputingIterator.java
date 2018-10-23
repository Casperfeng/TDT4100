package interfaces;
import java.util.*;
import java.util.function.BinaryOperator;

/**
 * 10/5/18
 * @author casperfeng
 * Gjør denne fordi Vegard Hellem ba meg
 * Kombinerer to iteratorer, skal hente en verdi 
 * fra hver og deretter utføre operasjonen på den
 */


public class BinaryComputingIterator implements Iterator<Double>{
	
	private Iterator<Double> it1;
	private Iterator<Double> it2;
	private BinaryOperator<Double> it3;
	private final Double default1;
	private final Double default2;
	
	public BinaryComputingIterator(Iterator<Double> iterator1, Iterator<Double> iterator2, BinaryOperator<Double> operator) {
		this(iterator1, iterator2, null, null, operator);
	}
	
	
	public BinaryComputingIterator(Iterator<Double> iterator1, Iterator<Double> iterator2, Double default1, Double default2, BinaryOperator<Double> operator) {
		this.it3 = operator;
		this.it1 = iterator1;
		this.it2 = iterator2;
		this.default1 = default1;
		this.default2 = default2;
		
	}
	@Override
	public boolean hasNext() {
		return ((it1.hasNext() || default1 != null) && (it2.hasNext() || default2 != null) && (it1.hasNext() || it2.hasNext()));		
	}
	
	@Override
	public Double next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		Double nextValueIt1 = (it1.hasNext()) ? it1.next() : default1;
		Double nextValueIt2 = (it2.hasNext()) ? it2.next() : default2;
		return it3.apply(nextValueIt1, nextValueIt2);
	}
}
