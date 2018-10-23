package interfaces;

import java.util.*;
import java.util.Comparator;
/**
 * created 22/2/18
 * @author casperfeng
 *
 */

public class NamedComparator implements Comparator<Named> { //implementer Comparator<Named> deretter importer Comparator og unimplemented methods
	
	@Override
	public int compare(Named person1, Named person2) {
		// TODO Auto-generated method stub
		String givenName1 = person1.getGivenName(), givenName2 = person2.getGivenName(), 
		familyName1 = person1.getFamilyName(), familyName2 = person2.getFamilyName(); //Lagrer alle fornavn, etternavn i individuelle strenger (jeg er lat)
		
		if (familyName1.compareTo(familyName2) > 0) { //hvis familyName2 er tidligere i alfabetet innfris denne
			return 1;
		}
		else if (familyName1.compareTo(familyName2) < 0) { //hvis familyName2 er senere i alfabetet innfris denne
			return -1;
		}
		else if (familyName1.compareTo(familyName2) == 0) { //hvis familyName1 == familyName2
			if (givenName1.compareTo(givenName2) > 0) { //hvis givenName2 er tidligere i alfabetet innfris denne
				return 1;
			}
			else if (givenName1.compareTo(givenName2) < 0) { //hvis givenName2 er senere i alfabetet innfris denne
				return -1;
			}
		}
		return 0; //hvis ingenting inntreffer dvs. givenName1==givenName2 && familyName1==familyName2 returneres 0
	}
	
	public static void main(String[] args) {
		Comparator<Named> c = new NamedComparator();
		List<Named> v = Arrays.asList(new Person1("Casper", "Feng"), new Person1("Ellen", "Chang"), new Person1("Hans", "Chang"), new Person1("Catherine", "Feng")); 
		Collections.sort(v, c);
		System.out.println(v);
	}

}
