import java.util.stream.IntStream;

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
        IntStream.range(0, players.length).mapToObj(i -> new Thread(players[i], "Player[" + i + "]")).forEach(Thread::start);
    }

    public Field(int width, int height, int numberOfPlayer, int privateZone, long moveInterval, long timout) {
        this.width = width;
        this.height = height;
        referee = new Referee(width, height);
        players = new Player[numberOfPlayer];
        IntStream.range(0, numberOfPlayer).forEach(i -> players[i] = new Player(i, privateZone, referee, moveInterval, timout));
        referee.setPlayers(players);

    }
}