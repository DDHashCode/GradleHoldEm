public class Card {
    private CardRank rank;
    private Colour colour;

    public Card(CardRank rank, Colour colour) {
        this.rank = rank;
        this.colour = colour;
    }

    public CardRank getRank() {
        return rank;
    }

    public Colour getColour() {
        return colour;
    }

    public void printCardData() {
        System.out.println(this.rank + " " + this.colour);
    }

    @Override
    public String toString() {
        return rank + " " + colour;
    }

    public boolean hasSameColour(Card karta){
        return this.colour == karta.colour;
    }

    public boolean hasSameRank(Card karta){
        return this.rank == karta.rank;
    }




}
