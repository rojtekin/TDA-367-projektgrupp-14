package Model;

public class PlayerCharacter extends Entity implements IControllable {


    public PlayerCharacter(int spawnX, int spawnY, IModel model) {
        super(spawnX, spawnY, 32, 32, 5, 10, model);
        addCollision();
    }
}
