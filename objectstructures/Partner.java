package objectstructures;
/**
 * 22/2/18
 * @author casperfeng
 *
 */
public class Partner {
	
	private String name; //navnet til partner 
	private Partner partner; //en eventuell partner
	/**
	 * Konstruktør for et Partner-objekt (lager Partner m/navn) og partner = null
	 * @param name er navnet til partnern(personen) som opprettes
	 */
	
	public Partner(String name) {
		this.name = name;
		this.partner = null;
	}
	
	/**
	 * Returnerer navnet til Partner-objektet
	 * @return navnet returneres
	 */
	
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returnerer partnerern til Partner-objektet
	 * @return gir enten et Partner objekt eller Null hvis singel
	 */
	
	public Partner getPartner() {
		return this.partner;
	}
	/**
	 * Hjelpefunksjon til setPartner (gjør jobben enklere)
	 * @param partner er partneren som settes
	 */
	public void acceptPartner(Partner partner) {
		this.partner = partner;
	}
	
	/**
	 * Separerer to Partner-objekter fra hverandre :(
	 */
	public void divorcePartner() {
		this.partner.acceptPartner(null); 
		this.partner = null;
	}
	
	/**
	 * Danner et partnerskap for to Partner-objekter
	 * @param partner er forholdet som settes opp. Null tilsvarer skilsmisse.
	 */
	public void setPartner(Partner newPartner){
		if (this == newPartner) {
			throw new IllegalArgumentException("Du kan ikke gifte deg med deg selv!");
		}
		
		else if (this.getPartner() == null && newPartner.getPartner() == null) { //Hvis begge er single
			this.partner = newPartner;
			newPartner.acceptPartner(this);
		}
		else if (this.getPartner() != null && newPartner == null) { //Hvis Partner-objektet er i forhold, og får beskjed om å skille seg
			this.divorcePartner();
		}
		else if (this.getPartner() != null || newPartner.getPartner() != null) { //Sjekker om partner-objektene skal "Swinge litt ;)"
			this.divorcePartner();
			newPartner.divorcePartner();
			this.partner = newPartner;
			newPartner.acceptPartner(this);
			
		}
		
		
	}
	
	/**
	 * Skriver ut Partner-objektet fint (til en oversiktlig streng)
	 * @return en oversiktlig streng
	 */
	public String toString() {
		return this.name + " partner: " + this.partner;
	}

	
	
	
	
	
}
