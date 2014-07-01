package zuehlke.food;

import java.util.Date;

import javafx.beans.property.ListProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class AddMealViewModel {
	
	public class MealMadeOfViewModel {
		private Property<Double> quantity = new SimpleObjectProperty<Double>();
		private Property<String> estimationUnit = new SimpleStringProperty();
		
		public Property<Double> quantityProperty(){
			return quantity;
		}
		
		public Property<String> estimationUnitProperty(){
			return estimationUnit;
		}
	}

	private Property<Date> consumeDate = new SimpleObjectProperty<>();
	private ListProperty<MealMadeOfViewModel> madeOf = new SimpleListProperty<>();

	public Property<Date> consumeDateProperty() {
		return consumeDate;
	}
	
	public ListProperty<MealMadeOfViewModel> madeOfProperty(){
		return madeOf;
	}
	
}
