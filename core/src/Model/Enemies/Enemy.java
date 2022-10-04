package Model.Enemies;

import Model.Entity;
import com.dongbat.jbump.World;

public abstract class Enemy extends Entity {
    private double damage;
    private String enemyName = ""; // Should be the same as its corresponding prefix under assets.
    private boolean stuckInXDirection = false;
    private boolean stuckInYDirection = false;

    public Enemy(float x, float y, float height, float width, float speed, float health, float damage, World<Entity> world, String entityName) {
        super(x, y, height, width, speed, health, world, entityName);
        this.damage = damage;
    }

    /**
     * Moves the enemy toward the player character.
     * @param playerX the x-coordinate of the player character.
     * @param playerY the y-coordinate of the player character.
     */
    public void moveTowardPlayer(float playerX, float playerY) {
        float xDistance = playerX - this.getX();
        float yDistance = playerY - this.getY();
        float initialX = this.getX();
        float initialY = this.getY();

        if ((Math.abs(xDistance) > 16) && !stuckInYDirection) {
            if (xDistance > 0) {
                this.moveRight();
            }
            else {
                this.moveLeft();
            }
            stuckInXDirection = false;
            if (this.getX() == initialX) {
                stuckInXDirection = true;
                this.moveUp();
            }
        }
        else if(!stuckInXDirection){
            if (yDistance > 0) {
                this.moveUp();
            }
            else {
                this.moveDown();
            }
            stuckInYDirection = false;
            if (this.getY() == initialY) {
                stuckInYDirection = true;
                this.moveRight();
            }
        }
    }
}
