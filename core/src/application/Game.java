package application;

import controller.Controller;
import model.*;
import view.View;
import com.badlogic.gdx.ApplicationAdapter;

/**
 * Runs the main loop of the playable game, in other words the ingame loop
 */
public class Game extends ApplicationAdapter {
	private Model model;
	private View view;
	private Controller controller;
	@Override
	public void create () {
		model = ModelFactory.makeModel("Flowerfield");
		view = new View(model);
		view.initialize();
		controller = new Controller(model);
	}

	@Override
	public void render () {
		controller.update(model.getPlayer());
		model.update();
		view.update();
		Time.getInstance().tick();
	}

	@Override
	public void dispose () {
		view.dispose();
	}
}
