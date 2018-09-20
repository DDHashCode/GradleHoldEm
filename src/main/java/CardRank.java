public enum CardRank {
    TWO(1),
    THREE(2),
    FOUR(3),
    FIVE(4),
    SIX(5),
    SEVEN(6),
    EIGHT(7),
    NINE(8),
    TEN(9),
    J(10),
    Q(11),
    K(12),
    A(13);

    int number;

    private CardRank(int numer){
        this.number = numer;
    }

    public boolean isBiggerThan(CardRank test) {
        return this.number > test.number;
    }
}
