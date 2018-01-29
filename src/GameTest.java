import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.Random;

public class GameTest {

    public static LocalDateTime oldDate = LocalDateTime.now();

    public void play(int width, int height, int numberOfPlayer, int privateZone, long moveInterval, long timout) {
        Field field = new Field(width, height, numberOfPlayer, privateZone, moveInterval, timout);
        Thread thread = new Thread(field, "field");
        thread.start();
}
    @Test
    public void testGame() {
        /* The rules specify a 100 x 100 meter field and move interval 1000 ms and timeout 10000 ms bur for
        faster regression test this is reduced */
        GameTest game = new GameTest();
        try {
            game.play(30, 30, 10, 2, 100, 1000);
        } catch (Exception ex) {
            System.out.println("Error in game" + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        GameTest game = new GameTest();
        try {
            //game.play(100, 100, 10, 2, 1000, 10000);
            Random rn = new Random();

            for(int i =0; i < 100; i++)
            {
                int answer = rn.nextInt(10) + 1;
                System.out.println(answer);
            }
        } catch (Exception ex) {
            System.out.println("Error in game" + ex.getMessage());
        }
    }

}