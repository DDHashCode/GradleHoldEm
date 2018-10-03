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

    //todo hasFigure trzeba sprawwdzic, nie dziala.
    public PokerLayout hasFigure() {
        if (hasRoyalFlush(this.cards)) {
            this.player.setLayoutCards(showRoyalFlush(this.cards));
            return PokerLayout.ROYAL_FLUSH;
        } else if (hasStraightFlush(this.cards)) {
            this.player.setLayoutCards(showStraightFlush(this.cards));
            return PokerLayout.STRAIGHT_FLUSH;
        } else if (hasQuads(this.cards)) {
            this.player.setLayoutCards(showQuads(this.cards));
            return PokerLayout.QUADS;
        } else if (hasFull(this.cards)) {
            this.player.setLayoutCards(showFull(this.cards));
            return PokerLayout.FULL_HOUSE;
        } else if (hasColour(this.cards)) {
            this.player.setLayoutCards(showColour(this.cards));
            return PokerLayout.FLUSH;
        } else if (hasStreigh(this.cards)) {
            this.player.setLayoutCards(showStreigh(this.cards));
            return PokerLayout.STREIGH;
        } else if (hasThree(this.cards)) {
            this.player.setLayoutCards(showThree(this.cards));
            return PokerLayout.THREE;
        } else if (hasTwoPairs(this.cards)) {
            this.player.setLayoutCards(showTwoPairs(this.cards));
            return PokerLayout.TWO_PAIRS;
        } else if (hasPair(this.cards)) {
            this.player.setLayoutCards(showPair(this.cards));
            return PokerLayout.PAIR;
        } else
            this.player.getLayoutCards().addCardToDeck(showHigh(this.cards));
            return PokerLayout.HIGH_CARD;

    }
//todo zrobic has/showpair two pairs i three tak jak zrobione sa quadsy. showthree done
    private boolean hasHigh() {
        return !hasPair(this.cards);
    }

    private Card showHigh(Deck cards) {
        Card out = cards.getCardDeck().stream()
                .max(Comparator.comparing(Card::getRank))
                .get();
        return out;
    }

    private boolean hasPair(Deck cards) {
        Predicate<Card> predicate;
        boolean out = false;

        for (CardRank rank : CardRank.values()) {
            predicate = a -> a.getRank().equals(rank);
            if (cards.getCardDeck().stream().filter(predicate).count() == 2) {
                out = true;
                break;
            }
        }

        return out;
    }

    private Deck showPair(Deck cards) {
        Predicate<Card> predicate;
        Deck out = new Deck();

        for (CardRank rank : CardRank.values()) {
            predicate = a -> a.getRank().equals(rank);
            if (cards.getCardDeck().stream().filter(predicate).count() == 2) {
                cards.getCardDeck().stream()
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
            if (cards.getCardDeck().stream().filter(predicate).count() == 3) {
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
            if (cards.getCardDeck().stream().filter(predicate).count() == 3) {
                cards.getCardDeck().stream()
                        .filter(predicate)
                        .forEach(a -> {
                            out.addCardToDeck(a);
                        });
            }
        }
        return out;

        /*
        Map<CardRank, Long> res = this.cards.getCardDeck().stream().collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));

        Deck tempDeck = new Deck();
        res.forEach((cardRank, aLong) -> {
            if (aLong == 3) {
                System.out.println(cardRank);
                this.cards.getCardDeck().stream()
                        .filter(card -> card.getRank().equals(cardRank))
                        .forEach(a -> tempDeck.addCardToDeck(a));
            }
        });
        return tempDeck;*/
    }

    private boolean hasTwoPairs(Deck cards) {
        Deck temp = new Deck(cards);
        for (Card card : showPair(cards).getCardDeck()) {
            temp.removeCard(card);
        }
        return hasPair(cards) && hasPair(temp);
    }

    private Deck showTwoPairs(Deck cards) {
        Deck outputDeck = new Deck(showPair(cards));
        Deck temp = new Deck(cards);
        for (Card card : outputDeck.getCardDeck()) temp.removeCard(card);
        outputDeck.addCardS(showPair(temp));
        return outputDeck;
    }

    private boolean hasFull(Deck cards) {
        Deck temp = cards;
        Deck t = showThree(cards);
        temp.getCardDeck().forEach(a -> t.removeCard(a));
        return hasPair(temp);
    }

    private Deck showFull(Deck cards) {
        Deck temp = new Deck(cards);
        Deck outputDeck = new Deck(showThree(cards));
        for (Card card : showThree(cards).getCardDeck()) temp.removeCard(card);
        outputDeck.addCardS(temp);

        return outputDeck;
    }


    private boolean areNeighbours(int first, int second) {
        return first - second == 1 || first - second == -1;
    }

    private boolean hasStreigh(Deck cards) {
        Deck temp = new Deck(cards);
        int check = 0;
        int checker = temp.getCardDeck().get(0).getRank().getNumber();


        for (int i = 0; i < temp.getCardDeck().size(); i++) {
            if (check == 4) break;
            if (areNeighbours(checker, temp.getCardDeck().get(i).getRank().number)) {
                check++;
                checker = temp.getCardDeck().get(i).getRank().number;
            } else checker = temp.getCardDeck().get(i).getRank().number;
        }
        return check == 4;
    }

    private Deck showStreigh(Deck cards) {
        Deck tempList = new Deck();
        Deck outputList = new Deck();
        tempList.getCardDeck().sort(Comparator.comparing(Card::getRank));

        cards.getCardDeck().stream()
                .filter(distinctByKey(p -> p.getRank()))
                .forEach(tempList::addCardToDeck);


        for (int iterator = 1; iterator < tempList.getCardDeck().size(); iterator++) {
            if (iterator == 1) outputList.addCardToDeck(tempList.getCardDeck().get(iterator));
            if (tempList.getCardDeck().get(iterator).getRank().number == tempList.getCardDeck().get(iterator - 1).getRank().number + 1) {
                if (iterator == 1) outputList.addCardToDeck(tempList.getCardDeck().get(iterator));
                else outputList.addCardToDeck(tempList.getCardDeck().get(iterator));
            }
        }
        return outputList;
    }

    private boolean hasColour(Deck cards) {
        Predicate<Card> predicate;
        boolean out = false;

        for (Colour kolor : Colour.values()) {
            predicate = s -> s.getColour().equals(kolor);
            if (cards.getCardDeck().stream().filter(predicate).count() > 4) {
                out = true;
                break;
            }
        }

        return out;
    }

    private Deck showColour(Deck cards) {
        Predicate<Card> predicate;
        Deck out = new Deck();

        for (Colour kolor : Colour.values()) {
            predicate = a -> a.getColour().equals(kolor);
            if (cards.getCardDeck().stream().filter(predicate).count() >= 5) {
                cards.getCardDeck().stream()
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
            if (cards.getCardDeck().stream().filter(predicate).count() == 4) {
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
            if (cards.getCardDeck().stream().filter(predicate).count() == 4) {
                cards.getCardDeck().stream()
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

    private Deck showStraightFlush(Deck cards){ //Srednio mi się to podoba
        return showStreigh(cards);
    }

    private boolean hasRoyalFlush(Deck cards) {
        return hasStraightFlush(this.cards) &&
                cards.getCardDeck().stream().map(Card::getRank).anyMatch(CardRank.A::equals) &&
                cards.getCardDeck().stream().map(Card::getRank).anyMatch(CardRank.TEN::equals);
    }

    private Deck showRoyalFlush(Deck cards) {
        return showStreigh(cards);
    }
}

