import java.util.ArrayList;

public class Player {
    private ArrayList<Card> cardsInHand = new ArrayList<>();
    private PlayerPosition pos;
    private int cash;

    public Player(PlayerPosition pos) {
        this.pos = pos;
        this.cash = 0;
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

    public int getCash() { return this.cash; }

    public void setCash(int cash) { this.cash = cash; }

    public ArrayList<Card> getPlayerCards() {
        return cardsInHand;
    }


}
