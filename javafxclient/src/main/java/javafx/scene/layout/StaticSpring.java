package javafx.scene.layout;

import javafx.scene.layout.Spring.AbstractSpring;

public class StaticSpring extends AbstractSpring {
    protected double min;
    protected double pref;
    protected double max;

    public StaticSpring(){
    	super();
    }
    
    public StaticSpring(double min, double pref, double max){
        setMinimumValue(min);
        setPreferredValue(pref);
        setMaximumValue(max);
    }
    
    public StaticSpring(double pref) {
        this(pref, pref, pref);
    }
    
    public void setPreferredValue(double pref){
        this.pref = pref;
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

	public void setMinimumValue(double min) {
		this.min = min; 
	}
	
	public void setMaximumValue(double max) {
		this.max = max; 
	}
}
