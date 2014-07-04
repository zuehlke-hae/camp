package zuehlke.food;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
import ch.zuehlke.camp.jpa.Food;

public class AddMealController {

	@FXML
	private BorderPane borderPane;
	
	@FXML
	private Label userId;
	@FXML
	private Button saveMenu;
	@FXML
	private ComboBox<Food> foodField;
	@FXML
	private Button searchNutriment;
	@FXML
	private TextField wennField;
	@FXML
	private ListView<AddMealViewModel> menuComposition;
	
	private WeakReference<ZenFoodApp> app;
	
	public AddMealController(ZenFoodApp zenFoodApp){
		initialize();
		app = new WeakReference<ZenFoodApp>(zenFoodApp);
	}
	
	
	private void initialize() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddMeal.fxml"));
	    fxmlLoader.setController(this);
//	    fxmlLoader.setLocation(location);
//	    fxmlLoader.setResources(resources);

	    try {
	        fxmlLoader.load();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    borderPane = fxmlLoader.getRoot();
	    foodField.setConverter(new StringConverter<Food>() {

			@Override
			public Food fromString(String string) {
				return null;
			}

			@Override
			public String toString(Food object) {
				return object == null ? null : object.getName();
			}
		});
	    
	    saveMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				Task<Void> callServiceTask = new Task<Void>(){

					@Override
					protected Void call() throws Exception {
						return null;
						//FIXME: call service
					}
					
				};
				
				callServiceTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

					@Override
					public void handle(WorkerStateEvent event) {
						app.get().navigateToMainScreen();
					}
					
				});
				
				//FIXME: Use an application Executor
				new Thread(callServiceTask).start();
			}
		});
	    searchNutriment.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				final String searchString = foodField.getPromptText();
				
				final Task<List<Food>> callServiceTask = new Task<List<Food>>(){

					@Override
					protected List<Food> call() throws Exception {
						return (List<Food>)app.get().getService().findFood(searchString);
					}
					
				};
				
				callServiceTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

					@Override
					public void handle(WorkerStateEvent event) {
						try {
							List<Food> foods = callServiceTask.get();
							foodField.itemsProperty().getValue().clear();
							foodField.itemsProperty().getValue().addAll(foods);
						} catch (InterruptedException | ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				});
				
				//FIXME: Use an application Executor
				new Thread(callServiceTask).start();
			}
		});
	}


	public BorderPane getView() {
		return borderPane;
	}
	
	public void initViewModel(AddMealViewModel viewModel){
		Bindings.bindBidirectional(wennField.textProperty(), viewModel.consumeDateProperty(), new StringConverter<Date>() {

			private DateFormat dateFormatter = new SimpleDateFormat();
			
			@Override
			public Date fromString(String textFieldValue) {
				try {
					return dateFormatter.parse(textFieldValue);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}

			@Override
			public String toString(Date date) {
				return dateFormatter.format(date);
			}
		});
	}
	
}
