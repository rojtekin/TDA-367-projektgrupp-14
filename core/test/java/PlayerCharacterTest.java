import com.dongbat.jbump.World;
import model.IEntity;
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
        model.initialize();
    }

    @Test
    public void gainExperience_IncreasesPlayerExperience() {
        float initExperience = player.getExperience();
        player.gainExperience(1);
        float finalExperience = player.getExperience();
        assertTrue(finalExperience > initExperience);
    }

    @Test
    public void increaseLevel_IncreasesPlayerLevel() {
        float initLevel = player.getLevel();
        player.increaseLevel();
        float finalLevel = player.getLevel();
        assertTrue(finalLevel > initLevel);
    }

    @Test
    public void gainExperience_IncreasesPlayerLevel() {
        float initLevel = player.getLevel();
        player.gainExperience(100);
        model.update();
        float finalLevel = player.getLevel();
        assertTrue(finalLevel > initLevel);
    }

    @Test
    public void levelUpCheckAndApply_ResetsPlayerExperienceWhenLeveledUp() {
        player.gainExperience(100);
        float gainedExperience = player.getExperience();
        model.update();
        float playerExperience = player.getExperience();
        assertTrue(playerExperience == 0 && gainedExperience == 100);
    }

    @Test
    public void increaseMaxHealth_IncreasesMaxHealth() {
        float initMaxHealth = player.getMaxHealth();
        player.increaseMaxHealth();
        float finalMaxHealth = player.getMaxHealth();
        assertTrue(finalMaxHealth > initMaxHealth);
    }

    @Test
    public void decreaseMaxHealth_decreasesMaxHealth() {
        float initMaxHealth = player.getMaxHealth();
        player.decreaseMaxHealth();
        float finalMaxHealth = player.getMaxHealth();
        assertTrue(finalMaxHealth < initMaxHealth);
    }
}
