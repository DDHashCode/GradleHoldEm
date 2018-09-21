package pl.gradleholdem;
// todo TODO TODO TODO rozdzielenie pokerdeck i deck, pokerdeck ma dziedziczyc z deck, dodanie innych deckow, np flop, turn, river, spalanie karty
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class PokerDeck extends Deck {

    public PokerDeck() {
        maxCardsInDeck = 52;
        this.cardsInDeck = 0;
        for (int rank = 0; rank < CardRank.values().length; rank++) {
            for (int color = 0; color < Colour.values().length; color++) {
                cardDeck.add(new Card(CardRank.values()[rank], Colour.values()[color]));
                cardsInDeck++;
            }
        }
        this.cardDeck = tossCardDeck();
        this.deckStack.addAll(cardDeck);
    }


    public void printCardStack() {
        for (int i = 0; i < deckStack.size(); i++) {
            System.out.println(deckStack.elementAt(i));
        }
    }

    public Card popCardFromDeck() {
        this.cardsInDeck--;
        return deckStack.pop();
    }


}
