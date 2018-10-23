package patterns.observable;
import java.util.*;
import java.util.stream.Collectors;

public class HighscoreList {
	
	private int maxSize;
	private List<Integer> results ;
	private Collection<HighscoreListListener> listeners; 
	
	public HighscoreList(int maxSize) {
		if (maxSize <= 0) {
			throw new IllegalArgumentException("Size must be a positive integer!");
		}
		this.maxSize = maxSize;
		listeners = new ArrayList<>();
		results = new ArrayList<>();
	}
	
	public int size() {
		return results.size();
	}
	
	public int getElement(int pos) throws IllegalArgumentException{
		if (pos < 0  || pos > maxSize) {
			throw new IllegalArgumentException("Invalid position!");
		}
		return results.get(pos);
	}
	
	public void addResult(int result) {
		results.add(result);
		sortList();
		if (results.size() > maxSize) {
			results.remove(results.size() - 1);
		}
		for (int i = 0; i < results.size(); i++) {
			if (getElement(i) == result) {
				fireChangeListeners(i);
			}
		}
	}
	
	private void sortList() {
		this.results = results.stream().sorted((r1, r2) -> r1 - r2).collect(Collectors.toList());
	}
	
	public void addHighscoreListListener(HighscoreListListener listener) {
		if (!listeners.contains(listener)) {
			this.listeners.add(listener);
		}
	}
	
	public void removeHighscoreListListener(HighscoreListListener listener) {
		this.listeners.remove(listener);
	}
	
	private void fireChangeListeners(int index) {
		for (HighscoreListListener listener : listeners) {
			listener.listChanged(this, index);
		}
	}
	
	public String toString() {
		String out = "";
		int counter = 1;
		for (int r : results) {
			out += counter + ": " + r + "\n";
			counter++;
		}
		return out;
	}
	
	
	
	
}
