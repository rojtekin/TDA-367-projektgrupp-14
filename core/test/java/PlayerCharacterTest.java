import model.Model;
import model.PlayerCharacter;
import model.TiledMapCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class PlayerCharacterTest {

    PlayerCharacter player;
    Model model;
    @BeforeEach
    public void setUp() {
        TiledMapCache tmc = new TiledMapCache();
        player = new PlayerCharacter(0, 0 , tmc.getWorld());
        model = new Model(tmc, player, tmc.getSpawnPoints());
    }

    @Test
    void gainExperience_IncreasesPlayerExperience() {
        float initExperience = player.getExperience();
        player.gainExperience(1);
        float finalExperience = player.getExperience();
        assertTrue(finalExperience > initExperience);
    }

    @Test
    void increaseLevel_IncreasesPlayerLevel() {
        float initLevel = player.getLevel();
        player.increaseLevel();
        float finalLevel = player.getLevel();
        assertTrue(finalLevel > initLevel);
    }

    @Test
    void gainExperience_IncreasesPlayerLevel() {
        float initLevel = player.getLevel();
        player.gainExperience(100);
        model.update();
        float finalLevel = player.getLevel();
        assertTrue(finalLevel > initLevel);
    }

    @Test
    void levelUpCheckAndApply_ResetsPlayerExperienceWhenLeveledUp() {
        player.gainExperience(100);
        float gainedExperience = player.getExperience();
        model.update();
        float playerExperience = player.getExperience();
        assertTrue(playerExperience == 0 && gainedExperience == 100);
    }

    @Test
    void increaseHealth_IncreasesHealth() {
        player.takeDamage(5);
        float initHealth = player.getCurrentHealth();
        player.increaseCurrentHealth(1);
        float finalHealth = player.getCurrentHealth();
        assertTrue(finalHealth == initHealth + 1);
    }

    @Test
    void increaseHealth_DoesNotIncreaseHealthBeyondMax() {
        player.increaseCurrentHealth(1);
        assertTrue(player.getCurrentHealth() == player.getMaxHealth());
    }
}
