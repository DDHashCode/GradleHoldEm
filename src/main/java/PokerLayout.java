import java.util.ArrayList;

public enum PokerLayout{
    NONE(0),
    HIGH_CARD(1),
    PAIR(2),
    TWO_PAIRS(3),
    THREE(4),
    STREIGH(5),
    FLUSH(6),
    FULL_HOUSE(7),
    QUADS(8),
    STRAIGHT_FLUSH(9),
    ROYAL_FLUSH(10);

    int number;


    private PokerLayout(int numer) {
        this.number = numer;
    }

    public boolean isBiggerThan(PokerLayout check){
        return (this.number > check.number);
    }


}
