package model;

import com.badlogic.gdx.maps.Map;
import model.enemies.*;
import com.dongbat.jbump.World;


import java.util.ArrayList;
import java.util.List;

public class Model {
    private IMapLoader mapLoader;
    private PlayerCharacter player;
    private List<Entity> entityList = new ArrayList<>();

    public PlayerCharacter getPlayerCharacter() {
        return player;
    }

    public PlayerCharacter getPlayer(){
        return player ;
    }

    public Map getMap() {
        return mapLoader.getMap();
    }

    public void update(){

    }

    public Direction getPlayerDirection() {
        return player.getDirection();
    }

    public boolean playerIsMoving() {
        return player.isMoving();
    }

    public void setPlayerMoving(boolean moving) {
        player.setMoving(moving);
    }

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
        this.mapLoader = mapLoader;
        player = new PlayerCharacter(mapLoader.getMapUnitWidth() / 2, mapLoader.getMapUnitHeight() / 2, mapLoader.getWorld());
        entityList.add(player);
        Mouse mouse1 = new Mouse(50,50,16,16,2,1,1, mapLoader.getWorld()); //temporary
        entityList.add(mouse1);
    }
}
