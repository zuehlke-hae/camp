package javafx.scene.layout;

import javafx.scene.Node;
import javafx.scene.layout.Spring.AbstractSpring;

public class HeightSpring extends AbstractSpring {
	private Node c;

    public HeightSpring(Node c) {
        this.c = c;
    }
    
    public Node getNode(){
    	return c;
    }

    public double getMinimumValue() {
        return c.minHeight(computeContentBias(c));
    }

    public double getPreferredValue() {
        return c.prefHeight(computeContentBias(c));
    }

    public double getMaximumValue() {
        return Math.min(Short.MAX_VALUE, c.maxHeight(computeContentBias(c)));
    }

}
