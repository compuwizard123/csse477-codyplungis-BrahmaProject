package vectorgraphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

/**
 * contains all of the information for creating a line.
 * 
 * @author onggr. Created Sep 30, 2010.
 */
public class LineShape extends Shape {

	final private double CLICKERROR = 5;

	/**
	 * Creates a line using the Line2D.Double class.
	 */
	Line2D.Double line;

	/**
	 * creates a line with the given parameters.
	 * 
	 * @param xpos
	 * @param ypos
	 * @param width
	 * @param height
	 * @param borderColor
	 * @param fillColor
	 */
	public LineShape(double xpos, double ypos, double width, double height,
			Color borderColor, Color fillColor) {
		super(xpos, ypos, width, height, borderColor, fillColor);
		// Do Nothing.
	}

	@Override
	protected void draw(Graphics g) {
		this.line = new Line2D.Double(this.xpos, this.ypos, this.width,
				this.height);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(this.borderColor);
		g2.setStroke(new BasicStroke(2));
		g2.draw(this.line);
		g2.setColor(this.fillColor);
		g2.fill(this.line);
	}

	@Override
	public boolean contains(double x, double y) {
		return this.line.contains(x, y)
				|| this.line.ptLineDist(x, y) < this.CLICKERROR;
	}

	@Override
	public void move(double newX, double newY, double xOffset, double yOffset) {
		double deltaX = this.width - this.xpos;
		double deltaY = this.height - this.ypos;
		this.xpos = newX - xOffset;
		this.ypos = newY - yOffset;
		this.width = newX + deltaX;
		this.height = newY + deltaY;
	}
}
