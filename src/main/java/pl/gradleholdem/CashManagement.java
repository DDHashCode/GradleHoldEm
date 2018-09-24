package pl.gradleholdem;

public class CashManagement {
    private int startCash;
    private PlayerManagement playerMGM;
    private int smallBlind;
    private int bigBlind;
    private int chipsStack;

    public CashManagement(int startCash, PlayerManagement playerMGM, int smallBlind) {
        this.startCash = startCash;
        this.playerMGM = playerMGM;
        this.smallBlind = smallBlind;
        this.bigBlind = 2 * smallBlind;
        chipsStack = this.smallBlind + this.bigBlind;
    }


    //TODO create bet method
    public void setStartCash() {
        this.playerMGM.getPlayersList().stream().forEach(a -> a.setCash(startCash));
    }

    public void setStartCash(int cash) {
        this.playerMGM.getPlayersList().stream().forEach(a -> a.setCash(cash));
    }

    public void setChipsStack(int chipsStack) {
        this.chipsStack = chipsStack;
    }


    public int getChipsStack() {
        return chipsStack;
    }

    public int getSmallBlind() {
        return smallBlind;
    }

    public int getBigBlind() {
        return bigBlind;
    }

    public void setSmallBlind(int smallBlind) {
        this.smallBlind = smallBlind;
    }

    public void setBigBlind(int bigBlind) {
        this.bigBlind = bigBlind;
    }
}
