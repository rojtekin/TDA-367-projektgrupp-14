import model.*;
import model.monsters.Cyclops;
import model.monsters.Monster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DamageTest {

    Model model;
    Monster monster;
    TiledEnvironmentCache environment;

    @BeforeEach
    public void setUp() {
        environment = new TiledEnvironmentCache();
        model = new Model(environment, new PlayerCharacter(0, 0, environment.getWorld()), new ArrayList<>());
        monster = new Cyclops(100, 100, model.getWorld());
        model.addMonster(monster);
    }

    @Test
    public void takeDamage_InflictsDamageToPlayer() {
        float playerInitialHealth = model.getPlayer().getCurrentHealth();
        PlayerCharacter player = (PlayerCharacter) model.getPlayer();
        player.takeDamage(1);
        float playerFinalHealth = model.getPlayer().getCurrentHealth();
        assertTrue(playerFinalHealth < playerInitialHealth);
    }

    @Test
    public void takeDamage_InflictsDamageToMonster() {
        float enemyInitialHealth = monster.getCurrentHealth();
        monster.takeDamage(1);
        float enemyFinalHealth = monster.getCurrentHealth();
        assertTrue(enemyFinalHealth < enemyInitialHealth);
    }

    @Test
    public void despawn_RemovesMonster() {
        model.despawn(monster);
        assertFalse(model.getMonsters().contains(monster));
    }

    @Test
    public void despawnDeadNPCs_DoesNotDespawnMonsterAtFullHealth() {
        model.despawnDeadNPCs();
        assertTrue(model.getMonsters().contains(monster));
    }

    @Test
    public void despawnDeadNPCs_DoesNotDespawnMonstersAtPartialHealth() {
        monster.takeDamage(1);
        model.despawnDeadNPCs();
        assertTrue(model.getMonsters().contains(monster));
    }

    @Test
    public void despawnDeadNPCs_DespawnsMonstersAtZeroHealth() {
        monster.takeDamage(10);
        model.despawnDeadNPCs();
        assertFalse(model.getMonsters().contains(monster));
    }

    @Test
    public void move_DamagesTouchedPlayer() {
        float initHP = model.getPlayer().getCurrentHealth();
        Cyclops cyclops = new Cyclops(model.getPlayer().getWidth(), model.getPlayer().getY(), model.getWorld());
        cyclops.move(Direction.LEFT, cyclops.getSpeed());
        float finalHP = model.getPlayer().getCurrentHealth();
        assertTrue(initHP > finalHP);
    }
}
