package Application;

import Model.PlayerCharacter;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.PositionLockCamera;

public final class GameLogic {

    private GameLogic() {
    }

    public static void init() {
        Camera camera = new PositionLockCamera(PlayerCharacter.instance());
        camera.setClampToMap(true);
        Game.world().setCamera(camera);

        Game.world().setGravity(0);
        Game.world().onLoaded( e -> {
            Spawnpoint init_spawn = e.getSpawnpoint("init_spawn");
            if (init_spawn != null) {
                init_spawn.spawn(PlayerCharacter.instance());
            }
        });
    }
}
