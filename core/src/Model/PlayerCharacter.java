package Model;


public class PlayerCharacter extends Entity implements IControllable {
    private static PlayerCharacter instance;
    private boolean moving;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;

    public PlayerCharacter() {
        super(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 32, 32, 5, 10);
        this.moving = false;
    }

    public static PlayerCharacter instance() {
        if (instance == null)
            instance = new PlayerCharacter();
        return instance;
    }

    public void moveUp(){
        instance.setY(instance.getY() + instance.getSpeed());
        // Player cannot go off-screen
        if(instance.getY() + instance.getHeight() > SCREEN_HEIGHT) { instance.setY(SCREEN_HEIGHT - instance.getHeight()); }
        instance.setDirection(Direction.UP);
        instance.setMoving(true);
    }
    public void moveDown(){
        instance.setY(instance.getY() - instance.getSpeed());
        // Player cannot go off-screen
        if(instance.getY() < 0) { instance.setY(0); }
        instance.setDirection(Direction.DOWN);
        instance.setMoving(true);
    }
    public void moveRight(){
        instance.setX(instance.getX() + instance.getSpeed());
        // Player cannot go off-screen
        if(instance.getX() > SCREEN_WIDTH - instance.getWidth()) { instance.setX(SCREEN_WIDTH - instance.getWidth()); }
        instance.setDirection(Direction.RIGHT);
        instance.setMoving(true);
    }
    public void moveLeft(){
        instance.setX(instance.getX() - instance.getSpeed());
        // Player cannot go off-screen
        if(instance.getX() < 0) { instance.setX(0); }
        instance.setDirection(Direction.LEFT);
        instance.setMoving(true);
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
