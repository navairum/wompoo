package wompoo.eric.com.wompoo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Eric on 11/10/2015.
 */
public class Game {
    DeckOfCards deck;
    private int numPlayers;
    private List<Player> players;
    private String playerNames[];
    private int currentDealer;
    private int dealNumber;

    public Game(int numPlayers, String playerNames[]) {
        DeckOfCards deck = new DeckOfCards();
        this.numPlayers = numPlayers;
        this.deck = deck;
        this.playerNames = playerNames;
        this.currentDealer = 1;
        setupGame();
    }

    public void setupGame(){
        this.currentDealer = setInitialDealer(); //select random dealer. returned value will match playerId
        this.dealNumber = 1;
        setupPlayers();//sets the names, ids, and teams
        dealCards();
        if(!movePlayer()){  //if moveplayer returns false the move was not allowed (didnt pass rule check)
            //todo make user choose a new selection
        }
        //if end of round start new deal.  flag new player as dealer based on seating arrangement. change deal order (who the first card goes to).
    }
    public void dealCards(){
        //4 player wompoo card numbers per deal: 5,5,4 = 56 cards (out of 58 total)
        for(Player player : players){
            int cardsToDeal = 0;
            List cards = new ArrayList<Card>();
            switch(this.dealNumber) {
                case 3:
                    cardsToDeal = 4;
                    break;
                default:
                    cardsToDeal = 5;
                    break;
            }
            for(int i = 0; i< cardsToDeal; i++){
                cards.add(this.deck.getCard());  //getCard function should remove that card from the deck. TODO verify the card gets removed from deck and added to players hand
            }
            player.setCards(cards);
        }
        this.dealNumber++;

    }
    public int setInitialDealer(){
        Random rand = new Random();
        return (rand.nextInt(4)+1);
    }
    public boolean movePlayer(){
        if(checkRules()){
            return true;
        } else {
            return false;
        }
    }
    public void setupPlayers(){
        this.players = new ArrayList<Player>();
        for(int i =1;i<=this.numPlayers;i++){
            Player player = new Player();
            player.setName(playerNames[i]);
            player.setPlayerNumber(i);
            if(i <=2){
                player.setTeamId(1);
            } else {
                player.setTeamId(2);
            }
            players.add(player);
        }
    }

    public void Shuffle(){
        this.deck.shuffle();
    }

    public boolean checkRules(){
        return true; //return false if the move wasnt allowed
    }

}