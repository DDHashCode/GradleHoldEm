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

    public void addPlayer() {
        players.add(new Player(defaultPos));
        defaultPos.next();
    }

    public ArrayList<Player> getPlayersList() {
        return players;
    }


}
