package stateandbehavior;


public class UpOrDownCounter {
	
	private int stop;
	private int counter;
	
	public UpOrDownCounter(int begin, int end) throws IllegalArgumentException {
		if (!isValid(begin, end)) {
			throw new IllegalArgumentException("The start value can not be the same as the stop value");
		}
		this.counter = begin;
		this.stop = end;
		
	}
	
	public boolean isValid(int begin, int end) {
		if (begin==end) {
			return false;
		} else {
			return true;
		}
	}
	
	public void countincrement(int increment) {
		if (this.counter>stop) {
			this.counter -= increment;
		} else if (this.counter<stop) {
			this.counter += increment;
		}
		
			
		}
	public boolean count() {
		countincrement(1);
		if (this.counter==stop) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public int getCounter() {
		return this.counter;
	}

}
