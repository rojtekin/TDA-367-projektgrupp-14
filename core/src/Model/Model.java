package Model;

import Model.Enemies.Mouse;

import java.util.ArrayList;

public class Model implements IModel {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;
    private ArrayList<Entity> entityList = new ArrayList<>();

    public void initialize() {
        PlayerCharacter.instance();
        entityList.add(PlayerCharacter.instance());
        Mouse mouse1 = new Mouse(50,50,16,16,2,1,1);
        entityList.add(mouse1);
    }
    public ArrayList<Entity> getEntities(){
        return this.entityList;
    }
    public void update(){
    }
}
