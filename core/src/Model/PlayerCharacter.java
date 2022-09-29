package Model;


public class PlayerCharacter extends Entity implements IControllable {
    private boolean moving;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;

    public PlayerCharacter() {
        super(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 32, 32, 5, 10, "Player");
    }

    public void moveUp(){
        this.setY(this.getY() + this.getSpeed());
        // Player cannot go off-screen
        if(this.getY() + this.getHeight() > SCREEN_HEIGHT) this.setY(SCREEN_HEIGHT - this.getHeight());

        setMoving(true);
        setDirection(Direction.UP);

    }
    public void moveDown(){
        this.setY(this.getY() - this.getSpeed());

        // Player cannot go off-screen
        if(this.getY() < 0) { this.setY(0);
        }
        setMoving(true);
        setDirection(Direction.DOWN);
    }
    public void moveRight(){
        this.setX(this.getX() + this.getSpeed());
        // Player cannot go off-screen
        if(this.getX() > SCREEN_WIDTH - this.getWidth()) this.setX(SCREEN_WIDTH - this.getWidth());

        setMoving(true);
        setDirection(Direction.RIGHT);
    }
    public void moveLeft() {
        this.setX(this.getX() - this.getSpeed());
        // Player cannot go off-screen
        if (this.getX() < 0) {
            this.setX(0);
        }
        setMoving(true);
        setDirection(Direction.LEFT);
    }
    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
