package wompoo.eric.com.wompoo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Eric on 11/10/2015.
 */
public class DeckOfCards {
    private List<Card> cards;

    public DeckOfCards() {
        this.cards = new ArrayList<Card>();
        for (int i = 0; i < 52; i++) {
            String suit = "";
            int rank = 0;
            int offset = 0;

            if(i<15){
                suit = "Spades";
            } else if(i >= 15 && i < 29){
                suit = "Clubs";
                offset = 14;
            } else if(i >= 29 && i < 44){
                suit = "Diamonds";
                offset = 28;
            } else {
                suit = "Hearts";
                offset = 42;
            }
            if(i % 14 == 0){
                rank = 1;
            } else{
                rank = i - offset;
            }

            Card card = new Card(suit,rank); //Instantiate a Card
            this.cards.add(card); //Adding card to the Deck
        }
    }

    public Card getCard(){
        Card card = this.cards.get(0);
        this.cards.remove(0);
        return card;
    }

    public void shuffle(){
        Collections.shuffle(this.cards);
    }


}