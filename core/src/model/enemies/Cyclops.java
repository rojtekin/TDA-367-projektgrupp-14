package model.enemies;

import model.Entity;
import com.dongbat.jbump.World;

public class Cyclops extends Enemy {
    private boolean stuckInXDirection = false;
    private boolean stuckInYDirection = false;

    public Cyclops(int x, int y, int speed, float health, float damage, World<Entity> world) {
        super(x, y, 32, 32, speed, health, damage, world,"Cyclops");
    }

    /**
     * Moves the cyclops toward the player character in the x-direction or in the y-direction.
     * If the cyclops gets stuck in the x-direction, it is moved up until it is no longer stuck.
     * If it gets stuck in the y-direction, it is moved right until it is no longer stuck.
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
