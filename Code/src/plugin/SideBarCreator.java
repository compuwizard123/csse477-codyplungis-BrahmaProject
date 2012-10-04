package plugin;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SideBarCreator {

	private static JList sideList;
	private static DefaultListModel<String> listModel;
	private static PluginCore pc;
	
	public static JScrollPane getSideBar(PluginCore pcore){
		pc = pcore;
		
		listModel = new DefaultListModel<String>();
		sideList = new JList(listModel);
		sideList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sideList.setLayoutOrientation(JList.VERTICAL);
		JScrollPane scrollPane = new JScrollPane(sideList);
		scrollPane.setPreferredSize(new Dimension(100, 50));
		
		// Add action listeners
				sideList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						// If the list is still updating, return
						if(e.getValueIsAdjusting())
							return;
						
						// List has finalized selection, let's process further
						int index = sideList.getSelectedIndex();
						String id = listModel.elementAt(index);
						Plugin currentPlugin = pc.getCurrentPlugin();
						JPanel centerEnvelope = pc.getCenterEnvelope();
						JPanel contentPane = pc.getContentPane();
						Plugin plugin = pc.getIdToPlugin(id);
						
						if(plugin == null || plugin.equals(currentPlugin))
							return;
						
						// Stop previously running plugin
						if(currentPlugin != null)
							currentPlugin.stop();
						
						// The newly selected plugin is our current plugin
						currentPlugin = plugin;
						
						// Clear previous working area
						centerEnvelope.removeAll();
						
						// Create new working area
						JPanel centerPanel = new JPanel();
						centerEnvelope.add(centerPanel, BorderLayout.CENTER); 
						
						// Ask plugin to layout the working area
						currentPlugin.layout(centerPanel);
						contentPane.revalidate();
						contentPane.repaint();
						
						// Start the plugin
						currentPlugin.start();
						
						pc.setBottomLabelText("The " + currentPlugin.getId() + " is running!");
					}
				});
		
		return scrollPane;
	}

	public static void addElement(String id){
		listModel.addElement(id);
	}
	
	public static void removeElement(String id){
		listModel.removeElement(id);
	}

}
