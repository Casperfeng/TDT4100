package sokoban;
/**
 * 23/2/18
 * @author casperfeng
 *
 */
public class Push {
	private boolean pushable; //is the interaction a push - if yes true, if not false
	private String direction; //the direction of the push
	
	/**
	 * constructor for an interaction between player and a cell
	 * @param pushable true if pushable, false if not
	 * @param dir is the direction of the interaction
	 */
	public Push(boolean pushable, String dir) {
		this.pushable = pushable; 
		this.direction = dir;
	}
	/**
	 * check if the interaction is a push
	 * @return true if the interaction is a push, false if not
	 */
	
	public boolean isPushable() {
		return this.pushable;
	}
	
	/**
	 * check the direction of an interaction
	 * @return the direction of the interaction
	 */
	
	public String getDirection() {
		return this.direction;
	}

}
