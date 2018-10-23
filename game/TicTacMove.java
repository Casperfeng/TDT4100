package game;
/**
 * 2/3/18
 * @author casperfeng
 * klassen skal lagre x og y verdiene til TicTacToe trekk
 */
public class TicTacMove {
	
	private char c; //char til brikken
	private int x; //x posisjonen
	private int y; //y posisjonen
	/**
	 * lagrer x-posisjonen og y-posisjonen til et trekk
	 * @param x
	 * @param y
	 */
	public TicTacMove(char c, int x, int y) {
		this.x = x;
		this.y = y;
		this.c = c;
	}
	/**
	 * henter ut x-verdien
	 * @return x
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * henter ut y-verdien
	 * @return y
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * henter ut character
	 * @return character, c
	 */
	public char getChar() {
		return this.c;
	}
}
