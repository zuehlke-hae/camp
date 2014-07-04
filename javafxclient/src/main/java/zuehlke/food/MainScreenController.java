package zuehlke.food;

import java.lang.ref.WeakReference;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class MainScreenController {
	
	@FXML
	private BorderPane borderPane;

	@FXML
	private Button addMeal;
	
	@FXML
	private TableView<ConsumedNutrimentViewModel> consumedNutrimentDashboard;
	
	private WeakReference<ZenFoodApp> app;
	
	public MainScreenController(ZenFoodApp zenFoodApp){
		initialize();
		app = new WeakReference<ZenFoodApp>(zenFoodApp);
	}
	
	
	private void initialize() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
	    fxmlLoader.setController(this);

	    try {
	        fxmlLoader.load();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    borderPane = fxmlLoader.getRoot();
	    
	    addMeal.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				app.get().navigateToAddMeal();
			}
		});
	}


	public BorderPane getView() {
		return borderPane;
	}
	
	public void initViewModel(){
		
		ConsumedNutrimentViewModel consumedNutrimentViewModel = new ConsumedNutrimentViewModel();
		consumedNutrimentDashboard.getItems().add(consumedNutrimentViewModel);
	}
	
}
