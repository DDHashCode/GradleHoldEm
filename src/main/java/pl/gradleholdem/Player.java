package pl.gradleholdem;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> cardsInHand = new ArrayList<>();
    private PlayerPosition pos;
    private int cash;
    private String nickName;
    private static int ID = 1;
    private int playerID;
    private BetOptions status;

    public Player(PlayerPosition pos, String nickName) {
        this.pos = pos;
        this.cash = 0;
        this.nickName = nickName;
        status = BetOptions.DIDNT_BET_YET;
        playerID = ID;
        ID++;
    }

    public void AddCardToHand(Card card) {
        this.cardsInHand.add(card);
    }

    public void changePlayerPosition() {
        System.out.println(this.pos.toString());
        pos = pos.next().get();
    }

    public PlayerPosition getPos() {
        return this.pos;
    }

    public int getCash() {
        return this.cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public ArrayList<Card> getPlayerCards() {
        return cardsInHand;
    }

    public String getNickName() {
        return nickName;
    }

    public int getID() {
        return playerID;
    }

    public void setStatus(BetOptions status) {
        this.status = status;
    }

    public BetOptions getStatus() {
        return status;
    }

    public void removeCash(int cash){
        this.cash = this.cash - cash;
    }
}
