package encapsulation;

import java.util.ArrayList;

/**
 * 
 * @author casperfeng created 11/2/18
 *
 */
public class CardHand extends CardDeck {
	/**
	 * Konstruktør for korthånd
	 */
    public CardHand(){
        super(0);
    }
    /**
     * Legger til kort i kortstokken
     * @param card er et kort som legges til
     */
    public void addCard(Card card){
        cardDeck.add(card);
    }
    /**
     * 
     * @param n
     * @return
     */
    public Card play(int n){
        if (n<0 || n > getCardCount()){
            throw new IllegalArgumentException();
        }
        Card card = cardDeck.get(n);
        cardDeck.remove(card);
        return card;
    }
}