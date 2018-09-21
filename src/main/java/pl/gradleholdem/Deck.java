package pl.gradleholdem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Deck {
    protected int maxCardsInDeck;
    protected int cardsInDeck;
    protected ArrayList<Card> cardDeck = new ArrayList<>();
    protected Stack<Card> deckStack = new Stack<>();


    public Deck(int maxCardsInDeck, int cardsInDeck, ArrayList<Card> cardDeck, Stack<Card> deckStack) {
        this.maxCardsInDeck = maxCardsInDeck;
        this.cardsInDeck = cardsInDeck;
        this.cardDeck = cardDeck;
        this.deckStack = deckStack;
    }

    public Deck() {
    }

    protected ArrayList<Card> tossCardDeck() {
        ArrayList<Card> tempDeck = new ArrayList<>(cardDeck);
        Collections.shuffle(tempDeck);
        return tempDeck;
    }

    public ArrayList<Card> getCardDeck() {
        return cardDeck;
    }
}
