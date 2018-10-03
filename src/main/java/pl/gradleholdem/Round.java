package pl.gradleholdem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Round {
    private Gameplay game;
    private Scanner scanner = new Scanner(System.in);
    private int readIN;
    private FigureCheck whatDoIHave;
    private Map<Player, PokerLayout> figureResult = new HashMap<>();


    public Round(Gameplay game) {
        this.game = game;

        setDidntBetYet();
        //firstBet();
        game.cardMGM.firstDistribution(game.playerMGM);
        //bet();
        game.cardMGM.setFlop();
        //bet();
        game.cardMGM.setTurn();
        //bet();
        game.cardMGM.setRiver();
        //bet();


        for(Player player : game.playerMGM.getPlayersList()){
            whatDoIHave = new FigureCheck(game.cardMGM.getCardOnTable(), player);
            figureResult.put(player, whatDoIHave.hasFigure());
            player.setLayout(whatDoIHave.hasFigure());
        }

        System.out.println("\nHereHereBitte");
        System.out.println(game.cardMGM.getCardOnTable().getCardDeck());
        //figureResult.forEach((a, b) -> System.out.println(a.getNickName() + " : " + b + " : " + a.getLayoutCards().getCardDeck()));

    }

    private void firstBet() {
        game.playerMGM.getPlayersList().stream()
                .filter(a -> a.getPos().equals(PlayerPosition.SMALL_BLIND))
                .forEach(a -> {
                    a.removeCash(game.cashMGM.getSmallBlind());
                    game.cashMGM.addChipsToStack(game.cashMGM.getSmallBlind());
                    a.setStatus(BetOptions.CALL);
                });
        game.playerMGM.getPlayersList().stream()
                .filter(a -> a.getPos().equals(PlayerPosition.BIG_BLIND))
                .forEach(a -> {
                    a.removeCash(game.cashMGM.getBigBlind());
                    game.cashMGM.addChipsToStack(game.cashMGM.getBigBlind());
                    a.setStatus(BetOptions.CALL);
                });
        game.playerMGM.getPlayersList().stream()
                .filter(a -> !(a.getPos().equals(PlayerPosition.SMALL_BLIND) ||
                        a.getPos().equals(PlayerPosition.BIG_BLIND) ||
                        !a.getStatus().equals(BetOptions.ALLIN) ||
                        !a.getStatus().equals(BetOptions.PASS)))
                .forEach(a -> {
                    System.out.println(a.getNickName());
                    System.out.println("Actual bet is: " + game.cashMGM.getActualBet());
                    System.out.println("Stack is: " + game.cashMGM.getChipsStack());
                    System.out.println("1. Call\n2. Raise\n3. Pass");
                    readIN = scanner.nextInt();
                    switch (readIN) {
                        case 1: //Call
                            call(a);
                            break;
                        case 2: //Raise
                            raise(a);
                            break;
                        case 3: //Pass
                            a.setStatus(BetOptions.PASS);
                            break;
                        default:
                            System.out.println("Aborting program.");
                            System.exit(0x1);
                            break;
                    }
                });

    }

    private void bet() {
        setDidntBetYet();
        game.playerMGM.getPlayersList().stream()
                .filter(a -> !a.getStatus().equals(BetOptions.PASS))
                .forEach(a -> {
                    System.out.println(a.getNickName());
                    System.out.println("Actual bet is: " + game.cashMGM.getActualBet());
                    System.out.println("Stack is: " + game.cashMGM.getChipsStack());
                    System.out.println("1. Call\n2. Raise\n3. Pass");
                    readIN = scanner.nextInt();
                    switch (readIN) {
                        case 1: //Call
                            call(a);
                            break;
                        case 2: //Raise
                            raise(a);
                            break;
                        case 3: //Pass
                            a.setStatus(BetOptions.PASS);
                            break;
                        default:
                            System.out.println("Aborting program.");
                            System.exit(0x1);
                            break;
                    }
                });
    }

    private void raise(Player pl) {
        System.out.println("How much do you want to raise?");
        readIN = scanner.nextInt();
        if (readIN < pl.getCash() && readIN > 0) {
            pl.removeCash(readIN);
            game.cashMGM.addChipsToStack(readIN);
            game.cashMGM.setActualBet(readIN);
            pl.setStatus(BetOptions.RAISE);
        } else if (readIN <= 0) {
            System.out.println("You have to specify proper amount of money");
            raise(pl);
        } else if (readIN == pl.getCash()) {
            pl.removeCash(readIN);
            game.cashMGM.addChipsToStack(readIN);
            game.cashMGM.setActualBet(readIN);
            pl.setStatus(BetOptions.ALLIN);
        } else {
            System.out.println("You don't have these money.");
            raise(pl);
        }

    }

    private void call(Player pl) {
        if (game.cashMGM.getActualBet() < pl.getCash()) {
            pl.removeCash(game.cashMGM.getActualBet());
            game.cashMGM.addChipsToStack(game.cashMGM.getActualBet());
            pl.setStatus(BetOptions.CALL);
        } else if (game.cashMGM.getActualBet() >= pl.getCash()) {
            pl.removeCash(pl.getCash());
            game.cashMGM.addChipsToStack(pl.getCash());
            pl.setStatus(BetOptions.ALLIN);
        }

    }

    private void setDidntBetYet() {
        this.game.playerMGM.getPlayersList()
                .stream()
                .filter(a -> !a.getStatus().equals(BetOptions.ALLIN))
                .forEach(a -> a.setStatus(BetOptions.DIDNT_BET_YET));
    }


}
