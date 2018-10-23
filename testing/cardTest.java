package testing;

import org.junit.*;
import static org.junit.Assert.*;

import junit.framework.TestCase;

public class cardTest extends TestCase{
	
	private Card card, card2;
	
	@Before
	public void setUp() {
		card = new Card('S', 1);
	}
	
	@Test
	public void testConstructor() {
		char sym = 0;
		int num = 0;
		
		try {
			for (char c : new char[] {'S', 'H', 'D', 'C'}) {
				for (int i = 1; i <= 13; i++) {
					sym = c;
					num = num;
					card = new Card(c, i);
				}
			}
		}
		catch (Exception e) {
			fail("The card: " + "<" + sym + ", " + num + "> should be allowed, but got Exception: " + e.getClass());
		}
		
		try {
			card = new Card('H', 14);
			fail("constructor allowed illegal face!");
		}
		catch (Exception e) {}
		
		try {
			card = new Card('A', 5);
			fail("constructor allowed illegal suit!");
		}
		catch (Exception e) {}
	}
	
	@Test
	public void testGetters() {
		assertEquals('S', card.getSuit());
		assertEquals(1, card.getFace());
	}
	
}
