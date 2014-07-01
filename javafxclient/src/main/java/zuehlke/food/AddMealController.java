package zuehlke.food;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;

public class AddMealController {

	@FXML
	private BorderPane borderPane;
	
	@FXML
	private Label userId;
	@FXML
	private Button saveMenu;
	@FXML
	private TextField foodField;
	@FXML
	private TextField wennField;
	@FXML
	private ListView<AddMealViewModel> menuComposition;
	
	public AddMealController(){
		initialize();
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
	    
	    saveMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//FIXME: call service
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
