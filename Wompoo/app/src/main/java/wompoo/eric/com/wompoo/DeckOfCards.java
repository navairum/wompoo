package wompoo.eric.com.wompoo;

import android.util.Log;

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

            if(i<13){
                suit = "Spades";
            } else if(i >= 13 && i < 26){
                suit = "Clubs";
                offset = 13;
            } else if(i >= 26 && i < 39){
                suit = "Diamonds";
                offset = 26;
            } else {
                suit = "Hearts";
                offset = 39;
            }

            rank = i - offset;
            if(rank == 0){
                rank = 13; //15 joker, 14 = ace, 13 king, 12 queen ,11 jack
            }

            Card card = new Card(suit,rank); //Instantiate a Card
            this.cards.add(card); //Adding card to the Deck
        }
        Card card = new Card("Joker",15); //Instantiate a Card
        this.cards.add(card); //Adding a joker to the deck
        this.cards.add(card);//Adding a second joker to the deck
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