package view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Sport extends JPanel{
	
	public Sport(){
		JTabbedPane childTab = new JTabbedPane(JTabbedPane.LEFT);
		JPanel out = new JPanel();
		JPanel in = new JPanel();
		
		
		childTab.addTab("OUT", out);
		childTab.addTab("IN",in);
		
		
		childTab.setBounds(20, 20, 1000, 550);
		this.add(childTab);
		this.setSize(1300,700);
		this.setVisible(true);
		this.setLayout(null);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Sport();
	}
	

}