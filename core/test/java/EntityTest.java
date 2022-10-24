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
    void moveUp_IncreasesEntityY() {
        float initialYPosition = livingEntity.getY();
        livingEntity.move(Direction.UP);
        float finalYPosition = livingEntity.getY();
        assertTrue(finalYPosition > initialYPosition);
    }

    @Test
    void moveDown_DecreasesEntityY() {
        float initialYPosition = livingEntity.getY();
        livingEntity.move(Direction.DOWN);
        float finalYPosition = livingEntity.getY();
        assertTrue(finalYPosition < initialYPosition);
    }

    @Test
    void moveRight_IncreasesEntityX() {
        float initialXPosition = livingEntity.getX();
        livingEntity.move(Direction.RIGHT);
        float finalXPosition = livingEntity.getX();
        assertTrue(finalXPosition > initialXPosition);
    }

    @Test
    void moveLeft_DecreasesEntityX() {
        float initialXPosition = livingEntity.getX();
        livingEntity.move(Direction.LEFT);
        float finalXPosition = livingEntity.getX();
        assertTrue(finalXPosition < initialXPosition);
    }

    @Test
    void isMoving_ReturnsTrue_AfterMoving() {
        livingEntity.move(Direction.RIGHT);
        assertTrue(livingEntity.isInMotion());
    }

    @Test
    void pushBack_ChangesThePositionOfTheEntity() {
        float initialXPosition = livingEntity.getX();
        livingEntity.pushBack(new IntPoint(1,0));
        float finalXPosition = livingEntity.getX();
        assertTrue(initialXPosition != finalXPosition);
    }

    @Test
    void setWorld_ChangesWorld() {
        World<IEntity> initWorld = livingEntity.getWorld();
        livingEntity.setWorld(new World<IEntity>());
        World<IEntity> finalWorld = livingEntity.getWorld();
        assertTrue(initWorld != finalWorld);
    }

    @Test
    void updatePosition_ChangesEntityPositionAfterMovement() {
        float initX = livingEntity.getX();
        livingEntity.getWorld().move(livingEntity.getBoundingbox(), 1, livingEntity.getY(), CollisionFilter.defaultFilter);
        livingEntity.updatePosition();
        float finalX = livingEntity.getX();
        assertTrue(finalX == 1 && finalX != initX);
    }
}
