package pl.gradleholdem;


import java.util.Optional;

public class Player {
    private static int ID = 1;

    private int playerID;
    private String nickName;
    private int cash;
    private PlayerPosition pos;
    private Deck cardsInHand = new Deck();
    private Deck layoutCards = new Deck();


    private BetOptions status;
    private PokerLayout layout;

    public Player(PlayerPosition pos, String nickName) {
        this.pos = pos;
        this.cash = 0;
        this.nickName = nickName;
        status = BetOptions.DIDNT_BET_YET;
        playerID = ID;
        ID++;
    }

    public Deck getLayoutCards() {
        return layoutCards;
    }

    public void setLayoutCards(Deck layoutCards) {
        this.layoutCards = layoutCards;
    }

    public void setLayout(PokerLayout layout) {
        this.layout = layout;
    }

    public Optional getLayout() {
        return Optional.ofNullable(layout);
    }

    public void AddCardToHand(Card card) {
        this.cardsInHand.addCardToDeck(card);
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

    public Deck getPlayerCards() {
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
