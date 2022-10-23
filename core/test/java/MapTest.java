import model.TiledMapCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapTest {

    TiledMapCache tmc;

    @BeforeEach
    public void setUp() {
        tmc = new TiledMapCache();
    }

    @Test
    public void TiledMapCache_LoadsDefaultValues() {
        assertTrue(tmc.getMapUnitHeight() == 0 && tmc.getMapUnitWidth() == 0 &&
                tmc.getSpawnPoints().size() == 8);
    }
}
