package patterns.delegation.office;
import java.util.*;
import java.util.Arrays;
import java.util.function.BinaryOperator;
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*BinaryOperator<Double> multiplicator = (x ,y) -> x * y;
		System.out.println(multiplicator.apply(5.0, 9.2));*/
		
		/*Printer printer = new Printer();
		Clerk clerk2 = new Clerk(printer);
		Clerk clerk = new Clerk(printer);
		//clerk.printDocument("Hei!");
		//clerk.printDocument("på deg!");
		clerk2.printDocument("Halla");
		System.out.println(printer.getPrintHistory(clerk).size());*/
		List<String> liste = Arrays.asList("Johannes", "er", "en", "nisse", "som", "spiser", "fisk");
		/*Iterator<String> it = liste.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		if (!it.hasNext()) {
			it = liste.iterator();
		}
		System.out.println();
		while(it.hasNext()) {
			System.out.println(it.next());
		
		
		}*/
		
		String[] lol = {"Hakon55", "er", "den", "sykeste!"};
		String xD = "";
		System.out.println(Arrays.asList(lol));
		for (int i=0; i < lol.length; i++) {
			if (i==lol.length-1){
				xD += ", " + lol[i];
				xD += "]";
			}
			else if (i==0){
				xD += "[";
				xD += lol[i];
			}
			else {
				xD += ", " + lol[i];
				
			}
			
		}
		System.out.println(xD);
	
	}

}
