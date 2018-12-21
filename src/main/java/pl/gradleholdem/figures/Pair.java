package pl.gradleholdem.figures;

import pl.gradleholdem.Cardable;
import pl.gradleholdem.Deck;
import pl.gradleholdem.PokerLayout;

public class Pair implements Cardable {
    Deck deck;


    public Pair(Deck deck){
        this.deck = deck;
    }


    @Override
    public Deck showFigure() {
        return null;
    }

    @Override
    public PokerLayout getFigureName() {
        return PokerLayout.PAIR;
    }


}
