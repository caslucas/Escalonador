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

public class Utils {
	
	private static final ImageIcon ICONINFO = new ImageIcon("src/images/teste.png");
	
	public static ImageIcon getIconinfo() {
		return ICONINFO;
	}

	public static void showMessage(String message, String title, int timeout) {
		
		
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Times New Roman", Font.BOLD, 20))); 
		UIManager.put("OptionPane.messageForeground", Color.WHITE);

		
		 UIManager.put ("Panel.background", new Color(79,79,79));
		 UIManager.getDefaults().put("OptionPane.background",new Color(79,79,79));
		 UIManager.put("OptionPane.okButtonText", JOptionPane.OK_CANCEL_OPTION);  
		 UIManager.put("OptionPane.okButtonText", JOptionPane.QUESTION_MESSAGE);  
		 
		JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE);
		
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
