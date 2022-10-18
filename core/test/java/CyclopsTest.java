import com.dongbat.jbump.World;
import model.monsters.Cyclops;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CyclopsTest {

    private Cyclops cyclops;

    @BeforeEach
    public void setUp() { cyclops = new Cyclops(100, 100,1,1,1,new World<>()); }

    @Test
    public void moveTowardsPlayer_DecreasesTheDistanceBetweenCyclopsAndPlayer() {
        float playerX = 400;
        float playerY = 400;
        float initialXDistance = Math.abs(playerX - cyclops.getX());
        float initialYDistance = Math.abs(playerY - cyclops.getY());
        double initialDistance = Math.sqrt(Math.pow(initialXDistance, 2) + Math.pow(initialYDistance, 2));

        cyclops.moveTowardPlayer(playerX, playerY);
        float finalXDistance = Math.abs(playerX - cyclops.getX());
        float finalYDistance = Math.abs(playerY - cyclops.getY());
        double finalDistance = Math.sqrt(Math.pow(finalXDistance, 2) + Math.pow(finalYDistance, 2));
        assertTrue(finalDistance < initialDistance);
    }
}
