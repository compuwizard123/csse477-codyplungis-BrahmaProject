package vectorgraphics;

import java.awt.Color;
import java.awt.Graphics;

/**
 * The abstract shape class is the parent of LineShape, RectangleShape, and
 * EllipseShape.
 * 
 * @author onggr. Created Sep 28, 2010.
 */
public abstract class Shape {

	/**
	 * the x-position of the shape.
	 */
	double xpos;
	/**
	 * the y-position of the shape.
	 */
	double ypos;
	/**
	 * the width of the shape.
	 */
	double width;
	/**
	 * the height of the shape.
	 */
	double height;
	/**
	 * the border color of the shape.
	 */
	Color borderColor;
	/**
	 * the fill color of the shape.
	 */
	Color fillColor;

	/**
	 * Creates a shape with the given parameters.
	 * 
	 * @param xpos
	 *            the x-position of the shape.
	 * @param ypos
	 *            the y-position of the shape.
	 * @param width
	 *            the width of the shape.
	 * @param height
	 *            the height of the shape.
	 * @param borderColor
	 *            the border color of the shape.
	 * @param fillColor
	 *            the fill color of the shape.
	 */
	public Shape(double xpos, double ypos, double width, double height,
			Color borderColor, Color fillColor) {
		this.xpos = xpos;
		this.ypos = ypos;
		this.width = width;
		this.height = height;
		this.borderColor = borderColor;
		this.borderColor = borderColor;
		this.fillColor = fillColor;
	}

	@Override
	public Shape clone() throws CloneNotSupportedException {
		if (this instanceof LineShape) {
			return new LineShape(this.xpos + 15, this.ypos + 15, this.width,
					this.height, this.borderColor, this.fillColor);
		} else if (this instanceof RectangleShape) {
			return new RectangleShape(this.xpos + 15, this.ypos + 15,
					this.width, this.height, this.borderColor, this.fillColor);
		} else if (this instanceof EllipseShape) {
			return new EllipseShape(this.xpos + 15, this.ypos + 15, this.width,
					this.height, this.borderColor, this.fillColor);
		} else {
			return null;
		}
	}

	/**
	 * Draws the given shape.
	 * 
	 * @param g
	 * 
	 */
	protected void draw(Graphics g) {
		// Override by each shape

	}

	/**
	 * Checks whether a point is contained within a shape.
	 * 
	 * @param x
	 *            the x-coordinate of the point.
	 * @param y
	 *            the y-coordinate of the point.
	 * @return true if the point is contained in the shape, and false otherwise.
	 */
	public boolean contains(double x, double y) {
		// Overriden by each shape
		return false;
	}

	/**
	 * Moves the given shape to the given coordinates.
	 * 
	 * @param newX
	 *            the x-coordinate of the new point.
	 * @param newY
	 *            the y-coordinate of the new point.
	 * @param xOffset
	 *            the offset of the x coordinates.
	 * @param yOffset
	 *            the offset of the y coordinates.
	 */
	public void move(double newX, double newY, double xOffset, double yOffset) {
		// Overriden by each shape
	}

	/**
	 * Sets the field called 'borderColor' to the given value.
	 * 
	 * @param borderColor
	 *            The borderColor to set.
	 */
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	/**
	 * Sets the field called 'fillColor' to the given value.
	 * 
	 * @param fillColor
	 *            The fillColor to set.
	 */
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	/**
	 * Returns the value of the field called 'xpos'.
	 * 
	 * @return Returns the xpos.
	 */
	public double getXpos() {
		return this.xpos;
	}

	/**
	 * Returns the value of the field called 'ypos'.
	 * 
	 * @return Returns the ypos.
	 */
	public double getYpos() {
		return this.ypos;
	}

	/**
	 * Sets the field called 'ypos' to the given value.
	 * 
	 * @param ypos
	 *            The ypos to set.
	 */
	public void setYpos(double ypos) {
		this.ypos = ypos;
	}

	/**
	 * Sets the field called 'xpos' to the given value.
	 * 
	 * @param xpos
	 *            The xpos to set.
	 */
	public void setXpos(double xpos) {
		this.xpos = xpos;
	}

}
