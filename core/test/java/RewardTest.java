import model.Model;
import model.PlayerCharacter;
import model.TiledMapCache;
import model.rewards.LivingTrait;
import model.rewards.Reward;
import model.rewards.RewardSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RewardTest {


    Model model;
    RewardSystem rewardSystem;

    @BeforeEach
    public void setUp() {
        TiledMapCache tmc = new TiledMapCache();
        PlayerCharacter player = new PlayerCharacter(0,0, tmc.getWorld());
        model = new Model(tmc, player, tmc.getSpawnPoints());
        rewardSystem = new RewardSystem();
    }

    @Test
    public void levelUpChecker_FalseWhenPlayerJustSpawned() {
        assertFalse(rewardSystem.levelUpChecker(model.getPlayer()));
    }

    @Test
    public void levelUpChecker_FalseWhenPlayerExperienceLessThan100() {
        model.getPlayer().gainExperience(99);
        assertFalse(rewardSystem.levelUpChecker(model.getPlayer()));
    }

    @Test
    public void levelUpChecker_TrueWhenPlayerExperienceIs100() {
        model.getPlayer().gainExperience(100);
        assertTrue(rewardSystem.levelUpChecker(model.getPlayer()));
    }

    @Test
    public void getPerkList_EmptyByDefault() {
        assertTrue(model.getPlayer().getPerkList().isEmpty());
    }

    @Test
    public void getRandomReward_ReturnsRewardWhenPlayerPerkListIsEmpty() {
        Reward reward = rewardSystem.getRandomReward(model.getPlayer());
        assertTrue(reward != null);
    }

    @Test
    public void getRandomReward_DoesNotReturnPerkIfPerkListFull() {
        model.getPlayer().getPerkList().add(Reward.GLASS_CANNON);
        model.getPlayer().getPerkList().add(Reward.TANK);
        model.getPlayer().getPerkList().add(Reward.SPEED_DEVIL);
        Reward reward = rewardSystem.getRandomReward(model.getPlayer());
        assertTrue(reward != Reward.TANK && reward != Reward.GLASS_CANNON
        && reward != Reward.SPEED_DEVIL);
    }

    @Test
    public void applyReward_AddsPerkToPlayer() {
        rewardSystem.applyReward(model.getPlayer(), Reward.SPEED_DEVIL);
        assertTrue(model.getPlayer().getPerkList().get(0) == Reward.SPEED_DEVIL);
    }

    @Test
    public void applyReward_AddsTweakToPlayer() {
        int initSize = model.getPlayer().getTweaks().get(LivingTrait.SPEED).size();
        rewardSystem.applyReward(model.getPlayer(), Reward.SPEED_INCREASE);
        int finalSize = model.getPlayer().getTweaks().get(LivingTrait.SPEED).size();
        assertTrue(finalSize > initSize);
    }

    @Test
    public void tweaks_affectPlayerMaxHealth() {
        float initHealth = model.getPlayer().getMaxHealth();
        rewardSystem.applyReward(model.getPlayer(), Reward.HEALTH_INCREASE);
        float finalHealth = model.getPlayer().getMaxHealth();
        assertTrue(finalHealth > initHealth);
    }

    @Test
    public void tweaks_affectPlayerCurrentHealth() {
        PlayerCharacter player = (PlayerCharacter) model.getPlayer();
        player.takeDamage(5);
        float initHealth = model.getPlayer().getCurrentHealth();
        rewardSystem.applyReward(model.getPlayer(), Reward.HEALTH_INCREASE);
        float finalHealth = model.getPlayer().getCurrentHealth();
        assertTrue(finalHealth > initHealth);
    }

    @Test
    public void tweaks_affectPlayerSpeed() {
        float initSpeed = model.getPlayer().getSpeed();
        rewardSystem.applyReward(model.getPlayer(), Reward.SPEED_INCREASE);
        float finalSpeed = model.getPlayer().getSpeed();
        assertTrue(finalSpeed > initSpeed);
    }

    @Test
    public void tweaks_affectPlayerDamage() {
        float initDamage = model.getPlayer().getDamage();
        rewardSystem.applyReward(model.getPlayer(), Reward.DAMAGE_INCREASE);
        float finalDamage = model.getPlayer().getDamage();
        assertTrue(finalDamage > initDamage);
    }
}
