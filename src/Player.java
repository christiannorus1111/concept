import java.util.Random;

public class Player implements Runnable {
    private int id;
    private int xCoordinate;
    private int yCoordinate;
    private Referee referee;
    private int exclusiveZone;
    private int numberOfYellowCard;
    private boolean haveRedCard = false;
    private boolean isEjected = false;
    private boolean waitingMode = false;
    private boolean gameFinished = false;
    private long moveInterval;
    private long timout;
    
    public Player(int id, int exclusiveZone, Referee referee, long moveInterval, long timout) {
        super();
        this.id = id;
        this.exclusiveZone = exclusiveZone;
        this.referee = referee;
        this.moveInterval = moveInterval;
        this.timout = timout;
    }

    public void move() {
        Random r = new Random();
        if (r.nextBoolean()) {
            if (!r.nextBoolean())
                this.xCoordinate++;
            else
                this.yCoordinate++;
        } else {
            if (!r.nextBoolean())
                this.xCoordinate--;
            else
                this.yCoordinate--;
        }
        referee.checkPlayerMove(this);
    }

    public void timeOut() {
        try {
            waitingMode = true;
            System.out.println("Player[" + id + "] got time out!");
            Thread.sleep(timout);
            System.out.println("Player[" + id + "] time out over!");
            waitingMode = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (!isEjected && !gameFinished) {
            try {
                Thread.sleep(moveInterval);
                move();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }

    public int getExclusiveZone() {
        return exclusiveZone;
    }

    public void setExclusiveZone(int exlusiveZone) {
        this.exclusiveZone = exclusiveZone;
    }

    public int getNumberOfYellowCard() {
        return numberOfYellowCard;
    }

    public void setNumberOfYellowCard(int numberOfYellowCard) {
        this.numberOfYellowCard = numberOfYellowCard;
    }

    public boolean isHaveRedCard() {
        return haveRedCard;
    }

    public void setHaveRedCard(boolean haveRedCard) {
        this.haveRedCard = haveRedCard;
    }

    public boolean isEjected() {
        return isEjected;
    }

    public void setEjected(boolean ejected) {
        isEjected = ejected;
    }

    public boolean isWaitingMode() {
        return waitingMode;
    }

    public void setWaitingMode(boolean waitingMode) {
        this.waitingMode = waitingMode;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public long getMoveTimeInterval() {
        return this.moveInterval;
    }

    public long getReenterTimeInterval() {
        return this.timout;
    }

    public void addYellowCard() {
        this.numberOfYellowCard++;
    }


}