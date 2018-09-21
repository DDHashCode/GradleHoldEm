package pl.gradleholdem;

import java.util.ArrayList;

public class Gameplay extends PokerDeck {
        private PokerDeck pokerDeck = new PokerDeck();
        private ArrayList<Card> flopCards = new ArrayList<>();
        private ArrayList<Card> turnCards = new ArrayList<>();
        private ArrayList<Card> riverCards = new ArrayList<>();
        private ArrayList<Card> CardsOnTable = new ArrayList<>();



    public Gameplay() {
            this.StartGame();
        }


        private void StartGame() {
        //adding 2 players to the game, creating a pokerDeck, distributing cards to players
            PlayerManagement newGame = new PlayerManagement();
            newGame.addPlayer();
            newGame.addPlayer();


            pokerDeck.printCardStack();
            firstDistribution();
        //printing out to check if works
            //setting cards to flop - first three cards
            setFlop();
            CardsOnTable.addAll(flopCards);
            System.out.println(flopCards.toString());
            //setting turn - another cards
            setTurn();
            CardsOnTable.addAll(turnCards);
            System.out.println(turnCards.toString());
            //river
            setRiver();
            CardsOnTable.addAll(riverCards);
            /*System.out.println(riverCards.toString());
            CardsOnTable.removeAll(CardsOnTable);
            CardsOnTable.add(new pl.gradleholdem.Card(pl.gradleholdem.CardRank.FOUR, pl.gradleholdem.Colour.SPADE));
            CardsOnTable.add(new pl.gradleholdem.Card(pl.gradleholdem.CardRank.FIVE, pl.gradleholdem.Colour.SPADE));
            CardsOnTable.add(new pl.gradleholdem.Card(pl.gradleholdem.CardRank.SIX, pl.gradleholdem.Colour.SPADE));
            CardsOnTable.add(new pl.gradleholdem.Card(pl.gradleholdem.CardRank.EIGHT, pl.gradleholdem.Colour.SPADE));
            CardsOnTable.add(new pl.gradleholdem.Card(pl.gradleholdem.CardRank.SEVEN, pl.gradleholdem.Colour.SPADE));*/

            FinalDeck tempDeck = new FinalDeck(CardsOnTable, players.get(0));
        }
        //ROZDANIE KART KAZDEMU Z GRACZY
        private void firstDistribution() {
            for ( int i = 0 ; i < players.size() ; i++ ){
                players.get(i).AddCardToHand(pokerDeck.deckStack.pop());
            }

            for ( int i = 0 ; i < players.size() ; i++ ){
                players.get(i).AddCardToHand(pokerDeck.deckStack.pop());
            }

        }

        private void setFlop() {
            pokerDeck.deckStack.pop();
            this.flopCards.add(pokerDeck.deckStack.pop());
            this.flopCards.add(pokerDeck.deckStack.pop());
            this.flopCards.add(pokerDeck.deckStack.pop());
        }

        private void setTurn() {
            pokerDeck.deckStack.pop();
            this.turnCards.add(pokerDeck.deckStack.pop());
        }

        private void setRiver(){
            pokerDeck.deckStack.pop();
            this.riverCards.add(this.deckStack.pop());
        }


}
