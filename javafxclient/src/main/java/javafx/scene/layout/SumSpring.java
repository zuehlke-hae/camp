package javafx.scene.layout;

import javafx.scene.layout.Spring.CompoundSpring;

public class SumSpring extends CompoundSpring {
    public SumSpring(Spring s1, Spring s2) {
        super(s1, s2);
    }

    protected double op(double x, double y) {
        return x + y;
    }

    protected void setNonClearValue(double size) {
        super.setNonClearValue(size);
        s1.setStrain(this.getStrain());
        s2.setValue(size - s1.getValue());
    }
}