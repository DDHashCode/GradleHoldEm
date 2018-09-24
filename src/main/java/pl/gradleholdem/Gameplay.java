package pl.gradleholdem;


public class Gameplay {
    PlayerManagement playerMGM = new PlayerManagement();
    CardManagement cardMGM = new CardManagement();


    public Gameplay() {
        this.StartGame();
    }


    private void StartGame() {
        playerMGM.addPlayer();
        playerMGM.addPlayer();

        cardMGM.firstDistribution(playerMGM);
        cardMGM.setFlop();
        cardMGM.setTurn();
        cardMGM.setRiver();

        System.out.println(cardMGM.getCardOnTable().getCardDeck());


        //FinalDeck tempDeck = new FinalDeck(cardMGM.getCardOnTable(), );
    }
}
