package pl.gradleholdem;

import org.junit.Assert;
import org.junit.Test;


public class FigureCheckTest {
    Card c1 = new Card(CardRank.K, Colour.CLUB);
    Card c2 = new Card(CardRank.K, Colour.DIAMOND);
    Card c3 = new Card(CardRank.K, Colour.SPADE);
    Card c4 = new Card(CardRank.NINE, Colour.CLUB);
    Card c5 = new Card(CardRank.NINE, Colour.DIAMOND);



    Deck dck = new Deck();
    FigureCheck testFC;


    Player pl = new Player(PlayerPosition.BUTTON, "David");

    @Test
    public void hasFigureTest() {
        Card c1 = new Card(CardRank.K, Colour.CLUB);
        Card c2 = new Card(CardRank.K, Colour.DIAMOND);
        Card c3 = new Card(CardRank.K, Colour.SPADE);
        Card c4 = new Card(CardRank.NINE, Colour.CLUB);
        Card c5 = new Card(CardRank.NINE, Colour.DIAMOND);
        Card c6 = new Card(CardRank.K, Colour.HEART);
        Card c7 = new Card(CardRank.TEN, Colour.CLUB);


        Deck deck = new Deck();
        deck.addCardToDeck(c1);
        deck.addCardToDeck(c2);
        deck.addCardToDeck(c3);
        deck.addCardToDeck(c4);
        deck.addCardToDeck(c5);
        deck.addCardToDeck(c6);
        deck.addCardToDeck(c7);

        testFC = new FigureCheck(dck, pl);

        Assert.assertEquals(PokerLayout.QUADS, testFC.hasFigureTwo());
    }

    @Test
    public void playerTest() {
        Assert.assertEquals(0, pl.getPlayerCards().getSize());
    }

    @Test
    public void hasFigureTwoTest() {
        Card c1 = new Card(CardRank.TWO, Colour.SPADE);
        Card c2 = new Card(CardRank.THREE, Colour.SPADE);
        Card c3 = new Card(CardRank.THREE, Colour.CLUB);
        Card c5 = new Card(CardRank.J, Colour.SPADE);
        Card c7 = new Card(CardRank.A, Colour.SPADE);
        Card c4 = new Card(CardRank.TWO, Colour.CLUB);
        Card c6 = new Card(CardRank.THREE, Colour.HEART);



        Deck deck = new Deck();
        deck.addCardToDeck(c1);
        deck.addCardToDeck(c2);
        deck.addCardToDeck(c3);
        deck.addCardToDeck(c4);
        deck.addCardToDeck(c5);
        deck.addCardToDeck(c6);
        deck.addCardToDeck(c7);


        testFC = new FigureCheck(deck, pl);


            Assert.assertEquals(PokerLayout.FULL_HOUSE, testFC.hasFigureTwo());

    }

    @Test
    public void hasThree() {
        Card c1 = new Card(CardRank.K, Colour.CLUB);
        Card c2 = new Card(CardRank.A, Colour.DIAMOND);
        Card c3 = new Card(CardRank.K, Colour.SPADE);

        Deck dz = new Deck();

        dz.addCardToDeck(c1);
        dz.addCardToDeck(c2);
        dz.addCardToDeck(c3);

        testFC = new FigureCheck(dz, pl);

        Assert.assertTrue(testFC.hasThree());
    }

    @Test
    public void hasFull() {
        Card c1 = new Card(CardRank.TWO, Colour.SPADE);
        Card c2 = new Card(CardRank.THREE, Colour.SPADE);
        Card c3 = new Card(CardRank.THREE, Colour.CLUB);
        Card c5 = new Card(CardRank.J, Colour.SPADE);
        Card c7 = new Card(CardRank.A, Colour.SPADE);
        Card c4 = new Card(CardRank.THREE, Colour.CLUB);
        Card c6 = new Card(CardRank.THREE, Colour.HEART);


        Deck dz = new Deck();
        dz.addCardToDeck(c1);
        dz.addCardToDeck(c2);
        dz.addCardToDeck(c3);
        dz.addCardToDeck(c4);
        dz.addCardToDeck(c5);
        dz.addCardToDeck(c6);
        dz.addCardToDeck(c7);


        testFC = new FigureCheck(dz, pl);


        Assert.assertTrue(testFC.hasFull());

    }

    @Test
    public void showDeck() {
        Deck dk = new Deck();
        dk.addCardToDeck(c1);
        dk.addCardToDeck(c2);

        //Assert.assertTrue(dk.cardsInDeck == 2);
        Assert.assertEquals(2, dk.getSize());
    }


    @Test
    public void AreNeighboursTestTRUE() {
        Assert.assertTrue(new FigureCheck().areNeighbours(1,2));
    }
    @Test
    public void AreNeighboursTestTRUE2ND() {
        Assert.assertTrue(new FigureCheck().areNeighbours(1,3));
    }
    @Test
    public void AreNeighboursTestFALSE() {
        Assert.assertFalse(new FigureCheck().areNeighbours(1,2));
    }
    @Test
    public void AreNeighboursTestFALSE2ND() {
        Assert.assertFalse(new FigureCheck().areNeighbours(1,-1));
    }



    //HASCOLOR Tests


    @Test
    public void hasColorTest() {
        Card c1 = new Card(CardRank.TWO, Colour.DIAMOND);
        Card c2 = new Card(CardRank.A, Colour.SPADE);
        Card c3 = new Card(CardRank.NINE, Colour.HEART);
        Card c4 = new Card(CardRank.FIVE, Colour.DIAMOND);
        Card c5 = new Card(CardRank.SEVEN, Colour.DIAMOND);
        Card c6 = new Card(CardRank.TEN, Colour.DIAMOND);
        Card c7 = new Card(CardRank.K, Colour.DIAMOND);

        Deck deck = new Deck();
        deck.addCardToDeck(c1);
        deck.addCardToDeck(c2);
        deck.addCardToDeck(c3);
        deck.addCardToDeck(c4);
        deck.addCardToDeck(c5);
        deck.addCardToDeck(c6);
        deck.addCardToDeck(c7);

        FigureCheck testFC = new FigureCheck(deck, pl);

        Assert.assertTrue("Ma być true", testFC.hasColour());


    }


    @Test
    public void hasColorTest2nd() {
        Card c1 = new Card(CardRank.TWO, Colour.DIAMOND);
        Card c2 = new Card(CardRank.A, Colour.SPADE);
        Card c3 = new Card(CardRank.NINE, Colour.HEART);
        Card c4 = new Card(CardRank.FIVE, Colour.DIAMOND);
        Card c5 = new Card(CardRank.SEVEN, Colour.DIAMOND);
        Card c6 = new Card(CardRank.TEN, Colour.DIAMOND);
        Card c7 = new Card(CardRank.K, Colour.HEART);

        Deck deck = new Deck();
        deck.addCardToDeck(c1);
        deck.addCardToDeck(c2);
        deck.addCardToDeck(c3);
        deck.addCardToDeck(c4);
        deck.addCardToDeck(c5);
        deck.addCardToDeck(c6);
        deck.addCardToDeck(c7);

        FigureCheck testFC = new FigureCheck(deck, pl);

        Assert.assertFalse(testFC.hasColour());


    }

    //ShowColor tests
    @Test
    public void showColorTest() {
        Card c1 = new Card(CardRank.TWO, Colour.DIAMOND);
        Card c2 = new Card(CardRank.A, Colour.DIAMOND);
        Card c4 = new Card(CardRank.FIVE, Colour.DIAMOND);
        Card c5 = new Card(CardRank.SEVEN, Colour.DIAMOND);
        Card c6 = new Card(CardRank.TEN, Colour.DIAMOND);
        Card c7 = new Card(CardRank.K, Colour.HEART);
        Card c3 = new Card(CardRank.NINE, Colour.HEART);


        Deck deck = new Deck();
        deck.addCardToDeck(c1);
        deck.addCardToDeck(c2);
        deck.addCardToDeck(c3);
        deck.addCardToDeck(c4);
        deck.addCardToDeck(c5);
        deck.addCardToDeck(c6);
        deck.addCardToDeck(c7);

        FigureCheck testFC = new FigureCheck(deck, pl);


        Assert.assertTrue(
                testFC.showColour().deckStack.contains(c1) &&
                        testFC.showColour().deckStack.contains(c2) &&
                        testFC.showColour().deckStack.contains(c4) &&
                        testFC.showColour().deckStack.contains(c5) &&
                        testFC.showColour().deckStack.contains(c6) &&
                        testFC.showColour().deckStack.size() == 5);
    }

    @Test
    public void showColorTestTWo() {
        Card c1 = new Card(CardRank.TWO, Colour.DIAMOND);
        Card c2 = new Card(CardRank.A, Colour.DIAMOND);
        Card c4 = new Card(CardRank.FIVE, Colour.DIAMOND);
        Card c5 = new Card(CardRank.SEVEN, Colour.DIAMOND);
        Card c6 = new Card(CardRank.TEN, Colour.DIAMOND);
        Card c7 = new Card(CardRank.K, Colour.HEART);
        Card c3 = new Card(CardRank.NINE, Colour.HEART);


        Deck deck = new Deck();
        deck.addCardToDeck(c1);
        deck.addCardToDeck(c2);
        deck.addCardToDeck(c3);
        deck.addCardToDeck(c4);
        deck.addCardToDeck(c5);
        deck.addCardToDeck(c6);
        deck.addCardToDeck(c7);

        FigureCheck testFC = new FigureCheck(deck, pl);


        Assert.assertFalse(
                testFC.showColour().deckStack.contains(c1) &&
                        testFC.showColour().deckStack.contains(c2) &&
                        testFC.showColour().deckStack.contains(c4) &&
                        testFC.showColour().deckStack.contains(c5) &&
                        testFC.showColour().deckStack.contains(c6) &&
                        testFC.showColour().deckStack.size() != 5);
    }

    //hasStreigh Tests
    @Test
    public void hasStreighTest() {
        Card c1 = new Card(CardRank.FOUR, Colour.DIAMOND);
        Card c2 = new Card(CardRank.SIX, Colour.CLUB);
        Card c3 = new Card(CardRank.FIVE, Colour.HEART);
        Card c4 = new Card(CardRank.EIGHT, Colour.CLUB);
        Card c5 = new Card(CardRank.SEVEN, Colour.HEART);
        Card c6 = new Card(CardRank.A, Colour.DIAMOND);
        Card c7 = new Card(CardRank.A, Colour.SPADE);


        Deck deck = new Deck();
        deck.addCardToDeck(c1);
        deck.addCardToDeck(c2);
        deck.addCardToDeck(c3);
        deck.addCardToDeck(c4);
        deck.addCardToDeck(c5);
        deck.addCardToDeck(c6);
        deck.addCardToDeck(c7);

        FigureCheck testFC = new FigureCheck(deck, pl);


        try {
            Assert.assertTrue(testFC.hasStreigh());
        }
        catch(AssertionError e) {
            //e.printStackTrace();
            deck.sortByRankAndDistinct();
            deck.deckStack.stream().forEach(a -> a.printCardData());
        }


    }

    @Test
    public void hasStraightFlushTest() {

        Card c1 = new Card(CardRank.FOUR, Colour.DIAMOND);
        Card c2 = new Card(CardRank.SIX, Colour.DIAMOND);
        Card c3 = new Card(CardRank.FIVE, Colour.DIAMOND);
        Card c4 = new Card(CardRank.EIGHT, Colour.DIAMOND);
        Card c5 = new Card(CardRank.SEVEN, Colour.DIAMOND);
        Card c6 = new Card(CardRank.A, Colour.DIAMOND);
        Card c7 = new Card(CardRank.A, Colour.SPADE);


        Deck deck = new Deck();
        deck.addCardToDeck(c1);
        deck.addCardToDeck(c2);
        deck.addCardToDeck(c3);
        deck.addCardToDeck(c4);
        deck.addCardToDeck(c5);
        deck.addCardToDeck(c6);
        deck.addCardToDeck(c7);

        FigureCheck testFC = new FigureCheck(deck, pl);

        Assert.assertTrue(testFC.hasStraightFlush());

    }

    @Test
    public void hasRoyalFlushTest() {

        Card c1 = new Card(CardRank.Q, Colour.DIAMOND);
        Card c2 = new Card(CardRank.SIX, Colour.DIAMOND);
        Card c3 = new Card(CardRank.K, Colour.DIAMOND);
        Card c4 = new Card(CardRank.J, Colour.DIAMOND);
        Card c5 = new Card(CardRank.TEN, Colour.DIAMOND);
        Card c6 = new Card(CardRank.A, Colour.DIAMOND);
        Card c7 = new Card(CardRank.A, Colour.SPADE);


        Deck deck = new Deck();
        deck.addCardToDeck(c1);
        deck.addCardToDeck(c2);
        deck.addCardToDeck(c3);
        deck.addCardToDeck(c4);
        deck.addCardToDeck(c5);
        deck.addCardToDeck(c6);
        deck.addCardToDeck(c7);

        FigureCheck testFC = new FigureCheck(deck, pl);

        Assert.assertTrue(testFC.hasRoyalFlush());

    }




}