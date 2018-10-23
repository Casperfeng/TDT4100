package game;
/**
 * Created 9/2/18
 * @author casperfeng
 *
 */

public class Cell {
	
	private char value; //verdien for cellen
	private boolean changeable; //om cellen kan endres eller ikke
	private boolean uncertain = false; //Sjekker om cellen er sikker eller usikker
	
	/**
	 * Konstruktør for tom celle.
	 * @param value er verdien til cellen
	 * @param changeable gjør at ruten kan endres
	 */
	public Cell() {
		this.value = '.';
		this.changeable = true;
	}
	
	/**
	 * Konstruktør for celle med gyldig verdi.
	 *@param value angir verdien til ruten
	 *@param changeable gjør at ruten kan endres 
	 */
	public Cell(char value) {
		if (!isValidCell(value)) {
			throw new IllegalArgumentException("Not a valid value: " + getValue());
		}
		this.value = value;
		this.changeable = true;
		
	}
	/**
	 * Hjelpefunksjon som sier om 
	 * @param value er veriden som settes i konstruktøren
	 * @return gir true hvis verdien er gyldig, false ellers
	 */
	private boolean isValidCell(char value) {
		String validValues = ".123456789";
		if (validValues.indexOf(value)!=-1) {
			return true;
		}
		return false;
	}
	/**
	 * Sjekker om cellen er "changeable" (at den kan endres).
	 * @return true hvis den kan endres, false ellers
	 */
	
	public boolean isChangeable() {
		return (this.changeable == true) ? true:false;
	}
	
	/**
	 * Gjør at cellen ikke lenger kan endres (skal bare brukes under konstruktøren til Sudoku).
	 */
	public void setUnchangeable() {
		this.changeable = false;
	}
	
	/**
	 * Returnerer verdien til en celle. 
	 * @return angir verdien til cellen som char
	 */
	
	public char getValue() {
		return this.value;
	}
	
	/**
	 * Setter en gyldig verdi for cellen, utløser exceptions hvis verdien ikke er gyldig eller at den er changeable.
	 * @param value er ønsket verdi for cellen
	 */
	
	
	public void setValue(char value) throws IllegalStateException, IllegalArgumentException{
		if (!this.isChangeable()) {
			throw new IllegalStateException("The cell is not changeable");
		}
		if (!isValidCell(value)) {
			throw new IllegalArgumentException("The value is not valid");
		}
		this.value = value;
	}
	/**
	 * Markerer celler som "usikre", dvs. at man ikke er sikker på om det er riktig.
	 * @param opt er en boolean som skal angis som true hvis man er usikker, false hvis man er sikker.
	 */
	public void setCertainty(boolean opt) {
		this.uncertain = opt;
	}
	
	/**
	 * Sjekker om cellen er sikker eller usikker. 
	 * @return boolean om den er usikker eller ikke, false hvis sikker, true hvis usikker
	 */
	public boolean isUncertain() {
		return this.uncertain;
	}
	
	/**
	 * "Caster" cellen om til en String.
	 *@param returnerer cellens verdi som streng
	 */
	
	public String toString() {
		String out = "";
		out += this.value;
		return out;
	}
	
	

}
