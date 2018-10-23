package patterns.observable;
import java.util.*;


public class HighscoreListProgram implements HighscoreListListener{
	
	Scanner reader = new Scanner(System.in);
	HighscoreList list;
	
	@Override
	public void listChanged(HighscoreList list, int result) {
		System.out.println(list);
		System.out.println("Posisjon " + (result+1) + " er nå: " + (list.getElement(result)));	
	}
	
	public void init() {
		list = new HighscoreList(3);
		list.addHighscoreListListener(this);
	}
	
	public void run() {
		System.out.println("Skriv inn resultater");
		String val = "";
		while(!val.equals("quit")) {
			val = reader.nextLine();
			try {
				int num = Integer.valueOf(val);
				list.addResult(num);
			}catch(Exception e) {
			}
		}		
	}
	
	public static void main(String[] args) {
		HighscoreListProgram prog = new HighscoreListProgram();
		HighscoreList list = new HighscoreList(3);
		list.addResult(result);
	}
}
