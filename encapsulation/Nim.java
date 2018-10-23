package encapsulation;

public class Nim {
	
	private int[] stacks;
	
	public Nim(int amount) {
		int[] piles = {amount,amount,amount};
		this.stacks = piles;
		
	}
	
	public Nim() {
		int[] piles = {10,10,10};
		this.stacks = piles;
	}
	
	public void removePieces(int number, int targetPile) { 
		if (isGameOver()) {
			throw new IllegalStateException("Game is over");
		}
		else if (! isValidMove(number, targetPile)) {
			throw new IllegalArgumentException("Move not allowed!");
		}
		else {
			this.stacks[targetPile] -= number;
		}
	}
	
	public boolean isValidMove(int number, int targetPile) {
		if (number < 1 || targetPile > 2 || (this.stacks[0] < number) || (this.stacks[1] < number) || (this.stacks[2] < number)) {
			return false;
		}
		return true;
	}
	
	public boolean isGameOver() {
		for (int i=0; i<3; i++) {
			if (this.stacks[i] == 0) {
				return true;
			}
		}
		return false;
	}
	
	public int getPile(int targetPile) {
		return this.stacks[targetPile];
	}
	
	public String toString() {
		return ("Pile 1: " + this.stacks[0] + " Pile 2: " + this.stacks[1] + " Pile 3: " + this.stacks[2]);
	}

}
