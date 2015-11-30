package wompoo.eric.com.wompoo;

public class Card {

    private String suit;
    private int rank;
    public boolean  isSpecial;
    public boolean  isOutCard;

    public Card(String suit, int rank) {
        this.suit = suit;
        this.rank = rank;
        switch(rank){
            case 4:
            case 7:
            case 11: //11 = jack
                this.isSpecial = true;
                break;
            case 1: //ace
            case 13: //king
            case 15: //joker
                this.isOutCard = true;

            default:
                this.isSpecial = false;
                this.isOutCard = false;

        }
    }
    public String getSuit(){
        return this.suit;
    }

    public int getRank(){
        return this.rank;
    }

}