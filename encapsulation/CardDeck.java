package encapsulation;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author casperfeng created the 11/2/18
 *
 */
public class CardDeck {
	
    protected List<Card> cardDeck;
/**
 * Constructor for the card deck, used to fill carddeck.
 * @param n is amount of cards from each suit
 */
    public CardDeck(int n){
        cardDeck = new ArrayList<>();
        char suit = '1';
        for (int i = 0; i<4;i++){
            if (i == 0) suit = 'S';
            else if(i == 1) suit = 'H';
            else if(i == 2) suit = 'D';
            else if(i == 3) suit = 'C';

            for (int j = 0; j<n;j++){
                cardDeck.add(new Card(suit, j+1));
            }
        }
    }
/**
 * getter-method for the amount of cards.
 * @return the size of the card deck
 */
    public int getCardCount(){
        return cardDeck.size();
    }
/**
 * getter-method for the card.
 * @param n is what card
 * @return
 */
    public Card getCard(int n){
        if (n < 0 || n > getCardCount()-1){
            throw new IllegalArgumentException();
        }
        return cardDeck.get(n);
    }
/**
 * deals the hand
 * @param hand
 * @param n is amount of cards
 */

    public void deal(CardHand hand, int n){
        for (int i = 0; i<n;i++){
            Card card = cardDeck.get(getCardCount()-1);
            cardDeck.remove(card);
            hand.addCard(card);
        }
    }
/**
 * Shuffles the deck perfectly
 */
    public void shufflePerfectly(){
        List<Card> half1 = new ArrayList<>();
        List<Card> half2 = new ArrayList<>();
        half1.addAll(cardDeck.subList(0, getCardCount() / 2));
        half2.addAll(cardDeck.subList(getCardCount() / 2, getCardCount()));
        int n = getCardCount(),i = 0, h1 = 0, h2 = 0;
       
        cardDeck.clear();
        while (i < n){
            if (i%2 == 0){
                cardDeck.add(half1.get(h1));
                h1++;
            }
            else {
                cardDeck.add(half2.get(h2));
                h2++;
            }
            i++;
        }
    }
    /**
     * Returns the card deck as string representations.
     */
    public String toString(){
        return cardDeck.toString();
    }

    public static void main(String[] args) {
        CardDeck cd = new CardDeck(4);
        System.out.println(cd.cardDeck.get(0).toString());
        cd.shufflePerfectly();
        System.out.println(cd);
    }
}