import com.dongbat.jbump.World;
import model.Model;
import model.PlayerCharacter;
import model.TiledEnvironmentCache;
import model.monsters.Cyclops;
import model.monsters.Monster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DamageTest {

    Monster monster;
    Model model;

    @BeforeEach
    public void setUp() {
        model = new Model();
        model.initialize(new TiledEnvironmentCache());
        monster = new Cyclops(5, 0, 1, 10, 10, model.getWorld());
        model.addEnemy(monster);
    }

    @Test
    public void playerTakesDamage() {
        float playerInitialHealth = model.getPlayer().getCurrentHealth();
        model.getPlayer().takeDamage(1);
        float playerFinalHealth = model.getPlayer().getCurrentHealth();
        assertTrue(playerFinalHealth < playerInitialHealth);
    }

    @Test
    public void enemyTakesDamage() {
        float enemyInitialHealth = monster.getCurrentHealth();
        monster.takeDamage(1);
        float enemyFinalHealth = monster.getCurrentHealth();
        assertTrue(enemyFinalHealth < enemyInitialHealth);
    }

    @Test
    public void enemyDespawns() {
        model.despawn(monster);
        assertFalse(model.getEnemyList().contains(monster));
    }

    @Test
    public void healthyEnemyDoesNotDespawn() {
        model.checkEnemyHealth();
        assertTrue(model.getEnemyList().contains(monster));
    }

    @Test
    public void partiallyDamagedEnemyDoesNotDespawn() {
        monster.takeDamage(1);
        model.checkEnemyHealth();
        assertTrue(model.getEnemyList().contains(monster));
    }

    @Test
    public void enemyDespawnsAtZeroHealth() {
        monster.takeDamage(10);
        model.checkEnemyHealth();
        assertFalse(model.getEnemyList().contains(monster));
    }

    @Test
    public void playerTakesCollisionDamage() {
        float initHP = model.getPlayer().getCurrentHealth();
        monster.setY(model.getPlayer().getY());
        monster.setX(model.getPlayer().getWidth()+1);
        monster.moveLeft();
        float finalHP = model.getPlayer().getCurrentHealth();
        assertTrue(initHP > finalHP);
    }
}
