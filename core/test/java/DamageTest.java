import com.dongbat.jbump.World;
import model.Model;
import model.PlayerCharacter;
import model.monsters.Cyclops;
import model.monsters.Monster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DamageTest {

    PlayerCharacter player;
    Monster monster;
    Model model;

    @BeforeEach
    public void setUp() {
        player = new PlayerCharacter(0, 0, new World<>());
        monster = new Cyclops(0, 0, 10, 10, 10, new World<>());
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
        float enemyInitialHealth = monster.getCurrentHealth();
        monster.takeDamage(1);
        float enemyFinalHealth = monster.getCurrentHealth();
        assertTrue(enemyFinalHealth < enemyInitialHealth);
    }

    @Test
    public void enemyDespawns() {
        model.addEnemy(monster);
        model.despawn(monster);
        assertTrue(!(model.getEnemyList().contains(monster)));
    }

    @Test
    public void healthyEnemyDoesNotDespawn() {
        model.addEnemy(monster);
        model.checkEnemyHealth();
        assertTrue(model.getEnemyList().contains(monster));
    }

    @Test
    public void partiallyDamagedEnemyDoesNotDespawn() {
        model.addEnemy(monster);
        monster.takeDamage(1);
        model.checkEnemyHealth();
        assertTrue(model.getEnemyList().contains(monster));
    }

    @Test
    public void enemyDespawnsAtZeroHealth() {
        model.addEnemy(monster);
        monster.takeDamage(10);
        model.checkEnemyHealth();
        assertFalse(model.getEnemyList().contains(monster));
    }
}
