package view;

import java.awt.Color;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import server.User;

public class Status extends JPanel {


	public Status(User user) {
		initGUI(user);
	}
	
	private void initGUI(User user) {
		JTextField IDField = Utility.createTextField(user.getId(),100,50,280,30,false,Color.BLACK);
		JTextField NameField = Utility.createTextField(user.getName(),100,100,280,30,false,Color.BLACK);
		JTextField AgeField = Utility.createTextField(user.getGender(),100,150,280,30,false,Color.BLACK);
		JTextField GenderField = Utility.createTextField(String.valueOf(user.getAge()),100,200,280,30,false,Color.BLACK);
		JTextField HeightField = Utility.createTextField(String.valueOf(user.getHeight()),100,250,280,30,false,Color.BLACK);
		JTextField WeightField = Utility.createTextField(String.valueOf(user.getWeight()),100,300,280,30,false,Color.BLACK);
		JTextField GoalField = Utility.createTextField(String.valueOf(user.getGoal_weight()),100,350,280,30,false,Color.BLACK);
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0,0,1300,700);
		layeredPane.setLayout(null);
		
		layeredPane.add(IDField); 
		layeredPane.add(NameField); 
		layeredPane.add(AgeField); 
		layeredPane.add(GenderField); 
		layeredPane.add(HeightField); 
		layeredPane.add(WeightField); 
		layeredPane.add(GoalField); 
		this.add(layeredPane);
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(1300,700);
		
	}
}