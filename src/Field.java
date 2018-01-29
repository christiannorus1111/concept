
public class Field implements Runnable {
    private final int width;
    private final int height;

    public Player[] getPlayers() {
        return players;
    }

    private final Player[] players;
    private final Referee referee;

    public void run() {
        Thread refereeThread = new Thread(referee,"Referee");
        refereeThread.start();
        for (int i = 0; i < players.length; i++) {
            Thread playerI = new Thread(players[i],"Player["+i+"]");
            playerI.start();
        }
    }

    public Field(int width, int height, int numberOfPlayer, int privateZone, long moveInterval, long timout) {
        this.width = width;
        this.height = height;
        referee = new Referee(width, height);
        players = new Player[numberOfPlayer];
        for (int i = 0; i < numberOfPlayer; i++) {
            players[i] = new Player(i, privateZone, referee, moveInterval, timout);
        }
        referee.setPlayers(players);

    }
}