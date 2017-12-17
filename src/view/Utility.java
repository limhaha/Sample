package view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Utility {
	public static JTextField createTextField(String text, int x, int y, int width, int height, boolean opaque, Color color) {
		JTextField textField = new JTextField(text);
		textField.setBounds(x,y,width,height); //로그인 텍스트 필드 위치 
		textField.setOpaque(opaque); // 투명하게 하기 
		textField.setForeground(color);
		textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//투명하게 하기 
		return textField;
	}
	
	public static JTextField createTextField(int size, int x, int y, int width, int height, boolean opaque, Color color) {
		JTextField textField = new JTextField(size);
		textField.setBounds(x,y,width,height); //로그인 텍스트 필드 위치 
		textField.setOpaque(opaque); // 투명하게 하기 
		textField.setForeground(color);
		textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//투명하게 하기 
		return textField;
	}
	
	public static JTextField createTextField(int x, int y, int width, int height, boolean opaque, Color color) {
		JTextField textField = new JTextField();
		textField.setBounds(x,y,width,height); //로그인 텍스트 필드 위치 
		textField.setOpaque(opaque); // 투명하게 하기 
		textField.setForeground(color);
		textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//투명하게 하기 
		return textField;
	}
	
	public static JPasswordField createPasswordField(int x, int y, int width, int height, boolean opaque, Color color) {
		JPasswordField passField = new JPasswordField();
		passField.setBounds(x,y,width,height); //로그인 텍스트 필드 위치 
		passField.setOpaque(opaque); // 투명하게 하기 
		passField.setForeground(color);
		passField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//투명하게 하기 
		return passField;
	}
	
	public static JButton createButton(ImageIcon image, int x, int y, int width, int height) {
		JButton btn = new JButton(image);
		btn.setBounds(x, y, width, height);
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setContentAreaFilled(false);
		return btn;
	}
	
	public static JButton createButton(String text, int x, int y, int width, int height) {
		JButton btn = new JButton(text);
		btn.setBounds(x, y, width, height);
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setContentAreaFilled(false);
		return btn;
	}
}
