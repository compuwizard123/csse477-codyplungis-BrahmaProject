package vectorgraphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * selects the shapes to be drawn in the drawingPanel.
 * 
 * @author onggr. Created Sep 28, 2010.
 */
public class CreationPanel extends JPanel {
	/**
	 * is the drawingPanel for the shapes.
	 */
	protected DrawingPanel drawingPanel;
	/**
	 * changes certain attributes of shapes, such as the fill color and border
	 * color. Also allows the user to duplicate or remove selected shapes.
	 */
	protected AttributePanel attributePanel;

	/**
	 * Creates
	 * 
	 * @param drawingPanel
	 * @param attributePanel
	 */
	public CreationPanel(DrawingPanel drawingPanel,
			AttributePanel attributePanel) {
		this.drawingPanel = drawingPanel;
		this.attributePanel = attributePanel;

		Icon lineico = new ImageIcon("linebutton.gif");
		Icon rectangleico = new ImageIcon("rectanglebutton.gif");
		Icon ellipseico = new ImageIcon("ellipsebutton.gif");

		JLabel creationlabel = new JLabel("Creation Menu: ");
		JButton lineButton = new JButton(lineico);
		JButton rectangleButton = new JButton(rectangleico);
		JButton ellipseButton = new JButton(ellipseico);
		JButton selectButton = new JButton("Select");

		this.add(creationlabel);
		this.add(lineButton);
		this.add(rectangleButton);
		this.add(ellipseButton);
		this.add(selectButton);

		lineButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DrawingPanel.setDrawLine(true);
				DrawingPanel.setDrawRectangle(false);
				DrawingPanel.setDrawEllipse(false);
				DrawingPanel.setSelect(false);
			}
		});

		rectangleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DrawingPanel.setDrawLine(false);
				DrawingPanel.setDrawRectangle(true);
				DrawingPanel.setDrawEllipse(false);
				DrawingPanel.setSelect(false);
			}
		});

		ellipseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DrawingPanel.setDrawLine(false);
				DrawingPanel.setDrawRectangle(false);
				DrawingPanel.setDrawEllipse(true);
				DrawingPanel.setSelect(false);
			}
		});

		selectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DrawingPanel.setDrawLine(false);
				DrawingPanel.setDrawRectangle(false);
				DrawingPanel.setDrawEllipse(false);
				DrawingPanel.setSelect(true);
			}
		});

	}

}
