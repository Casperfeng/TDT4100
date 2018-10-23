package testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CardDeck {

	private List<Card> deck = new ArrayList<>();
	private List<Character> suits = Arrays.asList('S', 'H', 'D', 'C');

	public CardDeck(int n) {
		for (int j = 0; j<4; j++) {
			for (int i = 1; i < n; i++) {
				Card card = new Card(suits.get(j), i);
				deck.add(card);
			}
		}
	}

	public int getCardCount() {
		return deck.size();
	}

	public Card getCard(int n) {
		try{
			return deck.get(n);
		}
		catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException();
		}
	}

	public void shufflePerfectly() {
		 int bottom = 0;
		 int middle = deck.size()/2;
		 while (middle < deck.size()) {
			deck.set(bottom, deck.get(bottom));
			deck.set(bottom+1, deck.get(middle));
			bottom ++;
			middle ++;
		}
	}

}
