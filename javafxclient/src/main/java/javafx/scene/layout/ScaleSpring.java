package javafx.scene.layout;

public class ScaleSpring extends Spring {
    private Spring s;
    private float factor;

    public ScaleSpring(Spring s, float factor) {
        this.s = s;
        this.factor = factor;
    }

    public double getMinimumValue() {
        return Math.round((factor < 0 ? s.getMaximumValue() : s.getMinimumValue()) * factor);
    }

    public double getPreferredValue() {
        return Math.round(s.getPreferredValue() * factor);
    }

    public double getMaximumValue() {
        return Math.round((factor < 0 ? s.getMinimumValue() : s.getMaximumValue()) * factor);
    }

    public double getValue() {
        return Math.round(s.getValue() * factor);
    }

    public void setValue(double value) {
        if (value == UNSET) {
            s.setValue(UNSET);
        } else {
            s.setValue(Math.round(value / factor));
        }
    }

    /*pp*/ boolean isCyclic(SpringPane l) {
        return s.isCyclic(l);
    }
}
