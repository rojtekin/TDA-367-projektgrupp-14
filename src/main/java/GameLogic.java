import Model.PlayerCharacter;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.PositionLockCamera;

public final class GameLogic {

    private GameLogic() {
    }

    public static void init() {
        // we'll use a camera in our game that is locked to the location of the player
        Camera camera = new PositionLockCamera(PlayerCharacter.instance());
        camera.setClampToMap(true);
        Game.world().setCamera(camera);

        // add default game logic for when a level was loaded
        Game.world().onLoaded(e -> {
            // spawn the player instance on the spawn point with the name "enter"
            Spawnpoint enter = e.getSpawnpoint("enter");
            if (enter != null) {
                enter.spawn(PlayerCharacter.instance());
            }
        });
    }
}
