package vectorgraphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * Crea
 * 
 * @author onggr. Created Sep 28, 2010.
 */
public class ContainerPanel extends JPanel {
	/**
	 * Contains all of the other panels, including the drawingPanel,
	 * attributePanel, and creationPanel.
	 */
	public ContainerPanel() {
		BorderLayout containerLayout = new BorderLayout();
		containerLayout.setHgap(5);
		containerLayout.setVgap(5);
		this.setLayout(containerLayout);
		this.setBackground(Color.GRAY);
		DrawingPanel drawingPanel = new DrawingPanel();
		AttributePanel attributePanel = new AttributePanel(drawingPanel);
		CreationPanel creationPanel = new CreationPanel(drawingPanel,
				attributePanel);

		attributePanel.setPreferredSize(new Dimension(250, 850));

		this.add(drawingPanel, BorderLayout.CENTER);
		this.add(attributePanel, BorderLayout.WEST);
		this.add(creationPanel, BorderLayout.NORTH);
	}
}
