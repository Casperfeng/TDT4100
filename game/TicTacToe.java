package game;
import java.util.*;
import java.io.*; //InputStream og OutputStream bibloteket
/**
 * created 11/2/18
 * @author casperfeng created
 * TODO - FIX UNDO-method call when player has moved!!! FIXED
 * SAVE, UNDO AND REDO COMPLETED
 */
public class TicTacToe implements TicTacToeFileHandler{
	private char[][] board;
	private Stack<TicTacMove> undo = new Stack();
	private Stack<TicTacMove> redo = new Stack();
	private boolean hasMoved;
	/**
	 * Konstruktør for tomt brett 3x3.
	 */
	public TicTacToe() {
		char[][] newBoard = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
		this.board = newBoard;
	}
	
	/**
	 * tar inn et "lagret" brett og fortsetter å spille på det
	 * @param savedBoard er det lagrede brettet
	 */
	
	public TicTacToe(String savedBoard) {
		if (savedBoard.length() != 9) {
			throw new IllegalArgumentException("The board has to be of length 9");
		}
		char[][] newBoard = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
		int currentIndex = 0;
		for (int r = 0; r < 3; r++) {
			for (int c=0; c < 3; c++ ) {
				newBoard[r][c] = savedBoard.charAt(currentIndex);
				currentIndex++;
			}
		}
		this.board = newBoard;
	}
	
	/**
	 * endrer hele brettet
	 * @param savedBoard
	 */
	public void SetTicTacToe(String savedBoard) {
		if (savedBoard.length() != 9) {
			throw new IllegalArgumentException("The board has to be of length 9");
		}
		char[][] newBoard = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
		int currentIndex = 0;
		for (int r = 0; r < 3; r++) {
			for (int c=0; c < 3; c++ ) {
				newBoard[r][c] = savedBoard.charAt(currentIndex);
				currentIndex++;
			}
		}
		this.board = newBoard;
		if (!hasMoved) { //sjekker om redo blir kalt eller om det er et vanlig trekk (redo setter alltid hasMoved til true før den kalles)
			redo.removeAllElements(); //tømmer redo
		}
	}
	
	/**
	 * Returnerer cellen som er på angitt posisjon.
	 * @param x posisjon langs x aksen
	 * @param y posisjon langs y aksen
	 * @return verdien til cellen
	 */
	public  char getCell(int x, int y) {
		return this.board[x-1][y-1];
	}
	/**
	 * Setter-metode for å endre en tom rute til angitt verdi.
	 * @param c er verdien til cellen
	 * @param x er posisjon langs x aksen
	 * @param y er posisjon langs y aksen
	 * @return true hvis en endring ble utført, false ellers
	 */
	public boolean setCell(char c, int x, int y) {
		if (x>3 || x<1 || y>3 || y<1) {
			return false;
		}
		this.board[x-1][y-1] = c;
		undo.push(new TicTacMove(c, x, y)); //legger til et TicTacMove-objekt inn i stacken
		return true;
	}
	/**
	 * Sjekker om cellen er okkupert.
	 * @param x er posisjonen langs x aksen
	 * @param y er posisjonen langs y aksen
	 * @return true hvis cellen er okkupert, false ellers.
	 */
	
	public boolean isOccupied(int x, int y) {
		if (this.board[x-1][y-1]!=' ') {
			return true;
		}
		return false;
		
	}
	/**
	 * Sjekker hvem sin tur det er
	 * @return spilleren som har tur, angitt ved char.
	 */
	public char getCurrentPlayer() {
		int xCounter=0;
		int oCounter=0;
		for (int i=0; i<3; i++) {
			for (int j=0;j<3;j++) {
				if (this.board[i][j]=='x') {
					xCounter++;
				}
				else if(this.board[i][j]=='o') {
					oCounter++;
				}
			}
		}
		if (xCounter > oCounter) {
			return 'o';
		}
		return 'x';
	}
	
	/**
	 * Printer ut brettet
	 */
	public String toString() {
		return (" " + this.board[0][0] + " | " + this.board[0][1] + " | " + this.board[0][2] 
				+ " \n-----------\n" 
				+ " " +this.board[1][0] + " | " + this.board[1][1] + " | " + this.board[1][2] + 
				" \n-----------\n" 
				+" " + this.board[2][0] + " | " + this.board[2][1] + " | " + this.board[2][2] + " \n");
	}
	/**
	 * Sjekker om spillet har en vinner.
	 * @param c sjekker om c har vunnet.
	 * @return true hvis det er slutt, false ellers.
	 */
	public boolean isWinner(char c) {
		for (int row = 1;row<=3;row++) {
			if (((this.getCell(row,1)==this.getCell(row,2)) && (this.getCell(row,2)==this.getCell(row,3))) && this.getCell(row,1)==c){
				return true;
			}
			else if (((this.getCell(1,row)==this.getCell(2,row)) && (this.getCell(2,row)==this.getCell(3,row))) && this.getCell(1,row)==c) {
				return true;
			}
			else if (((this.getCell(1,1)==this.getCell(2,2)) && (this.getCell(2,2)==this.getCell(3,3))) && this.getCell(2,2)==c) { 
				return true;
			}
			else if (((this.getCell(3,1)==this.getCell(2,2)) && (this.getCell(2,2)==this.getCell(1,3))) && this.getCell(2,2)==c) {
				return true;
			}
		}
		return false;
		}
		/**
		 * Sjekker om spillet har en vinner.
		 * @return true hvis spillet har en vinner, false ellers.
		 */
	public boolean hasWinner() {
		if (isWinner('x')) {
			return true;
		}
		else if (isWinner('o')) {
			return true;
		}
		return false;
	}
	/**
	 * Sjekker om spillet er ferdig spilt.
	 * @return true hvis spillet er ferdig, false ellers.
	 */
	public boolean isFinished() {
		if (hasWinner()) {
			return true;
		}
		for (int i=1; i<4; i++) {
			for (int j=1;j<4;j++) {
				if (this.getCell(i,j)==' ') {
					return false;
				}
			}
		}
		return true;
		
	}
	/**
	 * Tar inn bruker input på formatet '<rad><kolonne>'
	 * @param in er strengen som blir satt inn.
	 */
	
	public void getInput(String in) {
		if (isFinished()) {
			return;
		}
		if (in.length()!= 2 && !in.equals("u") && !in.equals("r")) {
			throw new IllegalArgumentException("The input has to be <row><column>, u(undo), or r(redo)");
		}
		if (in.equals("u")) {
			this.undo();
			return;
		}
		else if (in.equals("r")) {
			this.redo();
			return;
		}
		int nextX = Character.getNumericValue(in.charAt(0));
		int nextY = Character.getNumericValue(in.charAt(1));
		if (!this.isOccupied(nextX, nextY)) {
			this.setCell(getCurrentPlayer(), nextX, nextY);
		}
	}
	
	/**
	 * angrer et trekk
	 */
	
	public void undo() {
			redo.push(undo.peek()); //redo legger til undo sin øverste verdi UTEN å fjerne den fra undo
			TicTacMove trekk = undo.pop();
			this.setCell(' ', trekk.getX(), trekk.getY()); //setter cellen blank
			undo.pop(); //må gjøres fordi når setCell kalles i linjen over, legger den til et trekk objekt vi ikke skal ha (det er ikke et ordentlig trekk)
	}
	
	/**
	 * gjør om en angring (går tilbake til det du gjorde før angringen)
	 */
	public void redo() {
			hasMoved = true;
			TicTacMove trekk = redo.pop();
			this.setCell(trekk.getChar(), trekk.getX(), trekk.getY()); //gjør om en angring (utfører et trekk)
			hasMoved = false;
	}
	
	/**
	 * printer ut nåværende tilstand til board
	 * @return en streng bestående av tilstanden
	 */
	
	public String currentBoard() {
		String board = "";
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				board += this.board[r][c];
			}
		}
		return board;
	}
	
	/**
	 * Skriver brettets tilstand på en fil, fileName
	 */
	@Override
	public void save(String fileName) {
		// TODO Auto-generated method stub
		String savedBoard = this.currentBoard();
		try {
			PrintWriter writer = new PrintWriter("/Users/casperfeng/tdt4100-2018-master/git/tdt4100-2018/ovinger/src/game/" + fileName);
			writer.println(savedBoard);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Leser brettets tilstand fra en fil, fileName
	 */
	@Override
	public void load(String fileName) {
		// TODO Auto-generated method stub
		try {
			BufferedReader reader = new BufferedReader(new FileReader("/Users/casperfeng/tdt4100-2018-master/git/tdt4100-2018/ovinger/src/game/" + fileName));
			String board = reader.readLine();
			this.SetTicTacToe(board);
			reader.close();
		}
		catch (IOException e) {
			e.getStackTrace();
		}
	}

}
