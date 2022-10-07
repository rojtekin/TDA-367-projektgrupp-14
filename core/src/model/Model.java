package model;

import com.badlogic.gdx.maps.Map;
import model.enemies.*;
import com.dongbat.jbump.World;


import java.util.ArrayList;
import java.util.List;

public class Model {
    private IMapLoader mapLoader;
    private IPlayerCharacter player;
    private List<Entity> entityList = new ArrayList<>();
    private RewardSystem rewardSystem = new RewardSystem();
    private World<IEntity> world = new World<>();

    public IPlayerCharacter getPlayerCharacter() {
        return player;
    }

    public IPlayerCharacter getPlayer(){
        return player ;
    }

    public Map getMap() {
        return mapLoader.getMap();
    }

    public void update(){
        if (rewardSystem.levelUpChecker(player)){
            player = rewardSystem.applyReward(player); // casting question again
        }
    }

    public Direction getPlayerDirection() {
        return player.getDirection();
    }

    public boolean playerIsMoving() {
        return player.isMoving();
    }

    public void setPlayerMoving(boolean moving) {
        player.setMoving(moving);
    } //TODO player should set its moving status to moving in PlayerCharacter not in Model, make setMoving private after fixing this

    public World<Entity> getWorld() {
        return mapLoader.getWorld();
    }

    public ArrayList<Entity> getEntities(){
        return new ArrayList<>(entityList);
    }
    /**
     * Loads a specified map and creates a playercharacter
     * @param mapLoader object that loads a map of a specific type
     */
    public void initialize(IMapLoader mapLoader) {
        rewardSystem.initialize();
        this.mapLoader = mapLoader;
        player = new PlayerCharacter(mapLoader.getMapUnitWidth() / 2, mapLoader.getMapUnitHeight() / 2, mapLoader.getWorld());
        Mouse mouse1 = new Mouse(50,50,16,16,2,1,1, mapLoader.getWorld()); //temporary
        entityList.add(mouse1);
    }
}
