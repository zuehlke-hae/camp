package javafx.scene.layout;

import javafx.scene.Node;
import javafx.scene.layout.Spring.AbstractSpring;

public class WidthSpring extends AbstractSpring {
    Node c;

   public WidthSpring(Node c) {
       this.c = c;
   }

   public double getMinimumValue() {
       return c.minWidth(computeContentBias(c));
   }

   public double getPreferredValue() {
       return c.prefWidth(computeContentBias(c));
   }

   public double getMaximumValue() {
       // We will be doing arithmetic with the results of this call,
       // so if a returned value is Integer.MAX_VALUE we will get
       // arithmetic overflow. Truncate such values.
       return Math.min(Short.MAX_VALUE, c.maxHeight(computeContentBias(c)));
   }
}
