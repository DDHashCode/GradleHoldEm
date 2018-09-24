package pl.gradleholdem;

import java.util.Scanner;

public class Round {
    private Gameplay game;
    Scanner scanner = new Scanner(System.in);
    int decision;


    public Round(Gameplay game) {
        this.game = game;
        System.out.println(this.game.cashMGM.getSmallBlind() + "\n" + this.game.cashMGM.getBigBlind() + "\n" + this.game.cashMGM.getChipsStack());

        game.cardMGM.firstDistribution(game.playerMGM);
        bet();
        game.cardMGM.setFlop();
        bet();
        game.cardMGM.setTurn();
        bet();
        game.cardMGM.setRiver();
        bet();
    }

    private void bet() {
        game.playerMGM.getPlayersList().stream()
                .forEach(c -> {
                    System.out.println("Player " + c.getNickName());
                    System.out.println("You need to call up to " + game.cashMGM.getChipsStack() + " or more..");
                    System.out.println("Decide:");
                    System.out.println("1. Call\n2. Raise\n3. Pass");
                    decision = scanner.nextInt();
                    switch (c.getPos()) {
                        case SMALL_BLIND:
                            c.removeCash(game.cashMGM.getSmallBlind());
                            break;
                        case BIG_BLIND:
                            c.removeCash(game.cashMGM.getBigBlind());
                            break;
                        default:
                            switch (decision) {
                                case 1:
                                    c.setStatus(BetOptions.CALL);
                                    break;
                                case 2:
                                    c.setStatus(BetOptions.RAISE);
                                    break;
                                case 3:
                                    c.setStatus(BetOptions.PASS);
                                    break;
                                default:
                                    break;
                            }
                            break;
                    }

                });

    }




      /*
        System.out.println(cardMGM.getCardOnTable().getCardDeck());*/
}
