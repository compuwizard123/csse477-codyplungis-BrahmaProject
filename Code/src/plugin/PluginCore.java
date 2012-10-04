package plugin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.*;

public class PluginCore {
	// GUI Widgets that we will need
	private JFrame frame;
	private JPanel contentPane;
	private JLabel bottomLabel;
	private JPanel centerEnvelope;
	
	// For holding registered plugin
	private HashMap<String, Plugin> idToPlugin;
	private Plugin currentPlugin;
	
	// Plugin manager
	PluginManager pluginManager;
	
	public PluginCore() {
		idToPlugin = new HashMap<String, Plugin>();
		
		// Lets create the elements that we will need
		frame = new JFrame("Pluggable Board Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = (JPanel)frame.getContentPane();
		contentPane.setPreferredSize(new Dimension(700, 500));
		bottomLabel = new JLabel("No plugins registered yet!");
		
		JScrollPane scrollPane = SideBarCreator.getSideBar(this);
		
		// Create center display area
		centerEnvelope = new JPanel(new BorderLayout());
		centerEnvelope.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		
		// Lets lay them out, contentPane by default has BorderLayout as its layout manager
		contentPane.add(centerEnvelope, BorderLayout.CENTER);
		contentPane.add(scrollPane, BorderLayout.EAST);
		contentPane.add(bottomLabel, BorderLayout.SOUTH);
		
		// Start the plugin manager now that the core is ready
		try {
			this.pluginManager = new PluginManager(this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		Thread thread = new Thread(this.pluginManager);
		thread.start();
	}
	
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
	
	public void stop() {
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				frame.setVisible(false);
			}
		});
	}
	
	public void addPlugin(Plugin plugin) {
		this.idToPlugin.put(plugin.getId(), plugin);
		SideBarCreator.addElement(plugin.getId());
		this.bottomLabel.setText("The " + plugin.getId() + " plugin has been recently added!");
	}
	
	public void removePlugin(String id) {
		Plugin plugin = this.idToPlugin.remove(id);
		SideBarCreator.removeElement(id);
		
		// Stop the plugin if it is still running
		plugin.stop();

		this.bottomLabel.setText("The " + plugin.getId() + " plugin has been recently removed!");
	}

	
	// Getters
	public JPanel getContentPane() {
		return contentPane;
	}
	public JPanel getCenterEnvelope() {
		return centerEnvelope;
	}
	public Plugin getCurrentPlugin() {
		return currentPlugin;
	}
	public Plugin getIdToPlugin(String id){
		return this.idToPlugin.get(id);
	}
	
	// Setters
	public void setBottomLabelText(String text){
		this.bottomLabel.setText(text);
	}
}
