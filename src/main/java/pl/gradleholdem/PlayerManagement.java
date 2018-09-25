package pl.gradleholdem;
//TODO amount of players, addplayer, playerpos, deleteplayer,


import java.util.ArrayList;

public class PlayerManagement {
    private ArrayList<Player> players;
    private static PlayerPosition defaultPos;


    public PlayerManagement() {
        players = new ArrayList<>();
        defaultPos = PlayerPosition.BUTTON;
    }


    public void addPlayer(String name) {
        players.add(new Player(defaultPos, name));
        defaultPos = defaultPos.next().get();
    }

    public ArrayList<Player> getPlayersList() {
        return players;
    }

    public void listPlayers() {
        this.getPlayersList().stream()
                .forEach(a -> System.out.printf("%-5d%-20s%-5d%-10s%n", a.getID(), a.getNickName(), a.getCash(), a.getPos().toString()));
    }


}
