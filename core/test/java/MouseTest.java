import com.dongbat.jbump.World;
import model.Time;
import model.monsters.Mouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MouseTest {
    private Mouse mouse;

    @BeforeEach
    public void setUp() { mouse = new Mouse(100, 100,1,1, new World<>()); }

    @Test
    public void moveTowardsPlayer_DecreasesTheDistanceBetweenMouseAndPlayer() {
        float playerX = 400;
        float playerY = 400;
        float initialXDistance = Math.abs(playerX - mouse.getX());
        float initialYDistance = Math.abs(playerY - mouse.getY());
        double initialDistance = Math.hypot(initialXDistance, initialYDistance);

        for (int i = 0; i < 60; i++) { Time.getInstance().tick(); }
        mouse.moveTowardPlayer(playerX, playerY);

        float finalXDistance = Math.abs(playerX - mouse.getX());
        float finalYDistance = Math.abs(playerY - mouse.getY());
        double finalDistance = Math.hypot(finalXDistance, finalYDistance);
        assertTrue(finalDistance < initialDistance);
    }
}
