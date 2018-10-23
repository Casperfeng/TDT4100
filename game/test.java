package game;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TicTacToe game = new TicTacToe(" x o x   ");
		System.out.println(game);
		game.save("Beatrice");
		TicTacToe newGame = new TicTacToe();
		newGame.load("Beatrice");
		System.out.println(newGame);
		
		

	}

}
