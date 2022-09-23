package Model;

//Abstract class for rectangles, to be used with collisionboxes, hitboxes and hurtboxes
public abstract class Rectangle {

    private int startX;
    private int endX;
    private int startY;
    private int endY;

    public Rectangle(int startX, int endX, int startY, int endY) {
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
    }

    public int getStartX() {
        return startX;
    }

    public int getEndX() {
        return endX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndY() {
        return endY;
    }

    public boolean overlaps(Rectangle collidable) {
        return (startX < collidable.getEndX() && startX > collidable.getStartX());
    }

}
