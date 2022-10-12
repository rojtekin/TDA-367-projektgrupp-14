import com.dongbat.jbump.IntPoint;
import com.dongbat.jbump.World;
import model.Direction;
import model.Entity;
import model.PlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntityTest {

    private Entity entity;

    @BeforeEach
    public void setUp() { entity = new PlayerCharacter(100, 100, new World<>()); }

    @Test
    public void moveUp_IncreasesEntityY() {
        float initialYPosition = entity.getY();
        entity.move(Direction.UP);
        float finalYPosition = entity.getY();
        assertTrue(finalYPosition > initialYPosition);
    }

    @Test
    public void moveDown_DecreasesEntityY() {
        float initialYPosition = entity.getY();
        entity.move(Direction.DOWN);
        float finalYPosition = entity.getY();
        assertTrue(finalYPosition < initialYPosition);
    }

    @Test
    public void moveRight_IncreasesEntityX() {
        float initialXPosition = entity.getX();
        entity.move(Direction.RIGHT);
        float finalXPosition = entity.getX();
        assertTrue(finalXPosition > initialXPosition);
    }

    @Test
    public void moveLeft_DecreasesEntityX() {
        float initialXPosition = entity.getX();
        entity.move(Direction.LEFT);
        float finalXPosition = entity.getX();
        assertTrue(finalXPosition < initialXPosition);
    }

    @Test
    public void isMoving_ReturnsTrue_AfterMoving() {
        entity.move(Direction.RIGHT);
        assertTrue(entity.isMoving());
    }

    @Test
    public void pushBack_ChangesThePositionOfTheEntity() {
        float initialXPosition = entity.getX();
        entity.pushBack(new IntPoint(1,0));
        float finalXPosition = entity.getX();
        assertTrue(initialXPosition != finalXPosition);
    }
}
