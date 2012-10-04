package vectorgraphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * contains all of the information for creating an ellipse.
 * 
 * @author onggr. Created Sep 30, 2010.
 */
public class EllipseShape extends Shape {

	private Ellipse2D.Double ellipse;

	/**
	 * creates an ellipse with the given parameters.
	 * 
	 * @param xpos
	 * @param ypos
	 * @param width
	 * @param height
	 * @param borderColor
	 * @param fillColor
	 */
	public EllipseShape(double xpos, double ypos, double width, double height,
			Color borderColor, Color fillColor) {
		super(xpos, ypos, width, height, borderColor, fillColor);
		// Do Nothing.
	}

	@Override
	protected void draw(Graphics g) {
		this.ellipse = new Ellipse2D.Double(this.xpos, this.ypos, this.width,
				this.height);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(this.borderColor);
		g2.setStroke(new BasicStroke(2));
		g2.draw(this.ellipse);
		g2.setColor(this.fillColor);
		g2.fill(this.ellipse);
	}

	@Override
	public boolean contains(double x, double y) {
		return this.ellipse.contains(x, y);
	}

	@Override
	public void move(double newX, double newY, double xOffset, double yOffset) {
		this.xpos = newX - xOffset;
		this.ypos = newY - yOffset;
	}

}
