package javafx.scene.layout;

import javafx.scene.layout.Spring.AbstractSpring;

public class StaticSpring extends AbstractSpring {
    protected double min;
    protected double pref;
    protected double max;

    public StaticSpring(){
    	super();
    }
    
    public StaticSpring(double pref) {
        this(pref, pref, pref);
    }

    public StaticSpring(double min, double pref, double max) {
        this.min = min;
        this.pref = pref;
        this.max = max;
    }
    
    public void setPreferredValue(double pref){
    	this.min = pref;
        this.pref = pref;
        this.max = pref;
    }

     public String toString() {
         return "StaticSpring [" + min + ", " + pref + ", " + max + "]";
     }

     public double getMinimumValue() {
        return min;
    }

    public double getPreferredValue() {
        return pref;
    }

    public double getMaximumValue() {
        return max;
    }
}
