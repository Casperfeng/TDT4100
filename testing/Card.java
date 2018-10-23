package testing;

import java.util.Arrays;
import java.util.List;

public class Card {

	private final char SUIT;
	private final int FACE;
	private List<Character> legalSuits = Arrays.asList('S', 'H', 'D', 'C');

	public Card(char suit, int face) {
		if (!legalSuits.contains(suit) ) {
			throw new IllegalArgumentException("Illegal suit value");
		}
		if (face < 1) {
			throw new IllegalArgumentException("Illegal face value");
		}
		this.SUIT = suit;
		this.FACE = face;
	}

	public char getSuit() {
		return SUIT;
	}
	public int getFace() {
		return FACE;
	}

	public String toString() {
		return String.valueOf(SUIT) + String.valueOf(FACE);
	}
}
