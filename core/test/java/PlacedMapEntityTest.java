import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlacedMapEntityTest {

    PlacedMapEntity placedMapEntity;
    Model model;

    @BeforeEach
    public void setUp() {
        TiledMapCache tmc = new TiledMapCache();
        model = new Model(tmc, new PlayerCharacter(0, 0, tmc.getWorld()),tmc.getSpawnPoints());
        placedMapEntity = new PlacedMapEntity(model.getPlayer().getX()-32, model.getPlayer().getY(),32, 32, model.getWorld());
    }

    @Test
    public void PlacedMapEntity_HasCollision() {
        float initX = model.getPlayer().getX();
        model.getPlayer().move(Direction.LEFT, 1);
        float finalX = model.getPlayer().getX();
        assertTrue(initX == finalX);
    }
}
