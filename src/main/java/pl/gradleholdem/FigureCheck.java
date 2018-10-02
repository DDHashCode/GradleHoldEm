package pl.gradleholdem;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FigureCheck {
    private Deck cards;
    private Player player;

    static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public FigureCheck(Deck cards, Player player) {
        this.cards = cards;
        this.player = player;
        this.cards.addCardS(player.getPlayerCards());
        this.cards.getCardDeck().sort(Card::compareTo);

        /*System.out.println("Has HIGH: " + hasHigh());
        System.out.println("pair: " + hasPair(this.cards) + " " + showPair(this.cards));
        System.out.println("three: " + hasThree(this.cards) + " " + showThree(this.cards));
        System.out.println("Full: " + hasFull(this.cards) + " " + showFull(this.cards));
        System.out.println("two pairs: " + hastwoPairs(this.cards) + " " + showTwoPairs(this.cards));
        System.out.println("Has Streigh: " + hasStreigh(this.cards) + " " + showStreigh(this.cards));
        System.out.println("Has Color: " + hasColour(this.cards) + " " + showColor(this.cards));
        System.out.println("Has Quads: " + hasQuads(this.cards) + " " + showQuads(this.cards));
        System.out.println("Has Poker: " + hasPoker(this.cards) + " " + showPoker(this.cards));
        */


    }

    //todo+
    /*public PokerLayout hasFigure() {
        if (hasRoyalFlush(this.cards)) {
            return PokerLayout.ROYAL_FLUSH;
        } else if (hasStraightFlush(this.cards)) {
            return PokerLayout.STRAIGHT_FLUSH;
        } else if (hasQuads(this.cards)) {
            return PokerLayout.QUADS;
        } else if (hasFull(this.cards)) {
            return PokerLayout.FULL_HOUSE;
        } else if (hasColour(this.cards)) {
            return PokerLayout.FLUSH;
        } else if (hasStreigh(this.cards)) {
            return PokerLayout.STREIGH;
        } else if (hasThree(this.cards)) {
            return PokerLayout.THREE;
        } else if (hastwoPairs(this.cards)) {
            return PokerLayout.TWO_PAIRS;
        } else if (hasPair(this.cards)) {
            return PokerLayout.PAIR;
        } else
            return PokerLayout.HIGH_CARD;

    }*/

    public boolean hasPair(Deck cards) {
        cards.getCardDeck().stream()
                .filter(o -> )


        ArrayList<Card> temp = new ArrayList<>(cards);
        Card tempCard = temp.get(0);
        int counter = 1;
        for (int i = 1; i < temp.size(); i++) {
            if (counter == 2) break;
            //System.out.println(temp.get(i).getRank());
            if (tempCard.getRank().equals(temp.get(i).getRank())) counter++;
            if (!tempCard.getRank().equals(temp.get(i).getRank())) {
                tempCard = temp.get(i);
                counter = 1;
            }
        }
        return counter == 2;
    }

    public boolean hasThree(Deck cards) {
        ArrayList<Card> temp = new ArrayList<>(cards);
        Card tempCard = temp.get(0);
        int counter = 1;
        for (int i = 1; i < temp.size(); i++) {
            if (counter == 3) break;
            if (tempCard.getRank().equals(temp.get(i).getRank())) counter++;
            if (!tempCard.getRank().equals(temp.get(i).getRank())) {
                tempCard = temp.get(i);
                counter = 1;
            }
        }
        return counter == 3;
    }

    public boolean hasFull(Deck cards) {
        ArrayList<Card> temp = new ArrayList<>(cards);
        Card tempCard = temp.get(0);
        Card pairCard = tempCard;
        int counter = 1;
        for (int i = 1; i < temp.size(); i++) {
            if (counter == 2) break;
            //System.out.println(temp.get(i).getRank());
            if (tempCard.getRank().equals(temp.get(i).getRank())) {
                counter++;
                pairCard = temp.get(i);
            }
            if (!tempCard.getRank().equals(temp.get(i).getRank())) {
                tempCard = temp.get(i);
                counter = 1;
            }
        }
        temp.remove(tempCard);
        try {
            temp.remove(pairCard);
        } finally {
        }


        return counter == 2 && hasThree(temp);
    }

    public boolean hasHigh() {
        return !hasPair(this.cards);
    }

    public boolean hastwoPairs(Deck cards) {
        ArrayList<Card> temp = new ArrayList<>(cards);
        Card tempCard = temp.get(0);
        Card pairCard = tempCard;
        int counter = 1;
        for (int i = 1; i < temp.size(); i++) {
            if (counter == 2) break;
            //System.out.println(temp.get(i).getRank());
            if (tempCard.getRank().equals(temp.get(i).getRank())) {
                counter++;
                pairCard = temp.get(i);
            }
            if (!tempCard.getRank().equals(temp.get(i).getRank())) {
                tempCard = temp.get(i);
                counter = 1;
            }
        }
        temp.remove(tempCard);
        try {
            temp.remove(pairCard);
        } finally {
        }


        return counter == 2 && hasPair(temp);
    }

    public boolean areNeighbours(int first, int second) {
        return first - second == 1 || first - second == -1;
    }

    public boolean hasStreigh(Deck cards) {
        ArrayList<Card> temp = new ArrayList<>(cards);
        int check = 0;
        int checker = temp.get(0).getRank().number;
        for (int i = 0; i < temp.size(); i++) {
            if (check == 4) break;
            if (areNeighbours(checker, temp.get(i).getRank().number)) {
                check++;
                checker = temp.get(i).getRank().number;
            } else checker = temp.get(i).getRank().number;
        }
        return check == 4;
    }

    public boolean hasColour(Deck cards) {
        ArrayList<Card> temp = new ArrayList<>(cards);
        temp.sort(Comparator.comparing(Card::getColour));
        int counter = 0;
        Colour tempColour = temp.get(0).getColour();
        for (int i = 0; i < temp.size(); i++) {
            //System.out.println(temp.get(i).getColour());
            if (counter == 4) break;
            if (tempColour.equals(temp.get(i).getColour())) {
                counter++;
                tempColour = temp.get(i).getColour();
            } else tempColour = temp.get(i).getColour();
        }

        return counter == 4;
    }

    public boolean hasQuads(Deck cards) {
        ArrayList<Card> temp = new ArrayList<>(cards);
        int counter = 0;
        CardRank tempRank = temp.get(0).getRank();
        for (int i = 0; i < temp.size(); i++) {
            if (counter == 4) break;
            if (tempRank.equals(temp.get(i).getRank())) {
                counter++;
                tempRank = temp.get(i).getRank();
            } else if (!tempRank.equals(temp.get(i).getRank()) && counter > 1 && counter < 4) counter = 0;
            else tempRank = temp.get(i).getRank();
        }
        return counter == 4;
    }

    public boolean hasStraightFlush(Deck cards) {
        return hasColour(showStreigh(cards));
    }

    private boolean hasRoyalFlush(Deck cards) {
        return hasStraightFlush(this.cards) &&
                cards.stream().map(Card::getRank).anyMatch(CardRank.A::equals) &&
                cards.stream().map(Card::getRank).anyMatch(CardRank.TEN::equals);
    }

    public ArrayList<Card> showPair(ArrayList<Card> cards) {
        cards.sort(Comparator.comparing(Card::getRank));
        ArrayList<Card> outputDeck = new ArrayList<>();
        ArrayList<Card> tempDeck = new ArrayList<>(cards);
        for (int i = 0; i < tempDeck.size() - 1; i++) {
            if (tempDeck.get(i).getRank().equals(tempDeck.get(i + 1).getRank())) {
                outputDeck.add(tempDeck.get(i));
                outputDeck.add(tempDeck.get(i + 1));
                break;
            }
        }
        return outputDeck;
    }

    public ArrayList<Card> showThree(ArrayList<Card> cards) {
        cards.sort(Comparator.comparing(Card::getRank));
        ArrayList<Card> outputDeck = new ArrayList<>();
        ArrayList<Card> tempDeck = new ArrayList<>(cards);
        for (int i = 0; i < tempDeck.size() - 2; i++) {
            if (tempDeck.get(i).getRank().equals(tempDeck.get(i + 1).getRank()) && tempDeck.get(i).getRank().equals(tempDeck.get(i + 2).getRank())) {
                outputDeck.add(tempDeck.get(i));
                outputDeck.add(tempDeck.get(i + 1));
                outputDeck.add(tempDeck.get(i + 2));
                break;
            }
        }
        return outputDeck;
    }

    public ArrayList<Card> showTwoPairs(ArrayList<Card> cards) {
        cards.sort(Comparator.comparing(Card::getRank));
        ArrayList<Card> outputDeck = new ArrayList<>();
        ArrayList<Card> tempDeck = new ArrayList<>();
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getRank().equals(cards.get(i + 1).getRank())) {
                outputDeck.add(cards.get(i));
                outputDeck.add(cards.get(i + 1));
                break;
            }
        }
        tempDeck.addAll(cards);
        tempDeck.removeAll(outputDeck);
        outputDeck.addAll(showPair(tempDeck));
        return outputDeck;
    }

    public ArrayList<Card> showFull(ArrayList<Card> cards) {
        ArrayList<Card> outputDeck = new ArrayList<>(showPair(cards));
        ArrayList<Card> tempDeck = new ArrayList<>(cards);
        tempDeck.removeAll(outputDeck);
        outputDeck.addAll(showThree(tempDeck));
        return outputDeck;
    }

    public ArrayList<Card> showQuads(ArrayList<Card> cards) {
        cards.sort(Comparator.comparing(Card::getRank));
        ArrayList<Card> tempDeck = new ArrayList<>(cards);
        ArrayList<Card> outputDeck = new ArrayList<>();
        for (int i = 0; i < tempDeck.size() - 3; i++) {
            if (tempDeck.get(i).getRank().equals(tempDeck.get(i + 3).getRank())) {
                outputDeck.add(tempDeck.get(i));
                outputDeck.add(tempDeck.get(i + 1));
                outputDeck.add(tempDeck.get(i + 2));
                outputDeck.add(tempDeck.get(i + 3));
                break;
            }
        }
        return outputDeck;
    }

    public ArrayList<Card> showStreigh(ArrayList<Card> cards) {
        ArrayList<Card> tempList = new ArrayList<>(cards);
        ArrayList<Card> outputList = new ArrayList<>();
        tempList.sort(Comparator.comparing(Card::getRank));

        tempList = tempList.stream()
                .filter(distinctByKey(p -> p.getRank().number))
                .collect(Collectors.toCollection(ArrayList::new));


        for (int iterator = 1; iterator < tempList.size(); iterator++) {
            if (iterator == 1) outputList.add(tempList.get(iterator));
            if (tempList.get(iterator).getRank().number == tempList.get(iterator - 1).getRank().number + 1) {
                if (iterator == 1) outputList.add(tempList.get(iterator));
                else outputList.add(tempList.get(iterator));
            }
        }
        return outputList;
    }

    public ArrayList<Card> showColor(ArrayList<Card> cards) {
        ArrayList<Card> tempList = new ArrayList<>(cards);
        ArrayList<Card> outputList = new ArrayList<>();
        Predicate<Card> predicate;

        for (Colour kolor : Colour.values()) {
            predicate = s -> s.getColour().equals(kolor);
            if (tempList.stream().filter(predicate).count() > 4) {
                outputList = tempList.stream().filter(predicate).collect(Collectors.toCollection(ArrayList::new));
                break;
            }
        }

        return outputList;
    }

    public ArrayList<Card> showPoker(ArrayList<Card> cards) {
        return showStreigh(showColor(cards));
    }

}

