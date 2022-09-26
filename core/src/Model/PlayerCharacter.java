package Model;


import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;

public class PlayerCharacter extends Entity implements IControllable, ICollisionEntity {
    private static PlayerCharacter instance;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;
    private Item<Entity> collisionbox;
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
        instance.setY(instance.getY() + instance.getSpeed());
        world.move(collisionbox, (float) getX(), (float) getY() + getSpeed(), CollisionFilter.defaultFilter);
        // Player cannot go off-screen
        if(instance.getY() + instance.getHeight() > SCREEN_HEIGHT) instance.setY(SCREEN_HEIGHT - instance.getHeight());

    }
    public void moveDown(){
        instance.setY(instance.getY() - instance.getSpeed());
        world.move(collisionbox, (float) getX(), (float) getY() - getSpeed(), CollisionFilter.defaultFilter);
        // Player cannot go off-screen
        if(instance.getY() < 0) { instance.setY(0);
        }
    }
    public void moveRight(){
        instance.setX(instance.getX() + instance.getSpeed());
        world.move(collisionbox, (float) getX() + getSpeed(), (float) getY(), CollisionFilter.defaultFilter);
        // Player cannot go off-screen
        if(instance.getX() > SCREEN_WIDTH - instance.getWidth()) instance.setX(SCREEN_WIDTH - instance.getWidth());

    }
    public void moveLeft(){
        instance.setX(instance.getX() - instance.getSpeed());
        world.move(collisionbox, (float) getX() - getSpeed(), (float) getY(), CollisionFilter.defaultFilter);
        // Player cannot go off-screen
        if(instance.getX() < 0) { instance.setX(0); }

    }

    public void setWorld (World<Entity> world) {
        this.world = world;
        addCollision();
    }

    public void addCollision() {
        collisionbox = world.add(new Item<Entity>(instance), getX(), getY(), getWidth(), getHeight());
    }
}
