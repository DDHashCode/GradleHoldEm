package pl.gradleholdem;

public class CardManagement {
    private PokerDeck pokerDeck;
    private Deck flopCards;
    private Deck turnCards;
    private Deck riverCards;


    public CardManagement() {
        pokerDeck = new PokerDeck();
        flopCards = new Deck();
        turnCards = new Deck();
        riverCards = new Deck();
    }

    public void printDeck() {
        this.pokerDeck.printCardStack();
    }

    public void firstDistribution(PlayerManagement playerMGM) {
        pokerDeck.burnCard();
        for (Player pl : playerMGM.getPlayersList()) {
            pl.AddCardToHand(pokerDeck.getCard());
        }
        for (Player pl : playerMGM.getPlayersList()) {
            pl.AddCardToHand(pokerDeck.getCard());
        }
    }

    public void setFlop() {
        System.out.println("Flop");
        this.pokerDeck.burnCard();
        this.flopCards.addCardToDeck(pokerDeck.getCard());
        this.flopCards.addCardToDeck(pokerDeck.getCard());
        this.flopCards.addCardToDeck(pokerDeck.getCard());
    }

    public void setTurn() {
        System.out.println("Turn");
        this.pokerDeck.burnCard();
        this.flopCards.addCardToDeck(pokerDeck.getCard());
    }

    public void setRiver() {
        System.out.println("River");
        this.pokerDeck.burnCard();
        this.flopCards.addCardToDeck(pokerDeck.getCard());
    }

    public Deck getCardOnTable() {
        Deck tempDeck = new Deck();
        tempDeck.addCardS(flopCards);
        tempDeck.addCardS(turnCards);
        tempDeck.addCardS(riverCards);
        return tempDeck;
    }
}
