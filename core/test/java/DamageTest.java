import com.dongbat.jbump.World;
import model.Model;
import model.PlayerCharacter;
import model.enemies.Cyclops;
import model.enemies.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DamageTest {

    PlayerCharacter player;
    Enemy enemy;
    Model model;

    @BeforeEach
    public void setUp() {
        player = new PlayerCharacter(0, 0, new World<>());
        enemy = new Cyclops(0, 0, 10, 10, 10, new World<>());
        model = new Model();
    }

    @Test
    public void playerTakesDamage() {
        float playerInitialHealth = player.getCurrentHealth();
        player.takeDamage(1);
        float playerFinalHealth = player.getCurrentHealth();
        assertTrue(playerFinalHealth < playerInitialHealth);
    }

    @Test
    public void enemyTakesDamage() {
        float enemyInitialHealth = enemy.getCurrentHealth();
        enemy.takeDamage(1);
        float enemyFinalHealth = enemy.getCurrentHealth();
        assertTrue(enemyFinalHealth < enemyInitialHealth);
    }

    @Test
    public void enemyDespawns() {
        model.addEnemy(enemy);
        model.despawn(enemy);
        assertTrue(!(model.getEnemyList().contains(enemy)));
    }

    @Test
    public void healthyEnemyDoesNotDespawn() {
        model.addEnemy(enemy);
        model.checkEnemyHealth();
        assertTrue(model.getEnemyList().contains(enemy));
    }

    @Test
    public void partiallyDamagedEnemyDoesNotDespawn() {
        model.addEnemy(enemy);
        enemy.takeDamage(1);
        model.checkEnemyHealth();
        assertTrue(model.getEnemyList().contains(enemy));
    }

    @Test
    public void enemyDespawnsAtZeroHealth() {
        model.addEnemy(enemy);
        enemy.takeDamage(10);
        model.checkEnemyHealth();
        assertFalse(model.getEnemyList().contains(enemy));
    }
}
