package game;

public class SudokuProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String tester =  ".....2..38.273.45....6..87.9.8..5367..6...1..4513..9.8.84..3....79.512.62..8.....";
		Sudoku spill = new Sudoku(tester);
		spill.play();
	}

}
