package pl.gradleholdem;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {
    Card c1 = new Card(CardRank.K, Colour.DIAMOND);
    Card c2 = new Card(CardRank.K, Colour.HEART);
    Deck dck = new Deck();

    @Test
    public void addCardToDeck() {
        dck.addCardToDeck(c1);
        dck.addCardToDeck(c2);



        assertEquals(2, dck.getSize());
    }
}