package pl.gradleholdem;


public class Gameplay {
    PlayerManagement playerMGM = new PlayerManagement();
    CardManagement cardMGM = new CardManagement();
    CashManagement cashMGM;


    public Gameplay() {
        cashMGM = new CashManagement(20, playerMGM, 1);
        this.PreGameSetup();
    }

    private void PreGameSetup() {
        playerMGM.addPlayer("Dawid");
        playerMGM.addPlayer("Obelga");
        playerMGM.addPlayer("Karol");
        cashMGM.setStartCash();
        playerMGM.listPlayers();


        //FigureCheck tempDeck = new FigureCheck(cardMGM.getCardOnTable(), );
        StartGame();
        playerMGM.listPlayers();
    }

    private void StartGame() {
        new Round(this);
    }
}
