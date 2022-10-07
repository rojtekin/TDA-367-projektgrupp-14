import com.dongbat.jbump.World;
import model.Entity;
import model.PlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerCharacterTest {

    PlayerCharacter player;
    private static final float SCREEN_WIDTH = 800;
    private static final float SCREEN_HEIGHT = 480;

    @BeforeEach
    public void setUp() {
        player = new PlayerCharacter(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, new World<>());
        // Reset the position of the player character
    }

    @Test
    public void moveUp_IncreasesPlayerY() {
        float initialYPosition = player.getY();
        player.moveUp();
        float finalYPosition = player.getY();
        assertTrue(finalYPosition > initialYPosition);
    }

    @Test
    public void moveUp_DoesNotMovePlayerOffScreen() {
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
        //player.setX(SCREEN_WIDTH - player.getWidth());
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
        player.moveLeft();
        float finalXPosition = player.getX();
        assertTrue(finalXPosition >= 0);
    }
}
