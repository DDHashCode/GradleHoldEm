package pl.gradleholdem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Deck {
    protected int maxCardsInDeck;
    protected int cardsInDeck;
    //protected ArrayList<Card> cardDeck = new ArrayList<>();
    protected Stack<Card> deckStack = new Stack<>();


    public Deck(int maxCardsInDeck, int cardsInDeck, ArrayList<Card> cardDeck, Stack<Card> deckStack) {
        this.maxCardsInDeck = maxCardsInDeck;
        this.cardsInDeck = cardsInDeck;
        //this.cardDeck = cardDeck;
        this.deckStack = deckStack;
    }

    public Deck(Deck deck) {
        this.deckStack.addAll(deck.getCardDeck());
    }

    public Deck(){

    }

    protected void tossCardDeck() {
        Collections.shuffle(deckStack);
    }

    public ArrayList<Card> getCardDeck() {
        return new ArrayList<>(deckStack);
    }

    public int getSize() {
        return this.deckStack.size();
    }

    public void burnCard() {
        this.deckStack.pop();
    }

    public Card getCard() {
        return deckStack.pop();
    }

    public void addCardToDeck(Card card) {
        this.deckStack.push(card);
    }

    public void addCardS(Deck deck) {
        this.deckStack.addAll(deck.deckStack);
    }

    public void removeCard(Card card){
        this.deckStack.remove(card);
    }


}
