package model.monsters;

import com.dongbat.jbump.Collisions;
import model.Faction;
import model.IEntity;
import model.LivingEntity;
import com.dongbat.jbump.World;
import view.ISoundSubscriber;

import java.util.ArrayList;
import java.util.List;

public abstract class Monster extends LivingEntity implements IMonsterSoundPublisher {

    private final int experience;
    private final int score;
    private final List<ISoundSubscriber> subscribers = new ArrayList<>();

    /**
     * Default constructor for all monsters
     * @param x x spawn coordinate
     * @param y y spawn coordinate
     * @param height height of the collisionbox
     * @param width width of the collisionbox
     * @param speed The speed at which the monster moves
     * @param health The health of the monster
     * @param damage sets the value for damage to be caused when attacking.
     *               Damage done by collisions is a tenth of this value
     * @param faction The faction that this character belongs to and won't cause damage
     *                towards
     * @param world The world that the monster will move and collide in
     */
    public Monster(float x, float y, float height, float width, float speed, float health, float damage, Faction faction, World<IEntity> world, int score, int experience) {
        super(x, y, height, width, speed, health, damage, faction, world);
        this.score = score;
        this.experience = experience;
    }

    /**
     * Moves the enemy toward the target.
     * @param targetX the x-coordinate of the target
     * @param targetY the y-coordinate of the target
     * @return the collisions that occurred when moving
     */
    abstract public Collisions moveTowardTarget(float targetX, float targetY);

    /**
     * @return returns the experience amount for killing the monster
     */
    public int getExperience() {
        return experience;
    }

    /**
     * @return returns the score amount for killing the monster
     */
    public int getScore() {
        return score;
    }

    @Override
    public void addSubscriber(ISoundSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISoundSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifyMonsterAttack() {
        for (ISoundSubscriber s : subscribers) {
            s.playEnemyHit();
        }
    }
}
