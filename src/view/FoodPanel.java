package view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class FoodPanel extends JPanel{
	
	public FoodPanel(){
		
		JTabbedPane childTab = new JTabbedPane(JTabbedPane.LEFT);
		JPanel koreaFood = new JPanel();
		JPanel JapenFood = new JPanel();
		JPanel ChinaFood = new JPanel();
		
		childTab.addTab("Korea", koreaFood);
		childTab.addTab("Japen",JapenFood);
		childTab.addTab("China", ChinaFood);
		
		childTab.setBounds(20, 20, 1000, 550);
		this.add(childTab);
		this.setSize(1300,700);
		this.setVisible(true);
		this.setLayout(null);
	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FoodPanel();
	}

}