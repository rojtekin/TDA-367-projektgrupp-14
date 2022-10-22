import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.IntPoint;
import com.dongbat.jbump.World;
import model.Direction;
import model.IEntity;
import model.LivingEntity;
import model.PlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntityTest {

    private LivingEntity livingEntity;

    @BeforeEach
    public void setUp() { livingEntity = new PlayerCharacter(100, 100, new World<>()); }

    @Test
    public void moveUp_IncreasesEntityY() {
        float initialYPosition = livingEntity.getY();
        livingEntity.move(Direction.UP, livingEntity.getSpeed());
        float finalYPosition = livingEntity.getY();
        assertTrue(finalYPosition > initialYPosition);
    }

    @Test
    public void moveDown_DecreasesEntityY() {
        float initialYPosition = livingEntity.getY();
        livingEntity.move(Direction.DOWN, livingEntity.getSpeed());
        float finalYPosition = livingEntity.getY();
        assertTrue(finalYPosition < initialYPosition);
    }

    @Test
    public void moveRight_IncreasesEntityX() {
        float initialXPosition = livingEntity.getX();
        livingEntity.move(Direction.RIGHT, livingEntity.getSpeed());
        float finalXPosition = livingEntity.getX();
        assertTrue(finalXPosition > initialXPosition);
    }

    @Test
    public void moveLeft_DecreasesEntityX() {
        float initialXPosition = livingEntity.getX();
        livingEntity.move(Direction.LEFT, livingEntity.getSpeed());
        float finalXPosition = livingEntity.getX();
        assertTrue(finalXPosition < initialXPosition);
    }

    @Test
    public void isMoving_ReturnsTrue_AfterMoving() {
        livingEntity.move(Direction.RIGHT, livingEntity.getSpeed());
        assertTrue(livingEntity.isMoving());
    }

    @Test
    public void pushBack_ChangesThePositionOfTheEntity() {
        float initialXPosition = livingEntity.getX();
        livingEntity.pushBack(new IntPoint(1,0));
        float finalXPosition = livingEntity.getX();
        assertTrue(initialXPosition != finalXPosition);
    }

    @Test
    public void setWorld_ChangesWorld() {
        World<IEntity> initWorld = livingEntity.getWorld();
        livingEntity.setWorld(new World<IEntity>());
        World<IEntity> finalWorld = livingEntity.getWorld();
        assertTrue(initWorld != finalWorld);
    }

    @Test
    public void updatePosition_ChangesEntityPositionAfterMovement() {
        float initX = livingEntity.getX();
        livingEntity.getWorld().move(livingEntity.getBoundingbox(), 1, livingEntity.getY(), CollisionFilter.defaultFilter);
        livingEntity.updatePosition();
        float finalX = livingEntity.getX();
        assertTrue(finalX == 1 && finalX != initX);
    }
}
