package javafx.scene.layout;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.ConstraintsBase;

/**
 * A <code>Constraints</code> object holds the constraints that govern the
 * way a component's size and position change in a container controlled by a
 * <code>SpringLayout</code>. A <code>Constraints</code> object is like a
 * <code>Rectangle</code>, in that it has <code>x</code>, <code>y</code>,
 * <code>width</code>, and <code>height</code> properties. In the
 * <code>Constraints</code> object, however, these properties have
 * <code>Spring</code> values instead of integers. In addition, a
 * <code>Constraints</code> object can be manipulated as four edges --
 * north, south, east, and west -- using the <code>constraint</code>
 * property.
 * 
 * <p>
 * The following formulas are always true for a <code>Constraints</code>
 * object (here WEST and <code>x</code> are synonyms, as are and NORTH and
 * <code>y</code>):
 * 
 * <pre>
 *               EAST = WEST + WIDTH
 *              SOUTH = NORTH + HEIGHT
 *  HORIZONTAL_CENTER = WEST + WIDTH/2
 *    VERTICAL_CENTER = NORTH + HEIGHT/2
 *  ABSOLUTE_BASELINE = NORTH + RELATIVE_BASELINE*
 * </pre>
 * <p>
 * For example, if you have specified the WIDTH and WEST (X) location the
 * EAST is calculated as WEST + WIDTH. If you instead specified the WIDTH
 * and EAST locations the WEST (X) location is then calculated as EAST -
 * WIDTH.
 * <p>
 * [RELATIVE_BASELINE is a private constraint that is set automatically when
 * the SpringLayout.Constraints(Component) constuctor is called or when a
 * constraints object is registered with a SpringLayout object.]
 * <p>
 * <b>Note</b>: In this document, operators represent methods in the
 * <code>Spring</code> class. For example, "a + b" is equal to
 * <code>Spring.sum(a, b)</code>, and "a - b" is equal to
 * <code>Spring.sum(a, Spring.minus(b))</code>. See the {@link Spring
 * <code>Spring</code> API documentation} for further details of spring
 * arithmetic.
 * 
 * <p>
 * 
 * Because a <code>Constraints</code> object's properties -- representing
 * its edges, size, and location -- can all be set independently and yet are
 * interrelated, a <code>Constraints</code> object can become
 * <em>over-constrained</em>. For example, if the <code>WEST</code>,
 * <code>WIDTH</code> and <code>EAST</code> edges are all set, steps must be
 * taken to ensure that the first of the formulas above holds. To do this,
 * the <code>Constraints</code> object throws away the
 * <em>least recently set</em> constraint so as to make the formulas hold.
 * 
 * @since 1.4
 */
public class SpringConstraints extends ConstraintsBase {
	
	/**
	 * Specifies the top edge of a component's bounding rectangle.
	 */
	public static final String NORTH = "North";

	/**
	 * Specifies the bottom edge of a component's bounding rectangle.
	 */
	public static final String SOUTH = "South";

	/**
	 * Specifies the right edge of a component's bounding rectangle.
	 */
	public static final String EAST = "East";

	/**
	 * Specifies the left edge of a component's bounding rectangle.
	 */
	public static final String WEST = "West";

	/**
	 * Specifies the horizontal center of a component's bounding rectangle.
	 * 
	 * @since 1.6
	 */
	public static final String HORIZONTAL_CENTER = "HorizontalCenter";

	/**
	 * Specifies the vertical center of a component's bounding rectangle.
	 * 
	 * @since 1.6
	 */
	public static final String VERTICAL_CENTER = "VerticalCenter";

	/**
	 * Specifies the baseline of a component.
	 * 
	 * @since 1.6
	 */
	public static final String BASELINE = "Baseline";

	/**
	 * Specifies the width of a component's bounding rectangle.
	 * 
	 * @since 1.6
	 */
	public static final String WIDTH = "Width";

	/**
	 * Specifies the height of a component's bounding rectangle.
	 * 
	 * @since 1.6
	 */
	public static final String HEIGHT = "Height";

	private static String[] ALL_HORIZONTAL = { WEST, WIDTH, EAST,
			HORIZONTAL_CENTER };

	private static String[] ALL_VERTICAL = { NORTH, HEIGHT, SOUTH,
			VERTICAL_CENTER, BASELINE };
	
	private Spring west;
	private Spring north;
	private Spring east;
	private Spring south;
	private Spring width;
	private Spring height;
	private Spring horizontalCenter;
	private Spring verticalCenter;
	private Spring baseline;

	private List<String> horizontalHistory = new ArrayList<String>(2);
	private List<String> verticalHistory = new ArrayList<String>(2);

	// Used for baseline calculations
	private Node c;

	/**
	 * Creates an empty <code>Constraints</code> object.
	 */
	public SpringConstraints() {
		super();
	}

	/**
	 * Creates a <code>Constraints</code> object with the specified values
	 * for its <code>x</code> and <code>y</code> properties. The
	 * <code>height</code> and <code>width</code> springs have
	 * <code>null</code> values.
	 * 
	 * @param x
	 *            the spring controlling the component's <em>x</em> value
	 * @param y
	 *            the spring controlling the component's <em>y</em> value
	 */
	public SpringConstraints(Spring x, Spring y) {
		super();
		setWest(x);
		setNorth(y);
	}

	/**
	 * Creates a <code>Constraints</code> object with the specified values
	 * for its <code>x</code>, <code>y</code>, <code>width</code>, and
	 * <code>height</code> properties. Note: If the
	 * <code>SpringLayout</code> class encounters <code>null</code> values
	 * in the <code>Constraints</code> object of a given component, it
	 * replaces them with suitable defaults.
	 * 
	 * @param x
	 *            the spring value for the <code>x</code> property
	 * @param y
	 *            the spring value for the <code>y</code> property
	 * @param width
	 *            the spring value for the <code>width</code> property
	 * @param height
	 *            the spring value for the <code>height</code> property
	 */
	public SpringConstraints(Spring x, Spring y, Spring width, Spring height) {
		super();
		setWest(x);
		setNorth(y);
		setWidth(width);
		setHeight(height);
	}

	/**
	 * Creates a <code>Constraints</code> object with suitable
	 * <code>x</code>, <code>y</code>, <code>width</code> and
	 * <code>height</code> springs for component, <code>c</code>. The
	 * <code>x</code> and <code>y</code> springs are constant springs
	 * initialised with the component's location at the time this method is
	 * called. The <code>width</code> and <code>height</code> springs are
	 * special springs, created by the <code>Spring.width()</code> and
	 * <code>Spring.height()</code> methods, which track the size
	 * characteristics of the component when they change.
	 * 
	 * @param c
	 *            the component whose characteristics will be reflected by
	 *            this Constraints object
	 * @throws NullPointerException
	 *             if <code>c</code> is null.
	 * @since 1.5
	 */
	public SpringConstraints(Node c) {
		super();
		this.c = c;
		setWest(Spring.constant(c.getLayoutX()));
		setNorth(Spring.constant(c.getLayoutY()));
		setWidth(Spring.width(c));
		setHeight(Spring.height(c));
	}
	
	public Node getNode(){
		return c;
	}
	
	public void setNode(Node node) {
		c = node;
	}
	
	public List<String> getHorizontalHistory() {
		return horizontalHistory;
	}

	public List<String> getVerticalHistory() {
		return verticalHistory;
	}

	private void pushConstraint(String name, Spring value,
			boolean horizontal) {
		boolean valid = true;
		List<String> history = horizontal ? horizontalHistory
				: verticalHistory;
		if (history.contains(name)) {
			history.remove(name);
			valid = false;
		} else if (history.size() == 2 && value != null) {
			history.remove(0);
			valid = false;
		}
		if (value != null) {
			history.add(name);
		}
		if (!valid) {
			String[] all = horizontal ? ALL_HORIZONTAL : ALL_VERTICAL;
			for (String s : all) {
				if (!history.contains(s)) {
					setConstraint(s, null);
				}
			}
		}
	}

	private Spring sum(Spring s1, Spring s2) {
		return (s1 == null || s2 == null) ? null : Spring.sum(s1, s2);
	}

	private Spring difference(Spring s1, Spring s2) {
		return (s1 == null || s2 == null) ? null : Spring
				.difference(s1, s2);
	}

	private Spring scale(Spring s, float factor) {
		return (s == null) ? null : Spring.scale(s, factor);
	}

	private double getBaselineFromHeight(double height) {
//		if (height < 0) {
//			// Bad Scott, Bad Scott!
//			return -c.getBaselineOffset();//.getBaseline(c.getPrefWidth(), -height);
//		}
		return c.getBaselineOffset();//c.getBaseline(c.getPrefWidth(), height);
	}

	private double getHeightFromBaseLine(double baseline) {
		double prefHeight = c.prefHeight(Spring.computeContentBias(c));
		double prefBaseline = c.getBaselineOffset();//.getBaseline(c.prefWidth(Spring.computeContentBias(c)), prefHeight);
		if (prefBaseline == baseline) {
			// If prefBaseline < 0, then no baseline, assume preferred
			// height.
			// If prefBaseline == baseline, then specified baseline
			// matches preferred baseline, return preferred height
			return prefHeight;
		}
		// Valid baseline
		/*switch (c.getBaselineResizeBehavior()) {
		case CONSTANT_DESCENT:
			return prefHeight + (baseline - prefBaseline);
		case CENTER_OFFSET:
			return prefHeight + 2 * (baseline - prefBaseline);
		case CONSTANT_ASCENT:
			// Component baseline and specified baseline will NEVER
			// match, fall through to default
		default: // OTHER
			// No way to map from baseline to height.
		}*/
		//return Double.MIN_VALUE;
		return prefHeight + (baseline - prefBaseline);
	}

	private Spring heightToRelativeBaseline(Spring s) {
		return new Spring.SpringMap(s) {
			protected double map(double i) {
				return getBaselineFromHeight(i);
			}

			protected double inv(double i) {
				return getHeightFromBaseLine(i);
			}
		};
	}

	private Spring relativeBaselineToHeight(Spring s) {
		return new Spring.SpringMap(s) {
			protected double map(double i) {
				return getHeightFromBaseLine(i);
			}

			protected double inv(double i) {
				return getBaselineFromHeight(i);
			}
		};
	}

	private boolean defined(List<String> history, String s1, String s2) {
		return history.contains(s1) && history.contains(s2);
	}

	/**
	 * Sets the <code>x</code> property, which controls the <code>x</code>
	 * value of a component's location.
	 * 
	 * @param x
	 *            the spring controlling the <code>x</code> value of a
	 *            component's location
	 * 
	 * @see #getX
	 * @see SpringConstraints.Constraints
	 */
	public void setWest(Spring x) {
		this.west = x;
		pushConstraint(WEST, x, true);
	}

	/**
	 * Returns the value of the <code>x</code> property.
	 * 
	 * @return the spring controlling the <code>x</code> value of a
	 *         component's location
	 * 
	 * @see #setX
	 * @see SpringConstraints.Constraints
	 */
	public Spring getWest() {
		if (west == null) {
			if (defined(horizontalHistory, EAST, WIDTH)) {
				west = difference(east, width);
			} else if (defined(horizontalHistory, HORIZONTAL_CENTER, WIDTH)) {
				west = difference(horizontalCenter, scale(width, 0.5f));
			} else if (defined(horizontalHistory, HORIZONTAL_CENTER, EAST)) {
				west = difference(scale(horizontalCenter, 2f), east);
			}
		}
		return west;
	}

	/**
	 * Sets the <code>y</code> property, which controls the <code>y</code>
	 * value of a component's location.
	 * 
	 * @param y
	 *            the spring controlling the <code>y</code> value of a
	 *            component's location
	 * 
	 * @see #getY
	 * @see SpringConstraints.Constraints
	 */
	public void setNorth(Spring north) {
		this.north = north;
		pushConstraint(NORTH, north, false);
	}

	/**
	 * Returns the value of the <code>y</code> property.
	 * 
	 * @return the spring controlling the <code>y</code> value of a
	 *         component's location
	 * 
	 * @see #setY
	 * @see SpringConstraints.Constraints
	 */
	public Spring getNorth() {
		if (north == null) {
			if (defined(verticalHistory, SOUTH, HEIGHT)) {
				north = difference(south, height);
			} else if (defined(verticalHistory, VERTICAL_CENTER, HEIGHT)) {
				north = difference(verticalCenter, scale(height, 0.5f));
			} else if (defined(verticalHistory, VERTICAL_CENTER, SOUTH)) {
				north = difference(scale(verticalCenter, 2f), south);
			} else if (defined(verticalHistory, BASELINE, HEIGHT)) {
				north = difference(baseline, heightToRelativeBaseline(height));
			} else if (defined(verticalHistory, BASELINE, SOUTH)) {
				north = scale(
						difference(baseline,
								heightToRelativeBaseline(south)), 2f);
				/*
				 * } else if (defined(verticalHistory, BASELINE,
				 * VERTICAL_CENTER)) { y = scale(difference(baseline,
				 * heightToRelativeBaseline(scale(verticalCenter, 2))),
				 * 1f/(1-2*0.5f));
				 */
			}
		}
		return north;
	}

	/**
	 * Sets the <code>width</code> property, which controls the width of a
	 * component.
	 * 
	 * @param width
	 *            the spring controlling the width of this
	 *            <code>Constraints</code> object
	 * 
	 * @see #getWidth
	 * @see SpringConstraints.Constraints
	 */
	public void setWidth(Spring width) {
		this.width = width;
		pushConstraint(WIDTH, width, true);
	}

	/**
	 * Returns the value of the <code>width</code> property.
	 * 
	 * @return the spring controlling the width of a component
	 * 
	 * @see #setWidth
	 * @see SpringConstraints.Constraints
	 */
	public Spring getWidth() {
		if (width == null) {
			if (horizontalHistory.contains(EAST)) {
				width = difference(east, getWest());
			} else if (horizontalHistory.contains(HORIZONTAL_CENTER)) {
				width = scale(difference(horizontalCenter, getWest()), 2f);
			}
		}
		return width;
	}

	/**
	 * Sets the <code>height</code> property, which controls the height of a
	 * component.
	 * 
	 * @param height
	 *            the spring controlling the height of this
	 *            <code>Constraints</code> object
	 * 
	 * @see #getHeight
	 * @see SpringConstraints.Constraints
	 */
	public void setHeight(Spring height) {
		this.height = height;
		pushConstraint(HEIGHT, height, false);
	}

	/**
	 * Returns the value of the <code>height</code> property.
	 * 
	 * @return the spring controlling the height of a component
	 * 
	 * @see #setHeight
	 * @see SpringConstraints.Constraints
	 */
	public Spring getHeight() {
		if (height == null) {
			if (verticalHistory.contains(SOUTH)) {
				height = difference(south, getNorth());
			} else if (verticalHistory.contains(VERTICAL_CENTER)) {
				height = scale(difference(verticalCenter, getNorth()), 2f);
			} else if (verticalHistory.contains(BASELINE)) {
				height = relativeBaselineToHeight(difference(baseline,
						getNorth()));
			}
		}
		return height;
	}

	private void setEast(Spring east) {
		this.east = east;
		pushConstraint(EAST, east, true);
	}

	private Spring getEast() {
		if (east == null) {
			east = sum(getWest(), getWidth());
		}
		return east;
	}

	private void setSouth(Spring south) {
		this.south = south;
		pushConstraint(SOUTH, south, false);
	}

	private Spring getSouth() {
		if (south == null) {
			south = sum(getNorth(), getHeight());
		}
		return south;
	}

	private Spring getHorizontalCenter() {
		if (horizontalCenter == null) {
			horizontalCenter = sum(getWest(), scale(getWidth(), 0.5f));
		}
		return horizontalCenter;
	}

	private void setHorizontalCenter(Spring horizontalCenter) {
		this.horizontalCenter = horizontalCenter;
		pushConstraint(HORIZONTAL_CENTER, horizontalCenter, true);
	}

	private Spring getVerticalCenter() {
		if (verticalCenter == null) {
			verticalCenter = sum(getNorth(), scale(getHeight(), 0.5f));
		}
		return verticalCenter;
	}

	private void setVerticalCenter(Spring verticalCenter) {
		this.verticalCenter = verticalCenter;
		pushConstraint(VERTICAL_CENTER, verticalCenter, false);
	}

	private Spring getBaseline() {
		if (baseline == null) {
			baseline = sum(getNorth(), heightToRelativeBaseline(getHeight()));
		}
		return baseline;
	}

	private void setBaseline(Spring baseline) {
		this.baseline = baseline;
		pushConstraint(BASELINE, baseline, false);
	}

	/**
	 * Sets the spring controlling the specified edge. The edge must have
	 * one of the following values: <code>SpringLayout.NORTH</code>,
	 * <code>SpringLayout.SOUTH</code>, <code>SpringLayout.EAST</code>,
	 * <code>SpringLayout.WEST</code>,
	 * <code>SpringLayout.HORIZONTAL_CENTER</code>,
	 * <code>SpringLayout.VERTICAL_CENTER</code>,
	 * <code>SpringLayout.BASELINE</code>, <code>SpringLayout.WIDTH</code>
	 * or <code>SpringLayout.HEIGHT</code>. For any other
	 * <code>String</code> value passed as the edge, no action is taken. For
	 * a <code>null</code> edge, a <code>NullPointerException</code> is
	 * thrown.
	 * <p/>
	 * <b>Note:</b> This method can affect {@code x} and {@code y} values
	 * previously set for this {@code Constraints}.
	 * 
	 * @param edgeName
	 *            the edge to be set
	 * @param s
	 *            the spring controlling the specified edge
	 * 
	 * @throws NullPointerException
	 *             if <code>edgeName</code> is <code>null</code>
	 * 
	 * @see #getConstraint
	 * @see #NORTH
	 * @see #SOUTH
	 * @see #EAST
	 * @see #WEST
	 * @see #HORIZONTAL_CENTER
	 * @see #VERTICAL_CENTER
	 * @see #BASELINE
	 * @see #WIDTH
	 * @see #HEIGHT
	 * @see SpringConstraints.Constraints
	 */
	public void setConstraint(String edgeName, Spring s) {
		edgeName = edgeName.intern();
		if (edgeName == WEST) {
			setWest(s);
		} else if (edgeName == NORTH) {
			setNorth(s);
		} else if (edgeName == EAST) {
			setEast(s);
		} else if (edgeName == SOUTH) {
			setSouth(s);
		} else if (edgeName == HORIZONTAL_CENTER) {
			setHorizontalCenter(s);
		} else if (edgeName == WIDTH) {
			setWidth(s);
		} else if (edgeName == HEIGHT) {
			setHeight(s);
		} else if (edgeName == VERTICAL_CENTER) {
			setVerticalCenter(s);
		} else if (edgeName == BASELINE) {
			setBaseline(s);
		}
	}

	/**
	 * Returns the value of the specified edge, which may be a derived
	 * value, or even <code>null</code>. The edge must have one of the
	 * following values: <code>SpringLayout.NORTH</code>,
	 * <code>SpringLayout.SOUTH</code>, <code>SpringLayout.EAST</code>,
	 * <code>SpringLayout.WEST</code>,
	 * <code>SpringLayout.HORIZONTAL_CENTER</code>,
	 * <code>SpringLayout.VERTICAL_CENTER</code>,
	 * <code>SpringLayout.BASELINE</code>, <code>SpringLayout.WIDTH</code>
	 * or <code>SpringLayout.HEIGHT</code>. For any other
	 * <code>String</code> value passed as the edge, <code>null</code> will
	 * be returned. Throws <code>NullPointerException</code> for a
	 * <code>null</code> edge.
	 * 
	 * @param edgeName
	 *            the edge whose value is to be returned
	 * 
	 * @return the spring controlling the specified edge, may be
	 *         <code>null</code>
	 * 
	 * @throws NullPointerException
	 *             if <code>edgeName</code> is <code>null</code>
	 * 
	 * @see #setConstraint
	 * @see #NORTH
	 * @see #SOUTH
	 * @see #EAST
	 * @see #WEST
	 * @see #HORIZONTAL_CENTER
	 * @see #VERTICAL_CENTER
	 * @see #BASELINE
	 * @see #WIDTH
	 * @see #HEIGHT
	 * @see SpringConstraints.Constraints
	 */
	public Spring getConstraint(String edgeName) {
		edgeName = edgeName.intern();
		return (edgeName == WEST) ? getWest()
				: (edgeName == NORTH) ? getNorth()
						: (edgeName == EAST) ? getEast()
								: (edgeName == SOUTH) ? getSouth()
										: (edgeName == WIDTH) ? getWidth()
												: (edgeName == HEIGHT) ? getHeight()
														: (edgeName == HORIZONTAL_CENTER) ? getHorizontalCenter()
																: (edgeName == VERTICAL_CENTER) ? getVerticalCenter()
																		: (edgeName == BASELINE) ? getBaseline()
																				: null;
	}

	private void reset() {
		Spring[] allSprings = { west, north, width, height, east, south,
				horizontalCenter, verticalCenter, baseline };
		for (Spring s : allSprings) {
			if (s != null) {
				s.setValue(Spring.UNSET);
			}
		}
	}

	
}
