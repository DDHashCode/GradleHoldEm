public enum Colour {
    //HEART, SPADE, DIAMOND, CLUB
    SPADE(1), CLUB(2), DIAMOND(3), HEART(4);

    int color;

    private Colour(int colour){
        this.color = colour;
    }

    public int getVal() {
        return color;
    }
}
