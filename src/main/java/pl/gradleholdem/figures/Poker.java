package pl.gradleholdem.figures;

import pl.gradleholdem.Cardable;
import pl.gradleholdem.Deck;
import pl.gradleholdem.PokerLayout;

public class Poker implements Cardable {
    @Override
    public Deck showFigure() {
        return null;
    }

    @Override
    public PokerLayout getFigureName() {
        return PokerLayout.ROYAL_FLUSH;
    }
}
