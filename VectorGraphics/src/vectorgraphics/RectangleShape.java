package vectorgraphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * contains all of the information for creating a rectangle.
 * 
 * @author onggr. Created Sep 30, 2010.
 */
public class RectangleShape extends Shape {

	private Rectangle2D.Double rect;

	/**
	 * creates a rectangle shape with the given paramters.
	 * 
	 * @param xpos
	 * @param ypos
	 * @param width
	 * @param height
	 * @param borderColor
	 * @param fillColor
	 */
	public RectangleShape(double xpos, double ypos, double width,
			double height, Color borderColor, Color fillColor) {
		super(xpos, ypos, width, height, borderColor, fillColor);
		// Do Nothing.
	}

	@Override
	protected void draw(Graphics g) {
		this.rect = new Rectangle2D.Double(this.xpos, this.ypos, this.width,
				this.height);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(this.borderColor);
		g2.setStroke(new BasicStroke(2));
		g2.draw(this.rect);
		g2.setColor(this.fillColor);
		g2.fill(this.rect);
	}

	@Override
	public boolean contains(double x, double y) {
		return this.rect.contains(x, y);
	}

	@Override
	public void move(double newX, double newY, double xOffset, double yOffset) {
		this.xpos = newX - xOffset;
		this.ypos = newY - yOffset;
	}

}
