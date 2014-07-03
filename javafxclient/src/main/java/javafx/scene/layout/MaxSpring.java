package javafx.scene.layout;

import javafx.scene.layout.Spring.CompoundSpring;

public class MaxSpring extends CompoundSpring {

    public MaxSpring(Spring s1, Spring s2) {
        super(s1, s2);
    }

    protected double op(double x, double y) {
        return Math.max(x, y);
    }

    protected void setNonClearValue(double size) {
        super.setNonClearValue(size);
        s1.setValue(size);
        s2.setValue(size);
    }
}
