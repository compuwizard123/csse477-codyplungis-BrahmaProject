package vectorgraphics;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * controls the attributes of each of the shapes.
 * 
 * @author onggr. Created Sep 28, 2010.
 */
public class AttributePanel extends JPanel {
	private DrawingPanel drawingPanel;

	/**
	 * creates an attribute panel with the given drawingPanel.
	 * 
	 * @param drawingPanel
	 */
	public AttributePanel(DrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
		this.setLayout(new GridLayout(7, 1));
		JLabel attributeLabel = new JLabel("Shape Attributes",
				SwingConstants.CENTER);
		JButton delete = new JButton("Delete");
		JButton duplicate = new JButton("Duplicate");
		JButton fillColor = new JButton("Fill Color");
		JButton borderColor = new JButton("Border Color");
		JButton moveup = new JButton("Move Up");
		JButton movedown = new JButton("Move Down");

		this.add(attributeLabel);
		this.add(delete);
		this.add(duplicate);
		this.add(fillColor);
		this.add(borderColor);
		this.add(moveup);
		this.add(movedown);

		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DrawingPanel.removeSelectedShape();
				AttributePanel.this.drawingPanel.repaint();
				AttributePanel.this.drawingPanel.revalidate();
			}
		});
		duplicate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (AttributePanel.this.drawingPanel.getSelectedShape() != null
						&& AttributePanel.this.drawingPanel.isSelect()) {

					try {
						DrawingPanel.shapes
								.add(AttributePanel.this.drawingPanel
										.getSelectedShape().clone());
					} catch (CloneNotSupportedException exception) {
						System.out.println("You can't duplicate that object.");
					}
					AttributePanel.this.drawingPanel.repaint();
					AttributePanel.this.drawingPanel.validate();
				}
			}
		});
		fillColor.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				final JColorChooser fillcolorchooser = new JColorChooser();
				AttributePanel.this.drawingPanel.setLayout(new BorderLayout());
				AttributePanel.this.drawingPanel.add(fillcolorchooser,
						BorderLayout.WEST);
				AttributePanel.this.drawingPanel.repaint();
				AttributePanel.this.drawingPanel.validate();
				fillcolorchooser.getSelectionModel().addChangeListener(
						new ChangeListener() {

							@Override
							public void stateChanged(ChangeEvent e) {
								if (AttributePanel.this.drawingPanel
										.getSelectedShape() != null
										&& AttributePanel.this.drawingPanel
												.isSelect()) {
									AttributePanel.this.drawingPanel
											.getSelectedShape().fillColor = fillcolorchooser
											.getColor();
								} else {
									AttributePanel.this.drawingPanel
											.setFillColor(fillcolorchooser
													.getColor());
								}
								AttributePanel.this.drawingPanel
										.remove(fillcolorchooser);
								AttributePanel.this.drawingPanel
										.setLayout(new FlowLayout());
							}

						});
			}
		});
		borderColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final JColorChooser bordercolorchooser = new JColorChooser();
				AttributePanel.this.drawingPanel.setLayout(new BorderLayout());
				AttributePanel.this.drawingPanel.add(bordercolorchooser,
						BorderLayout.WEST);
				AttributePanel.this.drawingPanel.repaint();
				AttributePanel.this.drawingPanel.validate();
				bordercolorchooser.getSelectionModel().addChangeListener(
						new ChangeListener() {

							@Override
							public void stateChanged(ChangeEvent e) {
								if (AttributePanel.this.drawingPanel
										.getSelectedShape() != null
										&& AttributePanel.this.drawingPanel
												.isSelect()) {
									AttributePanel.this.drawingPanel
											.getSelectedShape().borderColor = bordercolorchooser
											.getColor();
								} else {
									AttributePanel.this.drawingPanel
											.setBorderColor(bordercolorchooser
													.getColor());
								}
								AttributePanel.this.drawingPanel
										.remove(bordercolorchooser);
								AttributePanel.this.drawingPanel
										.setLayout(new FlowLayout());
							}
						});

			}

		});
		moveup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (AttributePanel.this.drawingPanel.getSelectedShape() != null
						&& AttributePanel.this.drawingPanel.isSelect()
						&& DrawingPanel.getIndexOfSelectedShape() != DrawingPanel.shapes
								.size() - 1) {
					Shape temp = AttributePanel.this.drawingPanel
							.getSelectedShape();
					int index = DrawingPanel.getIndexOfSelectedShape();
					DrawingPanel.shapes.remove(index);
					DrawingPanel.shapes.add(index + 1, temp);
					DrawingPanel.setSelect(false);
					DrawingPanel.shapes.trimToSize();
					AttributePanel.this.drawingPanel.repaint();
					AttributePanel.this.drawingPanel.validate();
				}
			}
		});
		movedown.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (AttributePanel.this.drawingPanel.getSelectedShape() != null
						&& AttributePanel.this.drawingPanel.isSelect()
						&& DrawingPanel.getIndexOfSelectedShape() != 0) {
					Shape temp = AttributePanel.this.drawingPanel
							.getSelectedShape();
					int index = DrawingPanel.getIndexOfSelectedShape();
					DrawingPanel.shapes.remove(index);
					DrawingPanel.shapes.add(index - 1, temp);
					DrawingPanel.setSelect(false);
					DrawingPanel.shapes.trimToSize();
					AttributePanel.this.drawingPanel.repaint();
					AttributePanel.this.drawingPanel.validate();
				}

			}
		});

	}
}
