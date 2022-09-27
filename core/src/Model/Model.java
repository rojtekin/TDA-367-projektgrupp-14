package Model;

import Model.Enemies.*;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Model implements IModel {
    private PlayerCharacter player;
    private ArrayList<Entity> entityList = new ArrayList<>();

    public void initialize() {
        player = new PlayerCharacter();
        entityList.add(player);
        Mouse mouse1 = new Mouse(50,50,16,16,2,1,1); //temporary
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
