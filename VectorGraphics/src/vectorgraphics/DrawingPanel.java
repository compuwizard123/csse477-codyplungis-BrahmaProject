package vectorgraphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

/**
 * the panel for drawing shapes on.
 * 
 * @author onggr. Created Sep 28, 2010.
 */
public class DrawingPanel extends JPanel {
	/**
	 * Contains all of the shapes to be drawn on the drawingPanel.
	 */
	protected static ArrayList<Shape> shapes = new ArrayList<Shape>();
	static private int startX;
	static private int startY;
	static private int dX;
	static private int dY;
	static private int endX;
	static private int endY;
	static private boolean drawRectangle = false;
	static private boolean drawEllipse = false;
	static private boolean drawLine = false;
	static private Color borderColor;
	static private Color fillColor;
	static private boolean select = false;
	static private Shape selectedShape = null;
	static private int indexOfSelectedShape = -1;

	/**
	 * Creates a DrawingPanel to draw on, with default color white
	 * 
	 */
	public DrawingPanel() {
		DrawingPanel.startX = 0;
		DrawingPanel.startY = 0;
		DrawingPanel.dX = 0;
		DrawingPanel.dY = 0;
		DrawingPanel.endX = 0;
		DrawingPanel.endY = 0;
		DrawingPanel.borderColor = Color.BLACK;
		DrawingPanel.fillColor = Color.WHITE;
		this.setBackground(Color.WHITE);
		DrawingPanel.drawLine = true;
		DrawingPanel.addShape();
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				DrawingPanel.startX = e.getX();
				DrawingPanel.startY = e.getY();
				if (select) {
					DrawingPanel.selectedShape = DrawingPanel.selectShape(e
							.getX(), e.getY());
					if (DrawingPanel.selectedShape != null) {
						DrawingPanel.dX = e.getX()
								- (int) DrawingPanel.selectedShape.getXpos();
						DrawingPanel.dY = e.getY()
								- (int) DrawingPanel.selectedShape.getYpos();
					}
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (DrawingPanel.drawLine || DrawingPanel.select) {
					DrawingPanel.endX = e.getX();
					DrawingPanel.endY = e.getY();
				} else {
					if (e.getX() < DrawingPanel.startX) {
						DrawingPanel.dX = DrawingPanel.startX - e.getX();
						DrawingPanel.startX = e.getX();
					} else {
						DrawingPanel.dX = e.getX() - DrawingPanel.startX;
					}

					if (e.getY() < DrawingPanel.startY) {
						DrawingPanel.dY = DrawingPanel.startY - e.getY();
						DrawingPanel.startY = e.getY();
					} else {
						DrawingPanel.dY = e.getY() - DrawingPanel.startY;
					}
				}
				if (select && DrawingPanel.selectedShape != null) {
					DrawingPanel.selectedShape
							.move(DrawingPanel.endX, DrawingPanel.endY,
									DrawingPanel.dX, DrawingPanel.dY);
				} else {
					DrawingPanel.addShape();
				}
				DrawingPanel.this.repaint();
				DrawingPanel.this.validate();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// if(select){
				// DrawingPanel.selectedShape =
				// DrawingPanel.selectShape(e.getX(),e.getY());
				// }
			}
		});

	}

	/**
	 * @return the selectedShape
	 */
	public Shape getSelectedShape() {
		return selectedShape;
	}

	private static void addShape() {
		Shape temp = null;
		// System.out.println(DrawingPanel.startX + " | " + DrawingPanel.startY
		// + " | " + DrawingPanel.dX + " | " + DrawingPanel.dY);
		if (DrawingPanel.drawRectangle) {
			temp = new RectangleShape(DrawingPanel.startX, DrawingPanel.startY,
					DrawingPanel.dX, DrawingPanel.dY, DrawingPanel.borderColor,
					DrawingPanel.fillColor);
		} else if (DrawingPanel.drawEllipse) {
			temp = new EllipseShape(DrawingPanel.startX, DrawingPanel.startY,
					DrawingPanel.dX, DrawingPanel.dY, DrawingPanel.borderColor,
					DrawingPanel.fillColor);
		} else if (DrawingPanel.drawLine) {
			temp = new LineShape(DrawingPanel.startX, DrawingPanel.startY,
					DrawingPanel.endX, DrawingPanel.endY,
					DrawingPanel.borderColor, DrawingPanel.fillColor);
		}
		if (!select) {
			DrawingPanel.shapes.add(temp);
		}
	}

	private static Shape selectShape(int x, int y) {
		int endIndex = DrawingPanel.shapes.size() - 1;
		boolean found = false;
		while (endIndex >= 0 && !found) {
			Shape temp = DrawingPanel.shapes.get(endIndex);
			if (temp.contains(x, y)) {
				found = true;
				DrawingPanel.indexOfSelectedShape = endIndex;
				return temp;
			} else {
				endIndex--;
			}
		}
		return null;
	}

	/**
	 * Removes the selected shape from the arrayList.
	 */
	public static void removeSelectedShape() {
		if (DrawingPanel.selectedShape != null) {
			DrawingPanel.selectedShape = null;
			DrawingPanel.shapes.remove(DrawingPanel.indexOfSelectedShape);
		}
	}

	/**
	 * @param borderColor
	 *            the borderColor to set
	 */
	public void setBorderColor(Color borderColor) {
		DrawingPanel.borderColor = borderColor;
	}

	/**
	 * @param fillColor
	 *            the fillColor to set
	 */
	public void setFillColor(Color fillColor) {
		DrawingPanel.fillColor = fillColor;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// g.drawLine(DrawingPanel.startX, DrawingPanel.startY,
		// DrawingPanel.startX+DrawingPanel.dX,DrawingPanel.startY+DrawingPanel.dY);
		Iterator<Shape> iter = shapes.iterator();
		while (iter.hasNext()) {
			iter.next().draw(g);
		}

	}

	/**
	 * Sets the field called 'drawRect' to the given value.
	 * 
	 * @param drawRect
	 *            The drawRect to set.
	 */
	public static void setDrawRectangle(boolean drawRect) {
		DrawingPanel.drawRectangle = drawRect;
	}

	/**
	 * Sets the field called 'drawEllipse' to the given value.
	 * 
	 * @param drawEllipse
	 *            The drawEllipse to set.
	 */
	public static void setDrawEllipse(boolean drawEllipse) {
		DrawingPanel.drawEllipse = drawEllipse;
	}

	/**
	 * Sets the field called 'drawLine' to the given value.
	 * 
	 * @param drawLine
	 *            The drawLine to set.
	 */
	public static void setDrawLine(boolean drawLine) {
		DrawingPanel.drawLine = drawLine;
	}

	/**
	 * Returns the value of the field called 'drawRectangle'.
	 * 
	 * @return Returns the drawRectangle.
	 */
	public static boolean isDrawRectangle() {
		return drawRectangle;
	}

	/**
	 * Returns the value of the field called 'drawEllipse'.
	 * 
	 * @return Returns the drawEllipse.
	 */
	public static boolean isDrawEllipse() {
		return drawEllipse;
	}

	/**
	 * Returns the value of the field called 'drawLine'.
	 * 
	 * @return Returns the drawLine.
	 */
	public static boolean isDrawLine() {
		return drawLine;
	}

	/**
	 * Sets the field called 'select' to the given value.
	 * 
	 * @param select
	 *            The select to set.
	 */
	public static void setSelect(boolean select) {
		DrawingPanel.select = select;
	}

	/**
	 * @return the select
	 */
	public boolean isSelect() {
		return select;
	}

	/**
	 * Returns the value of the field called 'indexOfSelectedShape'.
	 * 
	 * @return Returns the indexOfSelectedShape.
	 */
	public static int getIndexOfSelectedShape() {
		return indexOfSelectedShape;
	}
}
