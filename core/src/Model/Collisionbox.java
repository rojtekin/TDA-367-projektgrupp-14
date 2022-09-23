package Model;

public class Collisionbox extends Rectangle {

    private boolean isCollidable = true;

    public Collisionbox(int startX, int endX, int startY, int endY) {
        super(startX, endX, startY, endY);
    }

    public void setCollision(boolean collision) {
        isCollidable = collision;
    }

    public boolean hasCollision() {
        return isCollidable;
    }



}
