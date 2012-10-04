package converter;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;


public class BaseConverterContent extends JPanel {
	private static JTextField decimal = new JTextField("0");
	private static JTextField binary = new JTextField("0");
	public BaseConverterContent(){
		
		this.setLayout(new GridLayout(2,2));
		decimal.getDocument().addDocumentListener(new DocumentListener(){

			@Override
			public void changedUpdate(DocumentEvent arg0) {
					
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				Document doc = arg0.getDocument();
				String val= "";
				try {
					val = doc.getText(0, doc.getLength());
					Integer deci = Integer.parseInt(val);
					binary.setText(Integer.toBinaryString(deci));
					
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		JLabel binaryLabel = new JLabel("Binary Value: ");
		JLabel decimalLabel = new JLabel("Decimal Value: ");
		this.add(decimalLabel);
		this.add(decimal);
		this.add(binaryLabel);
		this.add(binary);
	}
}
