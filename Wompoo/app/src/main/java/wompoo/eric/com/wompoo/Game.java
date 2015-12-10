package wompoo.eric.com.wompoo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Eric on 11/10/2015.
 */
public class Game extends AppCompatActivity{
    DeckOfCards deck;
    private int numPlayers;
    private List<Player> players;
    private String playerNames[];
    private int currentDealer;
    private int dealNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        this.numPlayers = (getIntent().getExtras()).getInt("numPlayers");
        this.playerNames = (getIntent().getExtras()).getStringArray("playerNames");
        DeckOfCards deck = new DeckOfCards();
        this.deck = deck;
        this.currentDealer = 1;
        setupGame();
        debug();
        List<Player> playerList = new ArrayList<Player>();
    }

   /* public Game(int numPlayers, String playerNames[]) {

    }*/

    public void debug(){
        for(int i =0; i<numPlayers; i++){
            Log.i("DEBUG","name: " + this.players.get(i).getName());
            Log.i("DEBUG","playernumber: " + this.players.get(i).getPlayerNumber());
            Log.i("DEBUG", "teamId: " + this.players.get(i).getTeamId());
            List<Card> cards =  this.players.get(i).getCards();
            for(int t =0; t< cards.size(); t++){
                Log.i("DEBUG - CARD","Suit: " + (cards.get(t).getSuit()) + "     Rank: " +(cards.get(t).getRank()));
            }
        }
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

        if(this.dealNumber == 1){
            this.deck = new DeckOfCards();  //start with a fresh deck
            Shuffle();
        }
        //4 player wompoo card numbers per deal: 5,5,4 = 56 cards (out of 58 total)
        for(Player player : players){
            int cardsToDeal = 0;
            List cards = new ArrayList<Card>();
            switch(this.dealNumber) {
                case 1:
                    cardsToDeal = 5;
                    break;
                default:
                    cardsToDeal = 4;
                    break;
            }
            for(int i = 0; i< cardsToDeal; i++){
                cards.add(this.deck.getCard());  //getCard function should remove that card from the deck. TODO verify the card gets removed from deck and added to players hand
            }
            player.setCards(cards);
        }
        drawCards();
        this.dealNumber++;
    }

    public void drawCards(){
        int id =0;
        for(Player player: players) {
            ImageButton cardImageButton = new ImageButton(this);
            RelativeLayout playerCardsLayout;
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)cardImageButton.getLayoutParams();
            switch (player.getPlayerNumber()) {
                case 0: //black
                    playerCardsLayout = (RelativeLayout) findViewById(R.id.blackcards);
                    params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                   // params.addRule(RelativeLayout.LEFT_OF, R.id.id_to_be_left_of);
                    break;
                case 1: //red
                    playerCardsLayout = (RelativeLayout) findViewById(R.id.redcards);
                    break;
                case 2: //white
                    playerCardsLayout = (RelativeLayout) findViewById(R.id.whitecards);
                    break;
                case 3: //blue
                    playerCardsLayout = (RelativeLayout) findViewById(R.id.bluecards);
                    break;
                default:
                    playerCardsLayout = null;
            }
            for(Card card:player.getCards()){
                String suit = card.getSuit();
                cardImageButton.setId(id);
                int rank = card.getRank();
                int imageResource =0;
                if(rank == 15){
                    imageResource = getResources().getIdentifier("joker", "drawable", getPackageName());
                } else{
                    imageResource = getResources().getIdentifier(suit.toLowerCase()+rank, "drawable", getPackageName());
                }


                cardImageButton.setImageResource(imageResource);
                cardImageButton.setScaleType(ImageView.ScaleType.FIT_XY);
                playerCardsLayout.addView(cardImageButton);
                cardImageButton.getLayoutParams().height = 135;
                cardImageButton.getLayoutParams().width = 105;
                 /* replicate this image button here:
                    <ImageButton
                    android:id="@+id/bluecard1"
                    android:background="@null"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                            />*/
                id++;
            }
        }
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
        for(int i =0;i<this.numPlayers;i++){
            Player player = new Player();
            player.setName(playerNames[i]);
            player.setPlayerNumber(i);
            if(i <2){
                player.setTeamId(1);
            } else {
                player.setTeamId(2);
            }
            //Block just sets the initial peg positions for the players on the board.  3 in the 'barracks' and 1 guy out.
            switch(i){
                case 0: //top right player - in our case black
                    player.setPegPositions(new int[]{1,0,0,0});
                    break;
                case 1://bottom right player - in our case red
                    player.setPegPositions(new int[]{17,0,0,0});
                    break;
                case 2: //bottom left player - in our case white
                    player.setPegPositions(new int[]{34,0,0,0});
                    break;
                case 3://top left player - in our case blue
                    player.setPegPositions(new int[]{51,0,0,0});
                    break;
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