package Model;


import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;

public class PlayerCharacter extends Entity implements IControllable, ICollisionEntity {
    private static PlayerCharacter instance;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;
    private Item<Entity> boundingbox;
    private World<Entity> world;


    public PlayerCharacter() {
        super(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 32, 32, 5, 10);
    }

    public static PlayerCharacter instance() {
        if (instance == null)
            instance = new PlayerCharacter();
        return instance;
    }

    public void moveUp(){
        world.move(boundingbox, (float) getX(), (float) getY() + getSpeed(), CollisionFilter.defaultFilter);
        updatePosition();
        // Player cannot go off-screen
        if(instance.getY() + instance.getHeight() > SCREEN_HEIGHT) instance.setY(SCREEN_HEIGHT - instance.getHeight());

    }
    public void moveDown(){
        world.move(boundingbox, (float) getX(), (float) getY() - getSpeed(), CollisionFilter.defaultFilter);
        updatePosition();
        // Player cannot go off-screen
        if(instance.getY() < 0) { instance.setY(0);
        }
    }
    public void moveRight(){
        world.move(boundingbox, (float) getX() + getSpeed(), (float) getY(), CollisionFilter.defaultFilter);
        updatePosition();
        // Player cannot go off-screen
        if(instance.getX() > SCREEN_WIDTH - instance.getWidth()) instance.setX(SCREEN_WIDTH - instance.getWidth());

    }
    public void moveLeft(){
        world.move(boundingbox, (float) getX() - getSpeed(), (float) getY(), CollisionFilter.defaultFilter);
        updatePosition();
        // Player cannot go off-screen
        if(instance.getX() < 0) { instance.setX(0); }

    }

    //Sets the world that the player will collide in
    public void setWorld (World<Entity> world) {
        this.world = world;
        addCollision();
    }

    //Adds the bounding box to the world
    public void addCollision() {
        boundingbox = world.add(new Item<Entity>(instance), getX(), getY(), getWidth(), getHeight());
    }

    //Updates the player coordinates to match the boundingbox
    public void updatePosition() {
        setX((int) world.getRect(boundingbox).x);
        setY((int) world.getRect(boundingbox).y);
    }
}
