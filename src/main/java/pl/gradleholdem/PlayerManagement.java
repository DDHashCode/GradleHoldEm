package pl.gradleholdem;
//TODO amount of players, addplayer, playerpos, deleteplayer,


import java.util.ArrayList;

public class PlayerManagement {
    private ArrayList<Player> players;
    private PlayerPosition defaultPos;


    public PlayerManagement() {
        players = new ArrayList<>();
        defaultPos = PlayerPosition.BIG_BLIND;
    }



    protected void addPlayer(Player player){
        players.add(player);
    }

    public void addPlayer(String name) {
        players.add(new Player(defaultPos, name));
        defaultPos.next();
    }

    public ArrayList<Player> getPlayersList() {
        return players;
    }

    public void listPlayers() {
        this.getPlayersList().stream()
                .forEach(a -> System.out.printf("%-5d%-20s%-5d%n", a.getID(), a.getNickName(), a.getCash()));
    }


}
