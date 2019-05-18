/****************************
 * @author : Lucas Lima
 * Email: lulima559@gmail.com
 ****************************/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Utils {
	
	public static void showMessage(String message, String title, int timeout) {
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
