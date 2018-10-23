package testing;

import org.junit.*;

import static org.junit.Assert.*;

import java.util.*;

import junit.framework.TestCase;

public class CardDeckTest extends TestCase{
	
	private CardDeck deck;
	
	@Before
	public void setUp() {
		deck = new CardDeck(2);
	}
	
	@Test
	public void testConstuctor() {
		try {
			deck = new CardDeck(14);
			fail("Illegal size of deck");
		}
		catch (IllegalArgumentException e) {
		}
		catch (Exception e) {
			fail("Expected IllegalArgumentException but got : " + e.getClass());
		}
	}
	
	@Test
	public void testGetCard() {
		try {
			Card c = deck.getCard(50);
			fail("Shouldnt be able to get a card number higher than amount of cards");
		}
		catch(IllegalArgumentException e) {
		}
		catch(Exception e) {
			fail("Expected IllegalArgumentException, but got : "  + e.getClass());
		}
	}
	
	@Test
	public void testShufflePerfectly() {
		deck.shufflePerfectly();
		List<Card> shuffledDeck = Arrays.asList(new Card('S', 1), new Card('D', 1), new Card('S', 2), new Card('D', 2), new Card('H', 1), new Card('C', 1), new Card('H', 2), new Card('C', 2));
		assertEquals(shuffledDeck, deck);
	}
	
	@Test
	public void testCardCount() {
		assertEquals(8, deck.getCardCount());
	}
	
	

}
