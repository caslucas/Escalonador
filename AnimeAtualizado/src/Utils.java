/****************************
 * @author : Lucas Lima
 * Email: lulima559@gmail.com
 ****************************/
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
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
		
		
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Times New Roman", Font.BOLD, 20 ))); 
		UIManager.put("OptionPane.messageForeground", Color.BLACK);
		UIManager.put("OptionPane.minimumSize",	new Dimension(200,100));
		UIManager.put(" OptionPane.label", SwingConstants.CENTER);
		
		
		 UIManager.put ("Panel.background", new Color(247,248,250));
		 UIManager.getDefaults().put("OptionPane.background",new Color(247,248,250));
		 
		 
		JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
		
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
