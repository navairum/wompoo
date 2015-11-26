package wompoo.eric.com.wompoo;

import java.util.List;

/**
 * Created by Eric on 11/11/2015.
 */
public class Player {

    private String name;
    private int playerNumber;
    private int teamId;
    private List<Card> cards;
    private boolean isDealer;
    private int[] pegPositions;

    public Player() {
        this.isDealer=false;
        this.pegPositions = new int[]{0,0,0,0};
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

    public void setPegPositions(int[] pegPositions){
        this.pegPositions = pegPositions;
    }

    public int[] getPegPositions(){
        return this.pegPositions;
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

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards(){
        return this.cards;
    }

}
