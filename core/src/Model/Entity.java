package Model;

import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;

public abstract class Entity {
    private int x;
    private int y;
    private int height;
    private int width;
    private int speed;
    private float health;
    private IModel model;
    private Item<Entity> boundingbox;
    private World<Entity> world;
    private CollisionFilter collisionType = CollisionFilter.defaultFilter;

    public CollisionFilter getCollisionType() {
        return collisionType;
    }
    public void setCollisionType (CollisionFilter collisionType) {
        this.collisionType = collisionType;
    }

    public void setModel(IModel model) {
        this.model = model;
    }

    public Entity(int x, int y, int height, int width, int speed,float health, IModel model) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.health = health;
        this.model = model;
        this.world = model.getWorld();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getSpeed() {
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
        boundingbox = world.add(new Item<Entity>(this), getX(), getY(), getWidth(), getHeight());
    }

    //Updates the player coordinates to match the boundingbox
    public void updatePosition() {
        setX((int) world.getRect(boundingbox).x);
        setY((int) world.getRect(boundingbox).y);
    }

    public void moveUp(){
        if (getY() + getHeight() <= model.getMapPixelHeight()){
            world.move(boundingbox, (float) getX(), (float) getY() + getSpeed(), CollisionFilter.defaultFilter);
            updatePosition();
        }
    }
    public void moveDown(){
        if (getY() >= 0) {
            world.move(boundingbox, (float) getX(), (float) getY() - getSpeed(), CollisionFilter.defaultFilter);
            updatePosition();
        }
    }
    public void moveRight(){
        if (getX() + getWidth() <= model.getMapPixelWidth()) {
            world.move(boundingbox, (float) getX() + getSpeed(), (float) getY(), CollisionFilter.defaultFilter);
            updatePosition();
        }
    }
    public void moveLeft(){
        if (getX() >= 0) {
            world.move(boundingbox, (float) getX() - getSpeed(), (float) getY(), CollisionFilter.defaultFilter);
            updatePosition();
        }
    }
}
