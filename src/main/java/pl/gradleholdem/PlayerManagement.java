package pl.gradleholdem;
//TODO amount of players, addplayer, playerpos, deleteplayer,


import java.util.ArrayList;

public class PlayerManagement {
    private int amountOfPlayers;
    private ArrayList<Player> players;
    private PlayerPosition defaultPos;


    public PlayerManagement() {
        amountOfPlayers = 0;
        players = new ArrayList<>();
        defaultPos = PlayerPosition.BIG_BLIND;
    }



    protected void addPlayer(Player player){
        players.add(player);
    }

    public void addPlayer() {
        players.add(new Player(defaultPos));
        defaultPos.next();
        amountOfPlayers = players.size();
    }


}
