package pl.gradleholdem;

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

    public int getNumber() {
        return number;
    }

  /*  public int compare(CardRank o1, CardRank o2){
        if (o1.getNumber() < o2.getNumber()) {
            return -1;
        }
        if (o1.getNumber() > o2.getNumber()) {
            return 1;
        }
        else return 0;

    }
*/

}
