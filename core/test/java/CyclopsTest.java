import com.dongbat.jbump.World;
import model.Direction;
import model.Model;
import model.PlayerCharacter;
import model.TiledMapCache;
import model.monsters.Cyclops;
import model.monsters.Monster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CyclopsTest {

    private Cyclops cyclops;

    @BeforeEach
    public void setUp() { cyclops = new Cyclops(100, 100, new World<>()); }

    @Test
    public void moveTowardsPlayer_DecreasesTheDistanceBetweenCyclopsAndPlayer() {
        float playerX = 400;
        float playerY = 400;
        float initialXDistance = Math.abs(playerX - cyclops.getX());
        float initialYDistance = Math.abs(playerY - cyclops.getY());
        double initialDistance = Math.hypot(initialXDistance, initialYDistance);

        cyclops.moveTowardPlayer(playerX, playerY);
        float finalXDistance = Math.abs(playerX - cyclops.getX());
        float finalYDistance = Math.abs(playerY - cyclops.getY());
        double finalDistance = Math.hypot(finalXDistance, finalYDistance);
        assertTrue(finalDistance < initialDistance);
    }
}
