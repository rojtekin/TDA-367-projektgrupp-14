import model.*;
import model.monsters.Cyclops;
import model.monsters.Monster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DamageTest {

    Model model;
    Monster monster;
    TiledMapCache environment;

    @BeforeEach
    public void setUp() {
        environment = new TiledMapCache();
        model = new Model(environment, new PlayerCharacter(0, 0, environment.getWorld()), environment.getSpawnPoints());
        monster = new Cyclops(100, 100, model.getWorld());
        model.addMonster(monster);
    }

    @Test
    void takeDamage_InflictsDamageToPlayer() {
        float playerInitialHealth = model.getPlayer().getCurrentHealth();
        PlayerCharacter player = (PlayerCharacter) model.getPlayer();
        player.takeDamage(1);
        float playerFinalHealth = model.getPlayer().getCurrentHealth();
        assertTrue(playerFinalHealth < playerInitialHealth);
    }

    @Test
    void takeDamage_InflictsDamageToMonster() {
        float enemyInitialHealth = monster.getCurrentHealth();
        monster.takeDamage(1);
        float enemyFinalHealth = monster.getCurrentHealth();
        assertTrue(enemyFinalHealth < enemyInitialHealth);
    }

    @Test
    void despawn_RemovesMonster() {
        model.despawn(monster);
        assertFalse(model.getMonsters().contains(monster));
    }

    @Test
    void despawnDeadNPCs_DoesNotDespawnMonsterAtFullHealth() {
        model.despawnDeadNPCs();
        assertTrue(model.getMonsters().contains(monster));
    }

    @Test
    void despawnDeadNPCs_DoesNotDespawnMonstersAtPartialHealth() {
        monster.takeDamage(1);
        model.despawnDeadNPCs();
        assertTrue(model.getMonsters().contains(monster));
    }

    @Test
    void despawnDeadNPCs_DespawnsMonstersAtZeroHealth() {
        monster.takeDamage(10);
        model.despawnDeadNPCs();
        assertFalse(model.getMonsters().contains(monster));
    }

    @Test
    void move_DamagesTouchedPlayer() {
        float initHP = model.getPlayer().getCurrentHealth();
        Cyclops cyclops = new Cyclops(model.getPlayer().getWidth()+16, model.getPlayer().getY(), model.getWorld());
        model.addMonster(cyclops);
        System.out.println(cyclops.getX());
        for (int i = 0; i < 17; i++)
            model.update();
        System.out.println(cyclops.getX());
        float finalHP = model.getPlayer().getCurrentHealth();
        assertTrue(initHP > finalHP);
    }

    @Test
    void weaponAttack_DamagesMonster() {
        Cyclops cyclops = new Cyclops(model.getPlayer().getWidth(), model.getPlayer().getY(), model.getWorld());
        float initHP = cyclops.getCurrentHealth();
        model.getPlayer().weaponAttack(315,45);
        float finalHP = cyclops.getCurrentHealth();
        assertTrue(initHP > finalHP);
    }
}
