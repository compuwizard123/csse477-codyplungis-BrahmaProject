package vectorgraphics;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import plugin.Plugin;

public class VectorGraphicsPlugin extends Plugin {
	public static final String PLUGIN_ID = "Vector Graphics";
	
	public VectorGraphicsPlugin() {
		super(PLUGIN_ID);
	}

	@Override
	public void layout(JPanel parentPanel) {
		parentPanel.setLayout(new BorderLayout());
		parentPanel.add(new ContainerPanel());
	}

	@Override
	public void start() {
		// Not much to do here
	}

	@Override
	public void stop() {
		// Not much to do here
	}
	
	// For now we need to declare dummy main method
	// to include in manifest file
	public static void main(String[] args) {
			}
}
