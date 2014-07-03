package zuehlke.food;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ZenFoodApp extends Application {
	private static final String OS_NAME = System.getProperty(
			"ensemble.os.name", System.getProperty("os.name"));
	private static final String OS_ARCH = System.getProperty(
			"ensemble.os.arch", System.getProperty("os.arch"));
	public static final boolean IS_IPHONE = false;
	public static final boolean IS_IOS = "iOS".equals(OS_NAME);
	public static final boolean IS_EMBEDDED = "arm".equals(OS_ARCH) && !IS_IOS;
	public static final boolean IS_DESKTOP = !IS_EMBEDDED && !IS_IOS;
	public static final boolean IS_MAC = OS_NAME.startsWith("Mac");

	private Scene scene;
	private AddMealController addMenuController;
	private UserIdentificationController userIdentificationController;
	private MainScreenController mainScreenController;

	static {
		System.out.println("IS_IPHONE = " + IS_IPHONE);
		System.out.println("IS_MAC = " + IS_MAC);
		System.out.println("IS_IOS = " + IS_IOS);
		System.out.println("IS_EMBEDDED = " + IS_EMBEDDED);
		System.out.println("IS_DESKTOP = " + IS_DESKTOP);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() throws Exception {
		addMenuController = new AddMealController(this);
		userIdentificationController = new UserIdentificationController(this);
		mainScreenController = new MainScreenController(this);
	};

	@Override
	public void start(final Stage stage) throws Exception {
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		// CREATE SCENE
		scene = new Scene(mainScreenController.getView(), 1024, 768, Color.WHITE);
		stage.setScene(scene);
		// START FULL SCREEN IF WANTED
		if (PlatformFeatures.START_FULL_SCREEN) {
			stage.setX(primaryScreenBounds.getMinX());
			stage.setY(primaryScreenBounds.getMinY());
			stage.setWidth(primaryScreenBounds.getWidth());
			stage.setHeight(primaryScreenBounds.getHeight());
		}
		stage.setTitle("ZenFoodApp");
		stage.show();
	}
	
	void navigateToAddMeal(){
		scene.setRoot(addMenuController.getView());
	}
	
	void navigateToMainScreen(){
		scene.setRoot(mainScreenController.getView());
	}
	
	void logout() {
		scene.setRoot(userIdentificationController.getView());
	}
}