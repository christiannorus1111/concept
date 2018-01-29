import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.IntStream;

public class Referee implements Runnable {
    private Player[] players;
    private final int width;
    private final int height;

    public Referee(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void run() {
        Player winner = null;
        while (winner == null) {
            winner = findWinner();
        }
        winner.setGameFinished(true);
        System.out.println("Winner ID: " + winner.getId());

        LocalDateTime newDate = LocalDateTime.now();

        Duration duration = Duration.between(GameTest.oldDate, newDate);

        System.out.println("Duration " + duration.getSeconds() + " seconds");

    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Player findWinner() {
        int wIndex = 0;
        int outPlayer = 0;
        Player winner = null;
        for (int i = 0; i < players.length; i++) {
            if (players[i].isEjected())
                outPlayer++;
            else
                wIndex = i;
        }
        if (outPlayer == players.length - 1) {
            winner = players[wIndex];
        }
        return winner;
    }

    public void handlePlayer(Player player) {

        player.addYellowCard();

        System.out.println("Player[" + player.getId() + "] gets yellow card!");

        if (player.getNumberOfYellowCard() >= 2) {
            if (!player.isHaveRedCard()) {
                player.setNumberOfYellowCard(0);
                player.setHaveRedCard(true);
                System.out.println("Player[" + player.getId() + "] gets red card");
                outPlayer(player);

            } else {
                player.setEjected(true);
                outPlayer(player);
            }
        }

        findWinner();
    }

    public void outPlayer(Player player) {
        Random random = new Random();
        player.timeOut();
        player.setxCoordinate(random.nextInt(width));
        player.setyCoordinate(random.nextInt(height));
    }

    public void returnTimeOutPlayer(Player player) {
        Random random = new Random();

        if (player.getxCoordinate() > width || player.getxCoordinate() < 0) {
            player.setxCoordinate(random.nextInt(width));

        } else if (player.getyCoordinate() > height || player.getyCoordinate() < 0) {
            player.setyCoordinate(random.nextInt(height));
        }
    }

    /*create a stream of integers and filter them to make sure they are not in waiting, are not ejected
    * and with this list check if a player is to close to another player and if so, handle with cards*/
    synchronized public void checkPlayerMove(Player player) {
        returnTimeOutPlayer(player);
        IntStream.range(0, players.length).filter(i -> !players[i].isEjected() && !players[i].isWaitingMode() && players[i].getId() != player.getId() &&
                !player.isEjected()).filter(i -> isTooClose(player, players[i])).mapToObj(i -> player).forEach(this::handlePlayer);
    }

    /* Caclulate if a player is to close to another player by substracting player a position for player b poaition, adding them the
       them after taking them to the power of 2 and taking the aquare root to the result and then check if this number is less then the
       exclusive zone if this is the case the player must get a card.
    */
    private static boolean isTooClose(Player a, Player b) {
        double d = Math.sqrt(Math.pow(a.getxCoordinate() - b.getxCoordinate(), 2) + Math.pow(a.getyCoordinate() - b.getyCoordinate(), 2));
        if (d >= 0 && d <= (a.getExclusiveZone() + b.getExclusiveZone()))
            return true;
        return false;
    }

}