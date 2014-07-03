package zuehlke.food;

import java.lang.ref.WeakReference;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.SpringPane;

public class UserIdentificationController {

	@FXML
	private SpringPane springPane;
	
	@FXML
	private Button identify;
	private WeakReference<ZenFoodApp> app;
	
	public UserIdentificationController(ZenFoodApp zenFoodApp){
		initialize();
		app = new WeakReference<ZenFoodApp>(zenFoodApp);
	}
	
	private void initialize() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserIdentification.fxml"));
	    fxmlLoader.setController(this);

	    try {
	        fxmlLoader.load();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    springPane = fxmlLoader.getRoot();
	    
	    identify.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				app.get().navigateToMainScreen();
			}
		});
	}


	public SpringPane getView() {
		return springPane;
	}
}
