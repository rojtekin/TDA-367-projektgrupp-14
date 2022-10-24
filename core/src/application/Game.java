package application;

import controller.Controller;
import model.*;
import view.View;
import com.badlogic.gdx.ApplicationAdapter;

/**
 * Application that create and connects model, view and controller
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
		model.addObserver(view);
		controller = new Controller(model.getPlayer());
	}

	@Override
	public void render () {
		controller.update();
		model.update();
		view.update();
		Time.getInstance().tick();
	}

	@Override
	public void dispose () {
		view.dispose();
	}
}
