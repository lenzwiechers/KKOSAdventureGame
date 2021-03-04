import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Inventory extends Window {
	
	private static final long serialVersionUID = -8220570650027588844L;

	LinkedList<Item> items = new LinkedList<Item>();
	
	static JPanel panel = new JPanel();
	
	Picture background;
	
	JButton button;
	
	Window window;
	
	public Inventory(Window window) {
		super("Inventory", 0, 0, 500, 500, panel);
		
		this.setVisible(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.window = window;
		
		background = new Picture("inv_background");
		
		panel.add(background);
		background.setBounds(0, 0, 1000, 1000);
		
		button = new JButton("Go back");
		panel.add(button);
		button.setBounds(50, 400, 400, 20);
		button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                discardInv();
            }
        });
	}
	
	public void showInv() {
		this.setVisible(true);
		window.setVisible(false);
	}
	
	public void discardInv() {
		this.setVisible(false);
		window.setVisible(true);
	}
}
