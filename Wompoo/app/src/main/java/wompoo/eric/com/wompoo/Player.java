package wompoo.eric.com.wompoo;

/**
 * Created by Eric on 11/11/2015.
 */
public class Player {

    private String name;
    private int playerNumber;
    private int teamId;
    private Card cards[];
    private boolean isDealer;

    public Player() {
        this.isDealer=false;

    }
    public void setIsDealer(boolean isDealer){
        this.isDealer = isDealer;
    }
    public void setTeamId(int id) {
        this.teamId = id;
    }

    public int getTeamId() {
        return this.teamId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber() {
        return this.playerNumber;
    }

    public void setCards(Card cards[]) {
        this.cards = cards;
    }

    public Card[] getCards(){
        return this.cards;
    }

}
