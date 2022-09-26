package Model;

import Model.Enemies.*;

import java.util.ArrayList;

public class Model implements IModel {
    private PlayerCharacter player;
    private ArrayList<Entity> entityList = new ArrayList<>();

    public void initialize() {
        player = new PlayerCharacter();
        entityList.add(player);
        Mouse mouse1 = new Mouse(50,50,16,16,2,1,1);
        entityList.add(mouse1);
    }
    public ArrayList<Entity> getEntities(){
        return new ArrayList<Entity>(entityList);
    }
    public PlayerCharacter getPlayer(){
        return player ;
    }
    public void update(){

    }
}
