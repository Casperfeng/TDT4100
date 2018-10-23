package interfaces;
import java.util.ArrayList;
/**
 * created 22/2/18
 * @author casperfeng
 *
 */
public class Person1 implements Named{ //Tricky med denne oppgaven er å hente ut fullName uten variablen lagret
	
	private String givenName; //gitt navn
	private String familyName; //familienavn
	
	/**
	 * Konstruktør for Person1-objekter
	 * @param givenName er gitt navn
	 * @param familyName er familienavn
	 */
	public Person1(String givenName, String familyName) {
		this.givenName = givenName;
		this.familyName = familyName;
	}
	/**
	 * metode for å sette navn
	 */
	@Override
	public void setGivenName(String name) {
		// TODO Auto-generated method stub
		this.givenName = name;

	}
	/**
	 * metode for å returnere navnet
	 */
	@Override
	public String getGivenName() {
		// TODO Auto-generated method stub
		return this.givenName;
	}
	
	/**
	 * metode for å sette familienavn
	 */

	@Override
	public void setFamilyName(String familyName) {
		// TODO Auto-generated method stub
		this.familyName = familyName;
	}
	/**
	* metode for å returnere familienavnet
	*/

	@Override
	public String getFamilyName() {
		// TODO Auto-generated method stub
		return this.familyName;
	}
	/**
	 * metode for å sette fullt navn
	 */
	@Override
	public void setFullName(String fullName) {
		// TODO Auto-generated method stub
		String[] names = fullName.split(" ");
		this.givenName = names[0];
		this.familyName = names[1];
	}
	
	/**
	 * metode for å returnere fullt navn
	 */

	@Override
	public String getFullName() {
		// TODO Auto-generated method stub
		return this.getGivenName() + " " + this.getFamilyName(); 
	}
	
	public String toString() {
		return this.getGivenName() + " " + this.getFamilyName();
	}

}
