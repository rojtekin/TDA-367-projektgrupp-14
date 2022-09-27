package Model;

import Model.Enemies.Enemy;
import Model.Enemies.Mouse;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;

import java.util.ArrayList;

public class Model implements IModel {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;
    private PlayerCharacter player;
    private ArrayList<Enemy> enemyList = new ArrayList<>();
    private World<Entity> world = new World<>();

    public PlayerCharacter getPlayer() {
        return player;
    }

    //World contains the collisionboxes from JBump
    public World<Entity> getWorld() {
        return world;
    }

    public void initialize() {
        player = new PlayerCharacter(world);
    }

    public ArrayList<Enemy> getEnemies(){
        return this.enemyList;
    }

    public void updateEnemyList() {
        for (Entity e : enemyList) {
            world.add(new Item<Entity>(e), e.getX(), e.getY(), e.getWidth(), e.getHeight());
        }
    }
}
