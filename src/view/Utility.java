package view;

import java.awt.Color;

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
}
