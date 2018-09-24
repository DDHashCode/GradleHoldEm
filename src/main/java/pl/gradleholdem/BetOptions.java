package pl.gradleholdem;

public enum BetOptions {
    DIDNT_BET_YET(0),
    PASS(1),
    CALL(2),
    RAISE(3);

    int value;

    BetOptions(int value) {
        this.value = value;
    }
}
