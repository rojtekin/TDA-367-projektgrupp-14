import com.dongbat.jbump.World;
import model.Model;
import model.monsters.Cyclops;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ModelTest {

    private Model model;

    @BeforeEach
    public void setUp() { model = new Model(); }

    @Test
    public void addEnemy_addsEnemyToEnemyList() {
        Cyclops cyclops = new Cyclops(100,100,1,1,1,new World<>());
        model.addEnemy(cyclops);
        assertTrue(model.getEnemyList().contains(cyclops));
    }
}
