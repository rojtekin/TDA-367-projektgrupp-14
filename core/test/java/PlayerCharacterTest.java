import Model.PlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerCharacterTest {

    PlayerCharacter player;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;

    @BeforeEach
    public void setUp() {
        //player = new PlayerCharacter();
        // Reset the position of the player character
        //player.setX(SCREEN_WIDTH / 2);
        //player.setY(SCREEN_HEIGHT / 2);
    }

    /*
    @Test
    public void moveUp_IncreasesPlayerY() {
        int initialYPosition = player.getY();
        player.moveUp();
        int finalYPosition = player.getY();
        assertTrue(finalYPosition > initialYPosition);
    }

    @Test
    public void moveUp_DoesNotMovePlayerOffScreen() {
        player.setY(SCREEN_HEIGHT - player.getHeight());
        player.moveUp();
        int finalYPosition = player.getY();
        assertTrue(finalYPosition + player.getHeight() <= SCREEN_HEIGHT);
    }

    @Test
    public void moveDown_DecreasesPlayerY() {
        int initialYPosition = player.getY();
        player.moveDown();
        int finalYPosition = player.getY();
        assertTrue(finalYPosition < initialYPosition);
    }

    @Test
    public void moveDown_DoesNotMovePlayerOffScreen() {
        player.setY(0);
        player.moveDown();
        int finalYPosition = player.getY();
        assertTrue(finalYPosition >= 0);
    }

    @Test
    public void moveRight_IncreasesPlayerX() {
        int initialXPosition = player.getX();
        player.moveRight();
        int finalXPosition = player.getX();
        assertTrue(finalXPosition > initialXPosition);
    }

    @Test
    public void moveRight_DoesNotMovePlayerOffScreen() {
        player.setX(SCREEN_WIDTH - player.getWidth());
        player.moveRight();
        int finalXPosition = player.getX();
        assertTrue(finalXPosition + player.getWidth() <= SCREEN_WIDTH);
    }

    @Test
    public void moveLeft_DecreasesPlayerX() {
        int initialXPosition = player.getX();
        player.moveLeft();
        int finalXPosition = player.getX();
        assertTrue(finalXPosition < initialXPosition);
    }

    @Test
    public void moveLeft_DoesNotMovePlayerOffScreen() {
        player.setX(0);
        player.moveLeft();
        int finalXPosition = player.getX();
        assertTrue(finalXPosition >= 0);
    }
    */
}
