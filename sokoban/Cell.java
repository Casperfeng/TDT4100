package sokoban;
/**
 * created 23/2/18
 * @author casperfeng
 * Creates a cell (logic for cell is in LevelCreator)
 */

public class Cell {
	
	private char value; //represents the value of the cell (symbol)
	private String type; //represents the type of the cell (String representation)
	
	/**
	 * constructor for a cell
	 * @param value is the dynamic value
	 * @param type is the static value
	 */
	public Cell(char value, String type) {
		this.value = value;
		this.type = type;
	}
	/**
	 * sets a type to cell
	 * @param type is the static value of cell 
	 */
	public void setType(String type) {
		this.type = type;
		
	}
	
	/**
	 * returns the type
	 * @return is the type of the cell
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * sets a value to cell
	 * @param value is the dynamic value of cell
	 */
	public void setValue(char value) {
		this.value = value;
	}
	/**
	 * returns the value
	 * @return is the value of the cell
	 */
	public char getValue() {
		return this.value;
	}
	
	/**
	 * prints the cell
	 */
	public String toString() {
		return this.value + "";
	}
	/**
	 * sets value and type to cell
	 * @param type is the string name of the cell
	 * @param value is the value of the cell
	 */
	
	public void setTypeValue(String type, char value) {
		this.type = type;
		this.value = value;
	}
	
	

}
