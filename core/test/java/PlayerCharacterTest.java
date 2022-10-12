import com.dongbat.jbump.World;
import model.Entity;
import model.PlayerCharacter;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerCharacterTest {

    PlayerCharacter player;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;

    @BeforeEach
    public void setUp() {
        player = new PlayerCharacter(0, 0, new World<>());
    }

    /*
    @Test
    public void moveUp_IncreasesPlayerY() {
        float initialYPosition = player.getY();
        player.moveUp();
        float finalYPosition = player.getY();
        assertTrue(finalYPosition > initialYPosition);
    }

    @Test
    public void moveUp_DoesNotMovePlayerOffScreen() {
        player.setY(SCREEN_HEIGHT - player.getHeight());
        player.moveUp();
        float finalYPosition = player.getY();
        assertTrue(finalYPosition + player.getHeight() <= SCREEN_HEIGHT);
    }

    @Test
    public void moveDown_DecreasesPlayerY() {
        float initialYPosition = player.getY();
        player.moveDown();
        float finalYPosition = player.getY();
        assertTrue(finalYPosition < initialYPosition);
    }

    @Test
    public void moveDown_DoesNotMovePlayerOffScreen() {
        player.setY(0);
        player.moveDown();
        float finalYPosition = player.getY();
        assertTrue(finalYPosition >= 0);
    }

    @Test
    public void moveRight_IncreasesPlayerX() {
        float initialXPosition = player.getX();
        player.moveRight();
        float finalXPosition = player.getX();
        assertTrue(finalXPosition > initialXPosition);
    }

    @Test
    public void moveRight_DoesNotMovePlayerOffScreen() {
        player.setX(SCREEN_WIDTH - player.getWidth());
        player.moveRight();
        float finalXPosition = player.getX();
        assertTrue(finalXPosition + player.getWidth() <= SCREEN_WIDTH);
    }

    @Test
    public void moveLeft_DecreasesPlayerX() {
        float initialXPosition = player.getX();
        player.moveLeft();
        float finalXPosition = player.getX();
        assertTrue(finalXPosition < initialXPosition);
    }

    @Test
    public void moveLeft_DoesNotMovePlayerOffScreen() {
        player.setX(0);
        player.moveLeft();
        float finalXPosition = player.getX();
        assertTrue(finalXPosition >= 0);
    }
    */
}
