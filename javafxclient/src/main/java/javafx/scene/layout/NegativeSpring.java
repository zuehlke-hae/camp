package javafx.scene.layout;

public class NegativeSpring extends Spring {
    private Spring s;

    public NegativeSpring(Spring s) {
        this.s = s;
    }

//Note the use of max value rather than minimum value here.
//See the opening preamble on arithmetic with springs.

    public double getMinimumValue() {
        return -s.getMaximumValue();
    }

    public double getPreferredValue() {
        return -s.getPreferredValue();
    }

    public double getMaximumValue() {
        return -s.getMinimumValue();
    }

    public double getValue() {
        return -s.getValue();
    }

    public void setValue(double size) {
        // No need to check for UNSET as
        // Integer.MIN_VALUE == -Integer.MIN_VALUE.
        s.setValue(-size);
    }

    /*pp*/ boolean isCyclic(SpringPane l) {
        return s.isCyclic(l);
    }
}
