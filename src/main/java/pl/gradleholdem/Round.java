package pl.gradleholdem;

import java.util.Scanner;

public class Round {
    private Gameplay game;
    Scanner scanner = new Scanner(System.in);
    int readIN;


    public Round(Gameplay game) {
        this.game = game;

        firstBet();
        game.cardMGM.firstDistribution(game.playerMGM);
        bet();
        /*game.cardMGM.setFlop();
        bet();
        game.cardMGM.setTurn();
        bet();
        game.cardMGM.setRiver();
        bet();*/
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
                .filter(a -> !(a.getPos().equals(PlayerPosition.SMALL_BLIND) || a.getPos().equals(PlayerPosition.BIG_BLIND)))
                .forEach(a -> {
                    System.out.println(a.getNickName());
                    System.out.println("Actual bet is: " + game.cashMGM.getActualBet());
                    System.out.println("Stack is: " + game.cashMGM.getChipsStack());
                    System.out.println("1. Call\n2. Raise\n3. Pass");
                    readIN = scanner.nextInt();
                    switch (readIN) {
                        case 1: //Call
                            a.removeCash(game.cashMGM.getActualBet());
                            game.cashMGM.addChipsToStack(game.cashMGM.getActualBet());
                            a.setStatus(BetOptions.CALL);
                            break;
                        case 2: //Raise
                            System.out.println("How much do you want to raise?");
                            readIN = scanner.nextInt();
                            a.removeCash(readIN);
                            game.cashMGM.addChipsToStack(readIN);
                            game.cashMGM.setActualBet(readIN);
                            a.setStatus(BetOptions.RAISE);
                            break;
                        case 3: //Pass
                            a.setStatus(BetOptions.PASS);
                            break;
                        default:
                            System.out.println("Aborting program.");
                            break;
                    }
                });

    }

    private void bet() {
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
                            a.removeCash(game.cashMGM.getActualBet());
                            game.cashMGM.addChipsToStack(game.cashMGM.getActualBet());
                            a.setStatus(BetOptions.CALL);
                            break;
                        case 2: //Raise
                            System.out.println("How much do you want to raise?");
                            readIN = scanner.nextInt();
                            a.removeCash(readIN);
                            game.cashMGM.addChipsToStack(readIN);
                            game.cashMGM.setActualBet(readIN);
                            a.setStatus(BetOptions.RAISE);
                            break;
                        case 3: //Pass
                            a.setStatus(BetOptions.PASS);
                            break;
                        default:
                            System.out.println("Aborting program.");
                            break;
                    }
                });
    }





}
