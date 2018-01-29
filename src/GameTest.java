import java.time.LocalDateTime;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class GameTest {

    public static LocalDateTime oldDate = LocalDateTime.now();
    private Field field;

    public void play(int width, int height, int numberOfPlayer, int privateZone, long moveInterval, long timout) {
        field = new Field(width, height, numberOfPlayer, privateZone, moveInterval, timout);
        Thread thread = new Thread(field, "field");
        thread.start();
}
    @Test
    public void testGame() {
        /* The rules specify a 100 x 100 meter field and move interval 1000 ms and timeout 10000 ms bur for
        faster regression test this is reduced */

        for(int i = 0; i < 100; i++) {
            GameTest game = new GameTest();
            try {
                game.play(50, 50, 10, 2, 100, 1000);
                // TODO check old games and compare to make sure player objects are not identical to make sure game is random
            } catch (Exception ex) {
                System.out.println("Error in game" + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        GameTest game = new GameTest();
        try {
            game.play(100, 100, 10, 2, 1000, 10000);
        } catch (Exception ex) {
            System.out.println("Error in game" + ex.getMessage());
        }
    }

}