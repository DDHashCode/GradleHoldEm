package pl.gradleholdem;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FigureCheck {
    public Deck cards;
    private Player player;

    static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
    public FigureCheck() {

    }

    public FigureCheck(Deck cards, Player player) {
        this.cards = cards;
        this.player = player;
        this.cards.addCardS(player.getPlayerCards());
        this.cards.deckStack.sort(Card::compareTo);




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

    //todo hasFigure trzeba sprawwdzic, nie dziala.
    public PokerLayout hasFigure() {
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
        } else if (hasTwoPairs(this.cards)) {
            return PokerLayout.TWO_PAIRS;
        } else if (hasPair(this.cards)) {
            return PokerLayout.PAIR;
        } else
            return PokerLayout.HIGH_CARD;

    }


    //todo not really working, create another method
    public PokerLayout hasFigureTwo() {
        if (hasRoyalFlush()) {
            return PokerLayout.ROYAL_FLUSH;
        } else if (hasStraightFlush()) {
            return PokerLayout.STRAIGHT_FLUSH;
        } else if (hasQuads()) {
            return PokerLayout.QUADS;
        } else if (hasColour()) {
            return PokerLayout.FLUSH;
        } else if (hasStreigh()) {
            return PokerLayout.STREIGH;
        } else if (hasThree() && !hasFull()) {
            return PokerLayout.THREE;
        } else if (hasTwoPairs()) {
            return PokerLayout.TWO_PAIRS;
        } else if (hasPair() && !hasFull()) {
            return PokerLayout.PAIR;
        } else if (hasFull()){
            return PokerLayout.FULL_HOUSE;
        } else {
            return PokerLayout.HIGH_CARD;
        }
    }


    private boolean hasHigh() {
        return !hasPair(this.cards);
    }

    private Card showHigh(Deck cards) {
        Card out = cards.deckStack.stream()
                .max(Comparator.comparing(Card::getRank))
                .get();
        return out;
    }

    private boolean hasPair(Deck cards) {
        Predicate<Card> predicate;
        boolean out = false;

        for (CardRank rank : CardRank.values()) {
            predicate = a -> a.getRank().equals(rank);
            if (cards.deckStack.stream().filter(predicate).count() == 2) {
                out = true;
                break;
            }
        }

        return out;
    }



    public boolean hasPair() {
        Predicate<Card> predicate;
        boolean out = false;

        for (CardRank rank : CardRank.values()) {
            predicate = a -> a.getRank().equals(rank);
            if (this.cards.deckStack.stream().filter(predicate).count() == 2) {
                out = true;
                break;
            }
        }

        return out;
    }



    public Deck showPair(Deck cards) {
        Predicate<Card> predicate;
        Deck out = new Deck();

        for (CardRank rank : CardRank.values()) {
            predicate = a -> a.getRank().equals(rank);
            if (cards.deckStack.stream().filter(predicate).count() == 2) {
                cards.deckStack.stream()
                        .filter(predicate)
                        .forEach(a -> {
                            out.addCardToDeck(a);
                        });
            }
        }
        return out;
    }

    private boolean hasThree(Deck cards) {
        Predicate<Card> predicate;
        boolean out = false;

        for (CardRank rank : CardRank.values()) {
            predicate = a -> a.getRank().equals(rank);
            if (cards.deckStack.stream().filter(predicate).count() == 3) {
                out = true;
                break;
            }
        }

        return out;
    }



    public boolean hasThree() {
        Predicate<Card> predicate;
        boolean out = false;

        for (CardRank rank : CardRank.values()) {
            predicate = a -> a.getRank().equals(rank);
            if (this.cards.deckStack.stream().filter(predicate).count() == 3) {
                out = true;
                break;
            }
        }

        return out;
    }



    private Deck showThree(Deck cards) {
        Predicate<Card> predicate;
        Deck out = new Deck();

        for (CardRank rank : CardRank.values()) {
            predicate = a -> a.getRank().equals(rank);
            if (cards.deckStack.stream().filter(predicate).count() == 3) {
                cards.deckStack.stream()
                        .filter(predicate)
                        .forEach(a -> {
                            out.addCardToDeck(a);
                        });
            }
        }
        return out;
    }

    private boolean hasTwoPairs(Deck cards) {
        Deck temp = new Deck(cards);
        for (Card card : showPair(cards).deckStack) {
            temp.removeCard(card);
        }
        return hasPair(cards) && hasPair(temp);
    }


    public boolean hasTwoPairs() {
        Deck temp = new Deck(this.cards);
        for (Card card : showPair(this.cards).deckStack) {
            temp.removeCard(card);
        }
        return hasPair(this.cards) && hasPair(temp);
    }



    private Deck showTwoPairs(Deck cards) {
        Deck outputDeck = new Deck(showPair(cards));
        Deck temp = new Deck(cards);
        for (Card card : outputDeck.deckStack) temp.removeCard(card);
        outputDeck.addCardS(showPair(temp));
        return outputDeck;
    }

    private boolean hasFull(Deck cards) {
        Deck temp = new Deck(cards);
        Deck t = showThree(cards);
        temp.deckStack.forEach(a -> t.removeCard(a));
        return hasPair(temp);
    }

    public boolean hasFull() {
        Deck temp = new Deck();
        Deck out = new Deck(this.cards);
        temp.addCardS(showThree(out)); // Three in temporary deck
        out.deckStack.removeAllElements();
        out.addCardS(this.cards);
        for (Card c : temp.deckStack) out.removeCard(c);
        return hasPair(out);
    }

    public Deck showThree() {
        Predicate<Card> predicate;
        Deck out = new Deck();

        for (CardRank rank : CardRank.values()) {
            predicate = a -> a.getRank().equals(rank);
            if (cards.deckStack.stream().filter(predicate).count() == 3) {
                cards.deckStack.stream()
                        .filter(predicate)
                        .forEach(a -> {
                            out.addCardToDeck(a);
                        });
            }
        }
        return out;
    }



    private Deck showFull(Deck cards) {
        Deck temp = new Deck(cards);
        Deck outputDeck = new Deck(showThree(cards));
        for (Card card : showThree(cards).deckStack) temp.removeCard(card);
        outputDeck.addCardS(temp);

        return outputDeck;
    }


    public boolean areNeighbours(int first, int second) {
        return first - second == 1 || first - second == -1;
    }

    private boolean hasStreigh(Deck cards) {
        Deck temp = new Deck(cards);
        int check = 0;
        int checker = temp.deckStack.get(0).getRank().getNumber();


        for (int i = 0; i < temp.deckStack.size(); i++) {
            if (check == 4) break;
            if (areNeighbours(checker, temp.deckStack.get(i).getRank().number)) {
                check++;
                checker = temp.deckStack.get(i).getRank().number;
            } else checker = temp.deckStack.get(i).getRank().number;
        }
        return check == 4;
    }

    public boolean hasStreigh() {
        Deck temp = new Deck(this.cards);
        temp.sortByRankAndDistinct();
        if (temp.deckStack.size() >= 5){
            return temp.deckStack.get(0).getRank().number + 1 == temp.deckStack.get(1).getRank().number &&
                    temp.deckStack.get(1).getRank().number + 1 == temp.deckStack.get(2).getRank().number &&
                    temp.deckStack.get(2).getRank().number + 1 == temp.deckStack.get(3).getRank().number &&
                    temp.deckStack.get(3).getRank().number + 1 == temp.deckStack.get(4).getRank().number;
        } else return false;

    }



    private Deck showStreigh(Deck cards) {
        Deck tempList = new Deck();
        Deck outputList = new Deck();
        tempList.deckStack.sort(Comparator.comparing(Card::getRank));

        cards.deckStack.stream()
                .filter(distinctByKey(p -> p.getRank()))
                .forEach(tempList::addCardToDeck);


        for (int iterator = 1; iterator < tempList.deckStack.size(); iterator++) {
            if (iterator == 1) outputList.addCardToDeck(tempList.deckStack.get(iterator));
            if (tempList.deckStack.get(iterator).getRank().number == tempList.deckStack.get(iterator - 1).getRank().number + 1) {
                if (iterator == 1) outputList.addCardToDeck(tempList.deckStack.get(iterator));
                else outputList.addCardToDeck(tempList.deckStack.get(iterator));
            }
        }
        return outputList;
    }


    public Deck showStreigh() {
        Deck tempList = new Deck();
        Deck outputList = new Deck();
        tempList.deckStack.sort(Comparator.comparing(Card::getRank));

        this.cards.deckStack.stream()
                .filter(distinctByKey(p -> p.getRank()))
                .forEach(tempList::addCardToDeck);


        for (int iterator = 1; iterator < tempList.deckStack.size(); iterator++) {
            if (iterator == 1) outputList.addCardToDeck(tempList.deckStack.get(iterator));
            if (tempList.deckStack.get(iterator).getRank().number == tempList.deckStack.get(iterator - 1).getRank().number + 1) {
                if (iterator == 1) outputList.addCardToDeck(tempList.deckStack.get(iterator));
                else outputList.addCardToDeck(tempList.deckStack.get(iterator));
            }
        }
        return outputList;
    }

    private boolean hasColour(Deck cards) {
        Predicate<Card> predicate;
        boolean out = false;

        for (Colour kolor : Colour.values()) {
            predicate = s -> s.getColour().equals(kolor);
            if (cards.deckStack.stream().filter(predicate).count() >= 5) {
                out = true;
                break;
            }
        }

        return out;
    }

    public boolean hasColour() {
        Predicate<Card> predicate;
        boolean out = false;

        for (Colour kolor : Colour.values()) {
            predicate = s -> s.getColour().equals(kolor);
            if (this.cards.deckStack.stream().filter(predicate).count() >= 5) {
                out = true;
                break;
            }
        }

        return out;
    } //tested, OK

    private Deck showColour(Deck cards) {
        Predicate<Card> predicate;
        Deck out = new Deck();

        for (Colour kolor : Colour.values()) {
            predicate = a -> a.getColour().equals(kolor);
            if (cards.deckStack.stream().filter(predicate).count() >= 5) {
                cards.deckStack.stream()
                        .filter(predicate)
                        .forEach(a -> {
                            out.addCardToDeck(a);
                        });
            }
        }
        return out;
    }

    public Deck showColour() {
        Predicate<Card> predicate;
        Deck out = new Deck();

        for (Colour kolor : Colour.values()) {
            predicate = a -> a.getColour().equals(kolor);
            if (this.cards.deckStack.stream().filter(predicate).count() >= 5) {
                this.cards.deckStack.stream()
                        .filter(predicate)
                        .forEach(a -> {
                            out.addCardToDeck(a);
                        });
            }
        }
        return out;
    }

    private boolean hasQuads(Deck cards) {
        Predicate<Card> predicate;
        boolean out = false;

        for (CardRank rank : CardRank.values()) {
            predicate = a -> a.getRank().equals(rank);
            if (cards.deckStack.stream().filter(predicate).count() == 4) {
                out = true;
                break;
            }
        }

        return out;
    }


    public boolean hasQuads() {
        Predicate<Card> predicate;
        boolean out = false;

        for (CardRank rank : CardRank.values()) {
            predicate = a -> a.getRank().equals(rank);
            if (this.cards.deckStack.stream().filter(predicate).count() == 4) {
                out = true;
                break;
            }
        }

        return out;
    }

    private Deck showQuads(Deck cards) {
        Predicate<Card> predicate;
        Deck out = new Deck();

        for (CardRank rank : CardRank.values()) {
            predicate = a -> a.getRank().equals(rank);
            if (cards.deckStack.stream().filter(predicate).count() == 4) {
                cards.deckStack.stream()
                        .filter(predicate)
                        .forEach(a -> {
                            out.addCardToDeck(a);
                        });
            }
        }
        return out;
    }

    private boolean hasStraightFlush(Deck cards) {
        return hasColour(showStreigh(cards));
    }

    public boolean hasStraightFlush() {
        return hasColour(showStreigh(this.cards));
    }

    private Deck showStraightFlush(Deck cards){ //Srednio mi się to podoba
        return showStreigh(cards);
    }

    private boolean hasRoyalFlush(Deck cards) {
        return hasStraightFlush(this.cards) &&
                cards.deckStack.stream().map(Card::getRank).anyMatch(CardRank.A::equals) &&
                cards.deckStack.stream().map(Card::getRank).anyMatch(CardRank.TEN::equals);
    }

    public boolean hasRoyalFlush() {
        return hasStraightFlush(this.cards) &&
                cards.deckStack.stream().map(Card::getRank).anyMatch(CardRank.A::equals) &&
                cards.deckStack.stream().map(Card::getRank).anyMatch(CardRank.TEN::equals);
    }

    private Deck showRoyalFlush(Deck cards) {
        return showStreigh(cards);
    }


}

