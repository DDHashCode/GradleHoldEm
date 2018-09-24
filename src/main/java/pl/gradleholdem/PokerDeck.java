package pl.gradleholdem;
// todo TODO TODO TODO rozdzielenie pokerdeck i deck, pokerdeck ma dziedziczyc z deck, dodanie innych deckow, np flop, turn, river, spalanie karty



public class PokerDeck extends Deck {

    public PokerDeck() {
        maxCardsInDeck = 52;
        this.cardsInDeck = 0;
        for (int rank = 0; rank < CardRank.values().length; rank++) {
            for (int color = 0; color < Colour.values().length; color++) {
                deckStack.add(new Card(CardRank.values()[rank], Colour.values()[color]));
                cardsInDeck++;
            }
        }
        tossCardDeck();
    }


    public void printCardStack() {
        for (int i = 0; i < deckStack.size(); i++) {
            System.out.println(deckStack.elementAt(i));
        }
    }



}
