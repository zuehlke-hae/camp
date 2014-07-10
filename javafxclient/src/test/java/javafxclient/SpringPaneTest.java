package javafxclient;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.scene.control.Label;
import javafx.scene.layout.SpringConstraints;
import javafx.scene.layout.SpringPane;
import javafx.scene.layout.StaticSpring;

public class SpringPaneTest {
	
	private SpringPane pane = new SpringPane();
	private Label testLabel = new Label("test");

	@Test
	public void testConstraint(){
		pane.getChildren().add(testLabel);
		SpringConstraints sc = new SpringConstraints();
		sc.setNode(testLabel);
		sc.setWest(new StaticSpring(10));
		sc.setNorth(new StaticSpring(10));
		pane.getSpringConstraints().add(sc);
		
		pane.layoutChildren();
		
		assertEquals(testLabel.getLayoutX(), 10.0, 0.0);
		assertEquals(testLabel.getLayoutY(), 10.0, 0.0);
		
	}
	
}
