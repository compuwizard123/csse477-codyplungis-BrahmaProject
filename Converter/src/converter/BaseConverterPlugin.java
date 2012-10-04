package converter;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import plugin.Plugin;

public class BaseConverterPlugin extends Plugin {
	public static final String PLUGIN_ID = "Base Converter";
	
	public BaseConverterPlugin() {
		super(PLUGIN_ID);
	}

	@Override
	public void layout(JPanel parentPanel) {
		parentPanel.setLayout(new BorderLayout());
		parentPanel.add(new BaseConverterContent());
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
