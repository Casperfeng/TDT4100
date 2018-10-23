package interfaces;

public interface Named {
	
	void setGivenName(String name); //metode for å sette navn
	String getGivenName(); //metode for å returnere navnet
	
	void setFamilyName(String familyName); //metode for å sette familienavn
	String getFamilyName(); //metode for å returnere familienavnet
	
	void setFullName(String fullName); //metode for å sette fullt navn
	String getFullName(); //metode for å returnere fullt navn
}
