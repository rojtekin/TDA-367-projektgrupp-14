package Model;

import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;

public abstract class Entity {
    private float x;
    private float y;
    private float height;
    private float width;
    private float speed;
    private float health;
    private Item<Entity> boundingbox;
    private World<Entity> world;
    private CollisionFilter collisionType = CollisionFilter.defaultFilter;

    public CollisionFilter getCollisionType() {
        return collisionType;
    }
    public void setCollisionType (CollisionFilter collisionType) {
        this.collisionType = collisionType;
    }

    public Entity(float x, float y, float height, float width, float speed,float health, World<Entity> world) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.health = health;
        setWorld(world);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public World<Entity> getWorld() {
        return world;
    }

    //Sets the world that the player will collide in
    public void setWorld (World<Entity> world) {
        this.world = world;
        addCollision();
    }

    //Adds the bounding box to the world
    public void addCollision() {
        boundingbox = world.add(new Item<>(this), getX(), getY(), getWidth(), getHeight());
    }

    //Updates the player coordinates to match the boundingbox
    public void updatePosition() {
        setX(world.getRect(boundingbox).x);
        setY(world.getRect(boundingbox).y);
    }

    public void moveUp(){
        world.move(boundingbox, getX(), getY() + getSpeed(), CollisionFilter.defaultFilter);
        updatePosition();
    }
    public void moveDown(){
        world.move(boundingbox, getX(), getY() - getSpeed(), CollisionFilter.defaultFilter);
        updatePosition();
    }
    public void moveRight(){
        world.move(boundingbox, getX() + getSpeed(), getY(), CollisionFilter.defaultFilter);
        updatePosition();
    }
    public void moveLeft(){
        world.move(boundingbox, getX() - getSpeed(), getY(), CollisionFilter.defaultFilter);
        updatePosition();
    }
}
