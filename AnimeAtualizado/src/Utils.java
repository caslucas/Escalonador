/****************************
 * @author : Lucas Lima
 * Email: lulima559@gmail.com
 ****************************/
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Utils {
	
	public static void showMessage(String message, String title, int timeout) {
		
		
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Times New Roman", Font.BOLD, 18))); 
		UIManager.put("OptionPane.messageForeground", Color.WHITE);
		UIManager.getIcon("Table.removeIcon");    
		 UIManager.put ("Panel.background", new Color(79,79,79));
		 UIManager.getDefaults().put("OptionPane.background",new Color(79,79,79));
		 UIManager.put("OptionPane.okButtonText", JOptionPane.OK_CANCEL_OPTION);  
		 UIManager.put("OptionPane.okButtonText", JOptionPane.QUESTION_MESSAGE);  
		 
		JOptionPane pane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
		
		JDialog dialog = pane.createDialog(null, title);
		
		dialog.setModal(false);
		dialog.setVisible(true);
		
		new Timer(timeout, new ActionListener() {
			public void actionPerformed(ActionEvent e ) {
				dialog.setVisible(false);				
			}
		}).start();
	}
}
