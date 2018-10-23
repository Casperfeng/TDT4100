package game;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Created 9/2/18
 * @author casperfeng
 *
 */

public class Sudoku {
	
	Scanner in = new Scanner(System.in);
	private int nextRow; //brukes i input metoden for å velge rad for neste celle
	private int nextCol; //brukes i input metoden for å velge kolonne for neste celle
	private char nextValue; //brukes i input metoden for å sette verdien til neste celle
	private ArrayList <ArrayList<Cell>> board = new ArrayList<ArrayList<Cell>>();
	/**
	 * Konstruktør for et brett uten forhåndssatte tall, lager et brett av tomme ruter.
	 */

	public Sudoku() {
		for (int r = 0; r < 9; r++) {
			board.add(new ArrayList<Cell>());
			for (int c = 0; c < 9; c++) {
				board.get(r).add(new Cell());
			}
		}
	}
	
	/**
	 * Konstruktør for et brett med forhåndssatte tall, setter verdiene inn i brettet.
	 * @param preDetermined er den forhåndssatte sekvensen av tall og tomme ruter
	 */
	public Sudoku(String preDetermined) {
		int counter = 0;
		for (int r = 0; r < 9; r++) {
			board.add(new ArrayList<Cell>());
			for (int c = 0; c < 9; c++) {
				char currentChar = preDetermined.charAt(counter);
				Cell currentCell = new Cell(currentChar);
				if (currentChar != '.') {
					currentCell.setUnchangeable();
					board.get(r).add(currentCell);
				}
				else {
					board.get(r).add(currentCell);
				}
				counter++;
			}
		}
	}
	/**
	 * Innvendig innkapsling getter for celle ved en gitt posisjon.
	 * @param r er raden cellen er plassert
	 * @param c er kolonnen cellen er plassert
	 * @return cellen som er på angitt posisjon
	 */
		
	private Cell getCell(int r, int c) {
		return board.get(r).get(c);
	}
	
	/**
	 * Innvendig innkapsling setter for celleverdien ved angitt posisjon. 
	 * @param r er raden cellen er plassert
	 * @param c er kolonnen cellen er plassert
	 * @param value verdien som skal settes for cellen på angitt posisjon
	 */
		
	public void setCell(int r, int c, char value) {
		if (getCell(r,c).isChangeable()) {
			getCell(r,c).setValue(value);
		}
	}
	
	/**
	 * Innvendig innkapsling setter som setter cellen som usikker.
	 * @param r er raden cellen er plassert
	 * @param c er kolonnen cellen er plassert
	 * @param value er den usikre verdien som settes
	 */
	
	private void setUncertain(int r, int c, char value) {
		getCell(r,c).setValue(value);
		getCell(r,c).setCertainty(true);
	}
	/**
	 * Innvendig innkalsping som setter cellens verdi til value.
	 * @param r er raden cellen er plassert
	 * @param c er kolonnen cellen er plassert
	 * @param value er verdien som settes
	 */
	private void setCertain(int r, int c, char value) {
		getCell(r,c).setValue(value);
		getCell(r,c).setCertainty(false);
	}
	/**
	 * Innvendig metode som forenkler toString metoden.
	 * @param r er raden til cellen
	 * @param c er kolonnen til cellen
	 * @return String av cellen angitt på korrekt format.
	 */
	private String stringModifier(int r,int c) {
		String out = "";
		Cell cell = getCell(r,c);
		if (!cell.isChangeable()) {
			out += "(" + cell + ")";
			return out;
		}
		else if (cell.isUncertain()) {
			out += " " + cell + "*";
			return out;
		}
		out = " " + cell + " ";
		return out;
	}
	
	/**
	 * toString metode for spillebrettet (sykt nais)
	 */
	public String toString() {
		String out = "";
		out += "+-------------+-------------+-------------+\n";
		for (int r=0; r<9; r++) {
			if (r==3 || r==6) {
				out += "+-------------+-------------+-------------+\n";
				out += "| ";
			}
			else {
				out += "| ";
			}
			for (int c=0; c<9; c++) {
				if (c==3 || c==6){
					out += "| ";
				}
				out += stringModifier(r,c) + " ";
			}
			out += "|\n";
		}
		out += "+-------------+-------------+-------------+\n";
		return out;
	}
	/**
	 * Funksjon som sjekker om kolonnene oppfyller reglene til Sudoku.
	 * @return boolean true hvis kolonnene ikke har en repeterende verdi, false ellers.
	 */
	
	private boolean checkVertical() {
		ArrayList <Character> takenChars = new ArrayList<>(); 
		for (int c=0; c<9; c++) {
			takenChars.clear();
			for (int r=0; r<9; r++) {	
				if (takenChars.contains(getCell(c,r).getValue()) && getCell(c,r).getValue()!='.') {
					return false;
				}
			takenChars.add(getCell(c,r).getValue());
			}
		}
		return true;
	}
	/**
	 * Funksjon som sjekker om radene oppfyller reglene til Sudoku.
	 * @return boolean true hvis radene ikke har en repeterende verdi, false ellers.
	 */
	private boolean checkHorisontal() {
		ArrayList<Character> takenChars = new ArrayList<>();
		for (int r=0; r<9; r++) {
			takenChars.clear();
			for (int c=0; c<9; c++) {
				if (takenChars.contains(getCell(r,c).getValue()) && getCell(r,c).getValue()!='.') {
					return false;
				}
			takenChars.add(getCell(r,c).getValue());
			}
		}
		return true;
	}
	/**
	 * Ser etter tomme plasser på brettet.
	 * @return gir true hvis det ikke er flere tomme plasser, false ellers
	 */
	private boolean checkEmptySpots() {
		for (int r=0; r<9; r++) {
			for (int c=0; c<9; c++) {
				if (getCell(r,c).getValue()=='.') {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Sjekker om spillet er vunnet.
	 * @return true hvis spillet er over, false ellers
	 */
	private boolean hasWon() {
		if (checkEmptySpots() && checkHorisontal() && checkVertical()) {
			return true;
		}
		return false;
	}
	/**
	 * Tar input og bruker det som parametere i metoder under spill og utfører spillet.
	 * @param in er bruker input som skal være på formen "<rad><kolonne><verdi>"
	 */
	public void getInput(String in) {
		if (hasWon()) {
			return;
		}
		if (((in.length()!=3 && in.length()!=4) || !in.substring(0,3).matches("[0-9]+"))){
			System.out.println("The string has to be of format '<row><column><value>'");
			return;
		}
		if (in.length()==3) {
			this.nextRow = Character.getNumericValue(in.charAt(0));
			this.nextCol = Character.getNumericValue(in.charAt(1));
			this.nextValue = in.charAt(2);
			this.setCertain(this.nextRow, this.nextCol, this.nextValue);
		}
		else if (in.charAt(3)=='*'){
			this.nextRow = Character.getNumericValue(in.charAt(0));
			this.nextCol = Character.getNumericValue(in.charAt(1));
			this.nextValue = in.charAt(2);
			this.setUncertain(this.nextRow, this.nextCol, this.nextValue);
		}
	}
	/**
	 * Plays the game, stops when the game is won
	 */
	
	public void play() {
		System.out.println("Welcome to Sudoku!");
		System.out.println("This game was created by Casper Feng, the 10th of February, 2018.");
		System.out.println("Enjoy yourself :)");
		System.out.println(this);
		while (!hasWon()) {
			System.out.print("Please enter the position and value for the cell '<row><column><value>' (0-indexed), mark with '*' next to value if unsure: ");
			String pos = in.nextLine();
			getInput(pos);
			System.out.println(this);
		}
		System.out.println("Congratulations you won!");
	}
}
		
	
	
	