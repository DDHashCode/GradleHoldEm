package pl.gradleholdem;

import java.util.*;


public class Deck {
    protected int maxCardsInDeck;
    protected int cardsInDeck;
    protected Stack<Card> deckStack = new Stack<>();


    public Deck(int maxCardsInDeck, int cardsInDeck, Stack<Card> deckStack) {
        this.maxCardsInDeck = maxCardsInDeck;
        this.cardsInDeck = cardsInDeck;
        this.deckStack = deckStack;
    }

    public Deck(Deck deck) {
        this.deckStack.addAll(deck.deckStack);
    }

    public Deck(){

    }

    protected void tossCardDeck() {
        Collections.shuffle(deckStack);
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

    public void sortByRankAndDistinct() {
        SortedSet<Card> sset = new TreeSet<>(Comparator.comparing(Card::getRank));
        sset.addAll(this.deckStack);
        this.deckStack.removeAllElements();
        this.deckStack.addAll(sset);
    }


}
