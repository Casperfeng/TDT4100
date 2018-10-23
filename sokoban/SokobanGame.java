package sokoban;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * 23/2/18
 * @author casperfeng
 * Sokoban game logic (also the game logic)
 * NOTE to READERS: should have implemented a set value and set type method early on
 * TODO - FIX UNDO-method call when player has moved!!!
 */
public class SokobanGame {
	private int playerRow = 0, playerCol = 0; //saves the position of player
	private final Stack<Push> undo, redo; //undo and redo pushes/moves
	private final ArrayList<ArrayList<Cell>> board; //2D ArrayList containing all cells
	private int height = 0, width = 0; //saves the height and width of the level board
	public ArrayList<ArrayList<Integer>> goals; //2D saves the row and column position of goal
	private boolean hasMoved; //brukes til å sjekke om et trekk har blitt utført (nødvendig i redo)
	
	public SokobanGame(String premadeBoard) {
		goals = new ArrayList<ArrayList<Integer>>();
		undo = new Stack<Push>(); //Stack-object to undo actions 
		redo = new Stack<Push>(); //Stack-object to redo actions
		width = premadeBoard.indexOf('\n'); //finds the width of board
		int n = premadeBoard.length(); //the amount of characters in the String (used to get the height of the board)
		
		for (int i = 0; i < n; i++) { //iterates through the premadeBoard String
			if (premadeBoard.charAt(i) == '\n') { //checks for newLine
				height++;
			}
		}
		
		height++; // has to adjust for the last row not having a newline
		board = new ArrayList<ArrayList<Cell>>();
		String strippedBoard = premadeBoard.replaceAll("\n",""); //makes it easier to iterate through the premade Board (removes all new lines)
		n = strippedBoard.length(); //updates n to the new length
		int currentIndex = 0; //manages the index while iterating through the board (easier than managing row + column)
		for (int r = 0; r < height; r++) { //iterates through the rows
			board.add(new ArrayList<Cell>()); //adds a new row 
			for (int c = 0; c < width; c++) { //iterates through the columns
				char currentChar = strippedBoard.charAt(currentIndex); //saves the current character (used for constructing cells)
				CellMaker(currentChar, r, c); //adds cell to board				
				currentIndex++; //Every time the inner loop finishes, the index goes up by one (could probably have been done better)
			}
		}
				
	}
	
	
	/**
	 * Helper function for the constructor
	 * makes the cells with the corresponding value, also saves the positions
	 * @param value is the value (char) for the cell
	 * @param r is the current row for the cell
	 * @param c is the current column for the cell
	 */
	
	private void CellMaker(char value, int r, int c) {
		ArrayList<Integer> places = new ArrayList<Integer>(Arrays.asList(r,c)); //creates a list that saves the row and column of goal
		if (value == '#') { //checks for wall
			board.get(r).add(new Cell(value, "Wall")); //creates wall
		}
		
		else if (value == '.') { //checks for goal
			board.get(r).add(new Cell(value, "Goal")); //creates goal
			goals.add(places); //Adds the values to goals - a list that contains all the positions of goals (2D list configured this way: [ [x-pos to goal1, y-pos to goal1], [x-pos to goal2, y-pos to goal2],...]}
		}
		
		else if (value == '$') { //checks for box
			board.get(r).add(new Cell(value, "Box")); // creates box
		}
		
		else if (value == '*') {
			board.get(r).add(new Cell(value, "Box on goal")); //creates box on goal, saves the position
			places = new ArrayList<Integer>(Arrays.asList(r,c)); //creates a list that saves the row and column of goal
			goals.add(places); //adds the list
		}
		
		else if (value == '@') { //checks for player
			board.get(r).add(new Cell(value, "Player")); //creates player, saves the position
			this.playerRow = r; this.playerCol = c; //saves position of player
		}
		
		else if (value == '+') { //checks for player on goal
			board.get(r).add(new Cell(value, "Player on goal")); //creates player on goal, saves the position
			this.playerRow = r; this.playerCol = c; //saves position of player
			places = new ArrayList<Integer>(Arrays.asList(r,c));//creates a list that saves the row and column of goal
			goals.add(places); //adds the list (see up for details)
			board.get(r).add(new Cell(value, "Empty")); //creates empty field
		}
		
		else if (value == ' ') { //checks for empty slot
			board.get(r).add(new Cell(value, "Empty"));
		}
	}
	
	/**
	 * Returns the row of player
	 * @return current row of player
	 */
	public int getPlayerRow() {
		return this.playerRow;
	}
	
	/**
	 * Returns the col of a player
	 * @return current col of player
	 */
	public int getPlayerCol() {
		return this.playerCol;
	}
	
	/**
	 * Returns the amount of goals in a board
	 * @return the amount of goals (helps me keep track of how many goals - this is needed in updateGoals
	 */
	
	/**
	 * Helper function - updates the symbols in case player moves on goal or box is on goal - Must be implemented in game logic
	 * Assumes Move implements a very simple "move" - method
	 * Cells get overwritten - cells that are moved from a spot means the spot will be ' '
	 */
	
	private void updateGoals() { 
		for (int r = 0; r < height; r++) { //iterates through the rows
			for (int c = 0; c < width; c++) { //iterates through the columns
					
				if (goals.contains(Arrays.asList(r,c))) { //when the row and column has a goal - then check... (checks below)
						
					if (getCell(r,c).getValue() == '$') { //if the position is marked as box 
						getCell(r,c).setValue('*'); //updates the value
						getCell(r,c).setType("Box on goal"); //updates type
					}
					
					else if (getCell(r,c).getValue() == '@') { //if the position is marked as player
						getCell(r,c).setValue('+'); //updates the value
						getCell(r,c).setType("Player on goal"); //updates type
					}
						
					else if (getCell(r,c).getValue() == ' ') { //if the position is marked as empty
						getCell(r,c).setValue('.'); //updates the value
						getCell(r,c).setType("Goal"); //updates type					
						}
					}
				
					if (!(goals.contains(Arrays.asList(r,c)))){ //checks every position that is NOT a goal
						if (getCell(r,c).getValue() == '.') { //checks if the position is a goal
							getCell(r,c).setTypeValue("Empty", ' ');
						}
						else if (getCell(r,c).getValue() == '*') { //checks if the position is a Box on goal
							getCell(r,c).setTypeValue("Box", '$');
						}
				}
			}
		}
	}
	
	/**
	 * Takes in direction and moves the cells accordingly
	 * Leaves previous cells as "empty" 
	 * @param direction is either "w" - up, "a" - left, "s" - down, "d" - right
	 */
	
	public void move(String direction) {
		int mode = isValidMove(direction);
		
		if (mode == 0) { //if the move is not valid - It stops the method from performing actions
			System.out.println("Invalid move!");
			return;
		}
		switch (direction) { //instead of writing if/else if
		case "w": //move up
			if (mode == 1) { //if player is moving to an empty piece
				board.get(this.playerRow).get(this.playerCol).setType("Empty"); //cell player moved from
				board.get(this.playerRow).get(this.playerCol).setValue(' '); //cell player moved from
				board.get(this.playerRow - 1).get(this.playerCol).setType("Player"); //empty cell is updated to player type
				board.get(this.playerRow - 1).get(this.playerCol).setValue('@'); //empty cell is updated to player value
				this.playerRow--; //updates player position
				undo.push(new Push(false, direction)); //adds a new Push to the Stack - sets the isPushing to false
				break;
			}
			else if (mode == 2) { //if player is pushing a object
				board.get(this.playerRow).get(this.playerCol).setType("Empty"); //cell player moved from
				board.get(this.playerRow).get(this.playerCol).setValue(' '); //cell player moved from
				board.get(this.playerRow - 2).get(this.playerCol).setType(board.get(this.playerRow - 1).get(this.playerCol).getType()); //the cell object is pushed onto gets the object type
				board.get(this.playerRow - 2).get(this.playerCol).setValue(board.get(this.playerRow - 1).get(this.playerCol).getValue()); //the cell object is pushed onto gets the object value
				board.get(this.playerRow - 1).get(this.playerCol).setType("Player"); //the cell that player moves to gets player type
				board.get(this.playerRow - 1).get(this.playerCol).setValue('@'); //the cell that player moves to gets player value
				this.playerRow--; //updates player position
				undo.push(new Push(true, direction)); //adds a new Push to the Stack - sets the isPushing to true
				break;
			}
		case "a": //move left
			if (mode == 1) { //if player is moving to an empty piece
				board.get(this.playerRow).get(this.playerCol).setType("Empty"); //cell player moved from
				board.get(this.playerRow).get(this.playerCol).setValue(' '); //cell player moved from
				board.get(this.playerRow).get(this.playerCol - 1).setType("Player"); //empty cell is updated to player type
				board.get(this.playerRow).get(this.playerCol - 1).setValue('@'); //empty cell is updated to player value
				this.playerCol--; //updates player position
				undo.push(new Push(false, direction)); //adds a new Push to the Stack - sets the isPushing to false
				break;
			}
			else if (mode == 2) { //if player is pushing a object
				board.get(this.playerRow).get(this.playerCol).setType("Empty"); //cell player moved from
				board.get(this.playerRow).get(this.playerCol).setValue(' '); //cell player moved from
				board.get(this.playerRow).get(this.playerCol - 2).setType(board.get(this.playerRow).get(this.playerCol - 1).getType()); //the cell object is pushed onto gets the object type
				board.get(this.playerRow).get(this.playerCol - 2).setValue(board.get(this.playerRow).get(this.playerCol - 1).getValue()); //the cell object is pushed onto gets the object value
				board.get(this.playerRow).get(this.playerCol - 1).setType("Player"); //the cell that player moves to gets player type
				board.get(this.playerRow).get(this.playerCol - 1).setValue('@'); //the cell that player moves to gets player value
				this.playerCol--; //updates player position
				undo.push(new Push(true, direction)); //adds a new Push to the Stack - sets the isPushing to true
				break;
			}
		case "s": //move down
			if (mode == 1) { //if player is moving to an empty piece
				board.get(this.playerRow).get(this.playerCol).setType("Empty"); //cell player moved from
				board.get(this.playerRow).get(this.playerCol).setValue(' '); //cell player moved from
				board.get(this.playerRow + 1).get(this.playerCol).setType("Player"); //empty cell is updated to player type
				board.get(this.playerRow + 1).get(this.playerCol).setValue('@'); //empty cell is updated to player value
				this.playerRow++; //updates player position
				undo.push(new Push(false, direction)); //adds a new Push to the Stack - sets the isPushing to false
				break;
			}
			else if (mode == 2) { //if player is pushing a object
				board.get(this.playerRow).get(this.playerCol).setType("Empty"); //cell player moved from
				board.get(this.playerRow).get(this.playerCol).setValue(' '); //cell player moved from
				board.get(this.playerRow + 2).get(this.playerCol).setType(board.get(this.playerRow + 1).get(this.playerCol).getType()); //the cell object is pushed onto gets the object type
				board.get(this.playerRow + 2).get(this.playerCol).setValue(board.get(this.playerRow + 1).get(this.playerCol).getValue()); //the cell object is pushed onto gets the object value
				board.get(this.playerRow + 1).get(this.playerCol).setType("Player"); //the cell that player moves to gets player type
				board.get(this.playerRow + 1).get(this.playerCol).setValue('@'); //the cell that player moves to gets player value
				this.playerRow++; //updates player position
				undo.push(new Push(true, direction)); //adds a new Push to the Stack - sets the isPushing to true
				break;
			}
			
		case "d": //move right
			if (mode == 1) { //if player is moving to an empty piece
				board.get(this.playerRow).get(this.playerCol).setType("Empty"); //cell player moved from
				board.get(this.playerRow).get(this.playerCol).setValue(' '); //cell player moved from
				board.get(this.playerRow).get(this.playerCol + 1).setType("Player"); //empty cell is updated to player type
				board.get(this.playerRow).get(this.playerCol + 1).setValue('@'); //empty cell is updated to player value
				this.playerCol++; //updates player position
				undo.push(new Push(false, direction)); //adds a new Push to the Stack - sets the isPushing to false
				break;
			}
			else if (mode == 2) { //if player is pushing a object
				board.get(this.playerRow).get(this.playerCol).setType("Empty"); //cell player moved from
				board.get(this.playerRow).get(this.playerCol).setValue(' '); //cell player moved from
				board.get(this.playerRow).get(this.playerCol + 2).setType(board.get(this.playerRow).get(this.playerCol + 1).getType()); //the cell object is pushed onto gets the object type
				board.get(this.playerRow).get(this.playerCol + 2).setValue(board.get(this.playerRow).get(this.playerCol + 1).getValue()); //the cell object is pushed onto gets the object value
				board.get(this.playerRow).get(this.playerCol + 1).setType("Player"); //the cell that player moves to gets player type
				board.get(this.playerRow).get(this.playerCol + 1).setValue('@'); //the cell that player moves to gets player value
				this.playerCol++; //updates player position
				undo.push(new Push(true, direction)); //adds a new Push to the Stack - sets the isPushing to true
				break;
			}
		}
		updateGoals(); //updates the goals
		if (!hasMoved) { //hvis spilleren beveger seg så skal redo tømmes (denne settes alltid true når redo kalles)
			redo.removeAllElements(); //tømmer hele Stacken
		}
	}
	
	/**
	 * Checks if the move is valid (helper function used in the move - method)
	 * FOUND ERROR - PUSHING BLOCK ONTO GOAL //FIXED 28/2/18
	 * @param direction is either "w" - up, "a" - left, "s" - down, "d" - right
	 * @return 0 - if non valid move, 1 - if normal move, 2 - if pushing
	 */
	
	private int isValidMove(String direction) { 
		if (direction.equals("w")) {
			if (board.get(this.playerRow - 1).get(playerCol).getType().equals("Empty") || board.get(this.playerRow - 1).get(playerCol).getType().equals("Goal")) { //checks if the cell over is empty or a goal
				return 1;
			}
			else if (board.get(this.playerRow - 1).get(this.playerCol).getType().equals("Wall") ) { //IMPORTANT if this doesnt get checked one can get OutOfIndexException below
				return 0;
			}
			else if (board.get(this.playerRow - 2).get(playerCol).getType().equals("Empty") || board.get(this.playerRow - 2).get(playerCol).getType().equals("Goal")) { //checks if the player can push an object
				return 2;
			}
		}
		else if (direction.equals("a")) {
			if (board.get(this.playerRow).get(playerCol - 1).getType().equals("Empty") || board.get(this.playerRow).get(playerCol - 1).getType().equals("Goal")) { //checks if the cell over is empty or a goal
				return 1;
			}
			else if (board.get(this.playerRow).get(this.playerCol - 1).getType().equals("Wall")) { //IMPORTANT if this doesnt get checked one can get OutOfIndexException below
				return 0;
			}
			else if (board.get(this.playerRow).get(playerCol - 2).getType().equals("Empty") || board.get(this.playerRow).get(playerCol - 2).getType().equals("Goal")) { //checks if the player can push an object
				return 2;
			}
		}
		else if (direction.equals("s")) {
			if (board.get(this.playerRow + 1).get(playerCol).getType().equals("Empty") || board.get(this.playerRow + 1).get(playerCol).getType().equals("Goal")) { //checks if the cell over is empty or a goal
				return 1;
			}
			else if (board.get(this.playerRow + 1).get(this.playerCol).getType().equals("Wall")) { //IMPORTANT if this doesnt get checked one can get OutOfIndexException below
				return 0;
			}
			else if (board.get(this.playerRow + 2).get(playerCol).getType().equals("Empty") || board.get(this.playerRow + 2).get(playerCol).getType().equals("Goal") ) { //checks if the player can push an object
				return 2;
			}
		}
		else if (direction.equals("d")) {
			if (board.get(this.playerRow).get(playerCol + 1).getType().equals("Empty") || board.get(this.playerRow).get(playerCol + 1).getType().equals("Goal")) { //checks if the cell over is empty or a goal
				return 1;
			}
			else if (board.get(this.playerRow).get(this.playerCol + 1).getType().equals("Wall")) { //IMPORTANT if this doesnt get checked one can get OutOfIndexException below
				return 0;
			}
			else if (board.get(this.playerRow).get(playerCol + 2).getType().equals("Empty") || board.get(this.playerRow).get(playerCol + 2).getType().equals("Goal")) { //checks if the player can push an object
				return 2;
			}
		}
		return 0;		
	}
	
	/**
	 * prints out the board
	 */
	public String toString() {
		String out = "";
		for (int r = 0; r < height; r++) { //iterates through the rows
			for (int c = 0; c < width; c++) {
				out += getCell(r,c).toString(); //adds every cell as String
			}
			out += "\n";
		}
			return out;
	}

	/**
	 * 
	 * @returns the height of the board
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * 
	 * @returns the width of the board
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * 
	 * @return the goal-ArrayList (the list that contains all the positions of goals)
	 */
	
	public ArrayList<ArrayList<Integer>> getGoals(){
		return this.goals;
	}
	
	/**
	 * 
	 * @param r is the row of the cell
	 * @param c is the column of the cell
	 * @returns the cell
	 */
	public Cell getCell(int r, int c) {
		return board.get(r).get(c);
	}
	
	/**
	 * function that allows you to undo an action
	 */
	
	public void undo() {
		redo.push(undo.peek()); //adds the actions of undo
		String oppositeDirection; //used to move the "opposite way" (same as undoing a move - but doesn't account for barrels moving back)
		Push interaction = undo.pop(); //checks the previous interaction done
		switch(interaction.getDirection()) { //moves the opposite direction of direction
		case "w": oppositeDirection = "s"; move(oppositeDirection); undo.pop(); break; //moves the opposite direction of direction
		case "a": oppositeDirection = "d"; move(oppositeDirection); undo.pop(); break; //moves the opposite direction of direction
		case "s": oppositeDirection = "w"; move(oppositeDirection); undo.pop(); break; //moves the opposite direction of direction
		case "d": oppositeDirection = "a"; move(oppositeDirection); undo.pop(); break; //moves the opposite direction of direction
		default: oppositeDirection = ""; break;  //default case - break
		}
		if (interaction.isPushable()) { //checks if the action is performing a push			
			switch(oppositeDirection) {
			case "w": //if the player moved up
				getCell(playerRow + 1, playerCol).setTypeValue(getCell(playerRow + 2, playerCol).getType(), getCell(playerRow + 2, playerCol).getValue()); //moves the box up
				getCell(playerRow + 2, playerCol).setTypeValue("Empty", ' ');
				break;
			case "a": //if the player moved left 
				getCell(playerRow, playerCol + 1).setTypeValue(getCell(playerRow, playerCol + 2).getType(), getCell(playerRow, playerCol + 2).getValue()); //moves the box up
				getCell(playerRow, playerCol + 2).setTypeValue("Empty", ' ');
				break;
			case "s": //if the player moved down
				getCell(playerRow - 1, playerCol).setTypeValue(getCell(playerRow - 2, playerCol).getType(), getCell(playerRow - 2, playerCol).getValue()); //moves the box up
				getCell(playerRow - 2, playerCol).setTypeValue("Empty", ' ');
				break;
			case "d": //if player moved right
				getCell(playerRow, playerCol - 1).setTypeValue(getCell(playerRow, playerCol - 2).getType(), getCell(playerRow, playerCol - 2).getValue()); //moves the box up
				getCell(playerRow, playerCol - 2).setTypeValue("Empty", ' ');
				break;
			}
		}
		updateGoals();
		
		
	}
	
	/**
	 * function redos an undo
	 */
	public void redo() {
		hasMoved = true;
		Push interaction = redo.pop();
		move(interaction.getDirection());
		hasMoved = false;
	}
	
	/**
	 * checks if the game is won
	 * @return true if game is won , false if not
	 */
	
	public boolean hasWon() {
		for (ArrayList<Integer> goal: goals) {
			if (!getCell(goal.get(0), goal.get(1)).getType().equals("Box on goal")) {
				return false;
			}
		}
		return true;
	}
	
}
