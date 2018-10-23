package interfaces;
/**
 * created 22/2/18
 * @author casperfeng
 *
 */
public class Person2 implements Named{ //Tricky med denne oppgaven er å sette fornavn(givenName) og etternavn (familyName) uten å lagre de i variabler
	
	private String fullName; //fullt navn til person
	/**
	 * Konstruktør for Person2
	 * @param fullName som er det fulle navnet til Person2
	 */
	public Person2(String fullName) {
		this.fullName = fullName;
		
	}
	@Override
	/**
	 * metode for å sette navn
	 */
	public void setGivenName(String name) {
		// TODO Auto-generated method stub
		this.fullName = name + " " + this.getFamilyName();
	}
	/**
	 * metode for å returnere navnet 
	 */
	@Override
	public String getGivenName() {
		// TODO Auto-generated method stub
		return this.fullName.substring(0, this.fullName.indexOf(' ')); //deler opp navnet ved mellomrom
	}
	
	/**
	 * metode for å sette familienavn
	 */

	@Override
	public void setFamilyName(String familyName) {
		// TODO Auto-generated method stub
			this.fullName += getGivenName() + " " + familyName; //
		}
	/**
	 * metode for å returnere familienavnet
	 */
	@Override
	public String getFamilyName() {
		// TODO Auto-generated method stub
		return this.fullName.substring(this.fullName.indexOf(' ') + 1);
	}
	/**
	 * metode for å sette fullt navn
	 */
	@Override
	public void setFullName(String fullName) {
		// TODO Auto-generated method stub
		this.fullName = fullName;
		
	}
	
	/**
	 * metode for å returnere fullt navn
	 */

	@Override
	public String getFullName() {
		// TODO Auto-generated method stub
		return this.fullName;
	}
	

}
