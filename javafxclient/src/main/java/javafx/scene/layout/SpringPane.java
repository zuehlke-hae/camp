package javafx.scene.layout;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.geometry.Dimension2D;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import javax.swing.SpringLayout;

import com.sun.javafx.collections.TrackableObservableList;

public class SpringPane extends Pane {

	private Map<Node, SpringConstraints> componentConstraints = new HashMap<Node, SpringConstraints>();

	private Spring cyclicReference = Spring.constant(Spring.UNSET);
	private Set<Spring> cyclicSprings;
	private Set<Spring> acyclicSprings;

	private static class SpringProxy extends Spring {
		private String edgeName;
		private Node c;
		private SpringPane l;

		public SpringProxy(String edgeName, Node c, SpringPane l) {
			this.edgeName = edgeName;
			this.c = c;
			this.l = l;
		}

		private Spring getConstraint() {
			return l.getConstraints(c).getConstraint(edgeName);
		}

		public double getMinimumValue() {
			return getConstraint().getMinimumValue();
		}

		public double getPreferredValue() {
			return getConstraint().getPreferredValue();
		}

		public double getMaximumValue() {
			return getConstraint().getMaximumValue();
		}

		public double getValue() {
			return getConstraint().getValue();
		}

		public void setValue(double size) {
			getConstraint().setValue(size);
		}

		/* pp */boolean isCyclic(SpringPane l) {
			return l.isCyclic(getConstraint());
		}

		public String toString() {
			return "SpringProxy for " + edgeName + " edge of " + c.getId()
					+ ".";
		}
	}
	
	public SpringPane(){
		setParent(this);
	}

	private void resetCyclicStatuses() {
		cyclicSprings = new HashSet<Spring>();
		acyclicSprings = new HashSet<Spring>();
	}

	private void setParent(Region p) {
		resetCyclicStatuses();
		SpringConstraints pc = getConstraints(p);

		pc.setWest(Spring.constant(0));
		pc.setNorth(Spring.constant(0));
		// The applyDefaults() method automatically adds width and
		// height springs that delegate their calculations to the
		// getMinimumSize(), getPreferredSize() and getMaximumSize()
		// methods of the relevant component. In the case of the
		// parent this will cause an infinite loop since these
		// methods, in turn, delegate their calculations to the
		// layout manager. Check for this case and replace the
		// the springs that would cause this problem with a
		// constant springs that supply default values.
		Spring width = pc.getWidth();
		if (width instanceof WidthSpring
				&& ((WidthSpring) width).c == p) {
			pc.setWidth(Spring.constant(0, 0, Integer.MAX_VALUE));
		}
		Spring height = pc.getHeight();
		if (height instanceof HeightSpring
				&& ((HeightSpring) height).getNode() == p) {
			pc.setHeight(Spring.constant(0, 0, Integer.MAX_VALUE));
		}
	}

	boolean isCyclic(Spring s) {
		if (s == null) {
			return false;
		}
		if (cyclicSprings.contains(s)) {
			return true;
		}
		if (acyclicSprings.contains(s)) {
			return false;
		}
		cyclicSprings.add(s);
		boolean result = s.isCyclic(this);
		if (!result) {
			acyclicSprings.add(s);
			cyclicSprings.remove(s);
		} else {
			System.err.println(s + " is cyclic. ");
		}
		return result;
	}

	private Spring abandonCycles(Spring s) {
		return isCyclic(s) ? cyclicReference : s;
	}

	// LayoutManager methods.

	/**
	 * Has no effect, since this layout manager does not use a per-component
	 * string.
	 */
	private void addLayoutComponent(String name, Node c) {
	}

	/**
	 * Removes the constraints associated with the specified component.
	 * 
	 * @param c
	 *            the component being removed from the container
	 */
	private void removeLayoutComponent(Node c) {
		componentConstraints.remove(c);
	}

	private static Dimension2D addInsets(double width, double height, Region p) {
		Insets i = p.getInsets();
		return new Dimension2D(width + i.getLeft() + i.getRight(), height + i.getTop()
				+ i.getBottom());
	}

//	public Dimension2D minimumLayoutSize(Region parent) {
//		setParent(parent);
//		SpringConstraints pc = getConstraints(parent);
//		return addInsets(abandonCycles(pc.getWidth()).getMinimumValue(),
//				abandonCycles(pc.getHeight()).getMinimumValue(), parent);
//	}
//
//	public Dimension2D preferredLayoutSize(Region parent) {
//		setParent(parent);
//		SpringConstraints pc = getConstraints(parent);
//		return addInsets(abandonCycles(pc.getWidth()).getPreferredValue(),
//				abandonCycles(pc.getHeight()).getPreferredValue(), parent);
//	}

	// LayoutManager2 methods.

//	public Dimension2D maximumLayoutSize(Region parent) {
//		setParent(parent);
//		SpringConstraints pc = getConstraints(parent);
//		return addInsets(abandonCycles(pc.getWidth()).getMaximumValue(),
//				abandonCycles(pc.getHeight()).getMaximumValue(), parent);
//	}

	/**
	 * If <code>constraints</code> is an instance of
	 * <code>SpringLayout.Constraints</code>, associates the constraints with
	 * the specified component.
	 * <p>
	 * 
	 * @param component
	 *            the component being added
	 * @param constraints
	 *            the component's constraints
	 * 
	 * @see SpringLayout.Constraints
	 */
	private void addLayoutComponent(Node component, Object constraints) {
		if (constraints instanceof SpringConstraints) {
			putConstraints(component, (SpringConstraints) constraints);
		}
	}

	/**
	 * Returns 0.5f (centered).
	 */
	public float getLayoutAlignmentX(Region p) {
		return 0.5f;
	}

	/**
	 * Returns 0.5f (centered).
	 */
	public float getLayoutAlignmentY(Region p) {
		return 0.5f;
	}

	public void invalidateLayout(Region p) {
	}

	// End of LayoutManger2 methods

	/**
	 * Links edge <code>e1</code> of component <code>c1</code> to edge
	 * <code>e2</code> of component <code>c2</code>, with a fixed distance
	 * between the edges. This constraint will cause the assignment
	 * 
	 * <pre>
	 * value(e1, c1) = value(e2, c2) + pad
	 * </pre>
	 * 
	 * to take place during all subsequent layout operations.
	 * <p>
	 * 
	 * @param e1
	 *            the edge of the dependent
	 * @param c1
	 *            the component of the dependent
	 * @param pad
	 *            the fixed distance between dependent and anchor
	 * @param e2
	 *            the edge of the anchor
	 * @param c2
	 *            the component of the anchor
	 * 
	 * @see #putConstraint(String, Component, Spring, String, Component)
	 */
	private void putConstraint(String e1, Node c1, double pad, String e2, Node c2) {
		putConstraint(e1, c1, Spring.constant(pad), e2, c2);
	}

	/**
	 * Links edge <code>e1</code> of component <code>c1</code> to edge
	 * <code>e2</code> of component <code>c2</code>. As edge
	 * <code>(e2, c2)</code> changes value, edge <code>(e1, c1)</code> will be
	 * calculated by taking the (spring) sum of <code>(e2, c2)</code> and
	 * <code>s</code>. Each edge must have one of the following values:
	 * <code>SpringLayout.NORTH</code>, <code>SpringLayout.SOUTH</code>,
	 * <code>SpringLayout.EAST</code>, <code>SpringLayout.WEST</code>,
	 * <code>SpringLayout.VERTICAL_CENTER</code>,
	 * <code>SpringLayout.HORIZONTAL_CENTER</code> or
	 * <code>SpringLayout.BASELINE</code>.
	 * <p>
	 * 
	 * @param e1
	 *            the edge of the dependent
	 * @param c1
	 *            the component of the dependent
	 * @param s
	 *            the spring linking dependent and anchor
	 * @param e2
	 *            the edge of the anchor
	 * @param c2
	 *            the component of the anchor
	 * 
	 * @see #putConstraint(String, Component, int, String, Component)
	 * @see #NORTH
	 * @see #SOUTH
	 * @see #EAST
	 * @see #WEST
	 * @see #VERTICAL_CENTER
	 * @see #HORIZONTAL_CENTER
	 * @see #BASELINE
	 */
	private void putConstraint(String e1, Node c1, Spring s, String e2, Node c2) {
		putConstraint(e1, c1, Spring.sum(s, getConstraint(e2, c2)));
	}

	private void putConstraint(String e, Node c, Spring s) {
		if (s != null) {
			getConstraints(c).setConstraint(e, s);
		}
	}
	
	private void removeConstraints(Node node, SpringConstraints constraints) {
		if (node != null){
			componentConstraints.remove(node);
		}
	}

	private SpringConstraints applyDefaults(Node c, SpringConstraints constraints) {
		if (constraints == null) {
			constraints = new SpringConstraints();
		}
		if (constraints.getNode() == null) {
			constraints.setNode(c);
		}
		if (constraints.getHorizontalHistory().size() < 2) {
			applyDefaults(constraints, SpringConstraints.WEST, Spring.constant(0), SpringConstraints.WIDTH,
					Spring.width(c), constraints.getHorizontalHistory());
		}
		if (constraints.getVerticalHistory().size() < 2) {
			applyDefaults(constraints, SpringConstraints.NORTH, Spring.constant(0), SpringConstraints.HEIGHT,
					Spring.height(c), constraints.getVerticalHistory());
		}
		return constraints;
	}

	private void applyDefaults(SpringConstraints constraints, String name1,
			Spring spring1, String name2, Spring spring2, List<String> history) {
		if (history.size() == 0) {
			constraints.setConstraint(name1, spring1);
			constraints.setConstraint(name2, spring2);
		} else {
			// At this point there must be exactly one constraint defined
			// already.
			// Check width/height first.
			if (constraints.getConstraint(name2) == null) {
				constraints.setConstraint(name2, spring2);
			} else {
				// If width/height is already defined, install a default for
				// x/y.
				constraints.setConstraint(name1, spring1);
			}
			// Either way, leave the user's constraint topmost on the stack.
			Collections.rotate(history, 1);
		}
	}

	private void putConstraints(Node component, SpringConstraints constraints) {
		componentConstraints.put(component,
				applyDefaults(component, constraints));
	}

	/**
	 * Returns the constraints for the specified component. Note that, unlike
	 * the <code>GridBagLayout</code> <code>getConstraints</code> method, this
	 * method does not clone constraints. If no constraints have been associated
	 * with this component, this method returns a default constraints object
	 * positioned at 0,0 relative to the parent's Insets and its width/height
	 * constrained to the minimum, maximum, and preferred sizes of the
	 * component. The size characteristics are not frozen at the time this
	 * method is called; instead this method returns a constraints object whose
	 * characteristics track the characteristics of the component as they
	 * change.
	 * 
	 * @param c
	 *            the component whose constraints will be returned
	 * 
	 * @return the constraints for the specified component
	 */
	public SpringConstraints getConstraints(Node c) {
		SpringConstraints result = componentConstraints.get(c);
		if (result == null) {
			Object cp = c.getUserData();
			if (cp instanceof SpringConstraints) {
				return applyDefaults(c, (SpringConstraints) cp);
			}
			result = new SpringConstraints();
			putConstraints(c, result);
		}
		return result;
	}

	/**
	 * Returns the spring controlling the distance between the specified edge of
	 * the component and the top or left edge of its parent. This method,
	 * instead of returning the current binding for the edge, returns a proxy
	 * that tracks the characteristics of the edge even if the edge is
	 * subsequently rebound. Proxies are intended to be used in builder
	 * envonments where it is useful to allow the user to define the constraints
	 * for a layout in any order. Proxies do, however, provide the means to
	 * create cyclic dependencies amongst the constraints of a layout. Such
	 * cycles are detected internally by <code>SpringLayout</code> so that the
	 * layout operation always terminates.
	 * 
	 * @param edgeName
	 *            must be one of <code>SpringLayout.NORTH</code>,
	 *            <code>SpringLayout.SOUTH</code>,
	 *            <code>SpringLayout.EAST</code>, <code>SpringLayout.WEST</code>
	 *            , <code>SpringLayout.VERTICAL_CENTER</code>,
	 *            <code>SpringLayout.HORIZONTAL_CENTER</code> or
	 *            <code>SpringLayout.BASELINE</code>
	 * @param c
	 *            the component whose edge spring is desired
	 * 
	 * @return a proxy for the spring controlling the distance between the
	 *         specified edge and the top or left edge of its parent
	 * 
	 * @see #NORTH
	 * @see #SOUTH
	 * @see #EAST
	 * @see #WEST
	 * @see #VERTICAL_CENTER
	 * @see #HORIZONTAL_CENTER
	 * @see #BASELINE
	 */
	public Spring getConstraint(String edgeName, Node c) {
		// The interning here is unnecessary; it was added for efficiency.
		edgeName = edgeName.intern();
		return new SpringProxy(edgeName, c, this);
	}

	
	@Override
	public void layoutChildren() {
		//minimumLayoutSize(this);
		final List<Node> children = getManagedChildren();
		int n = children.size();
		//getConstraints(this).reset();
		//for (int i = 0; i < n; i++) {
		//	getConstraints(children.get(i)).reset();
		//}

		Insets insets = this.getInsets();
		SpringConstraints pc = getConstraints(this);
		abandonCycles(pc.getWest()).setValue(0);
		abandonCycles(pc.getNorth()).setValue(0);
		abandonCycles(pc.getWidth()).setValue(
				this.getWidth() - insets.getLeft() - insets.getRight());
		abandonCycles(pc.getHeight()).setValue(
				this.getHeight() - insets.getTop() - insets.getBottom());

		for (int i = 0; i < n; i++) {
			Node c = children.get(i);
			SpringConstraints cc = getConstraints(c);
			double west = abandonCycles(cc.getWest()).getValue();
			double north = abandonCycles(cc.getNorth()).getValue();
			double width = abandonCycles(cc.getWidth()).getValue();
			double height = abandonCycles(cc.getHeight()).getValue();
			//c.setBounds(insets.getLeft() + x, insets.getTop() + y, width, height);
			 layoutInArea(c, west, north, width, height, c.getBaselineOffset(),
					 insets,  HPos.LEFT, VPos.BASELINE);
		}
	}
	
	private final ObservableList<SpringConstraints> springConstraints = new TrackableObservableList<SpringConstraints>() {
		@Override
		protected void onChanged(Change<SpringConstraints> c) {
			while(c.next()) {
				 for (SpringConstraints constraints : c.getRemoved()) {
					 if (constraints != null && !springConstraints.contains(constraints)) {
						 removeConstraints(constraints.getNode(), constraints);
						 constraints.remove(SpringPane.this);
					 }
				 }
				 
				 for (SpringConstraints constraints : c.getAddedSubList()) {
					 if (constraints != null) {
						 constraints.add(SpringPane.this);
						 putConstraints(constraints.getNode(), constraints);
					 }
				 }
			}
			requestLayout();
		}	
	};

	public final ObservableList<SpringConstraints>  getSpringConstraints() { return springConstraints; }

	
}
