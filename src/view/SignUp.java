package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import server.User;
import view.Login.LoginListener;

public class SignUp extends JFrame {

	BufferedImage img = null;
	JTextField IDTextField;
	JTextField AgeTextField;
	JTextField NameTextField;
	JTextField HeightTextField;
	JTextField WeightTextField;
	JTextField GoalTextField;
	JPasswordField passwordField;
	JPasswordField passwordConfirmField;
	JRadioButton rdbt1, rdbt2;
	JButton Ybt;
	JButton Nbt;
	JButton checkDuplicate;
	ButtonGroup genderButton = new ButtonGroup();
	String s;

	private boolean duplicate = true;
	private LoginListener listener;

	public SignUp(LoginListener listener) {
		this.listener = listener;
		initGUI();
	}

	public void initGUI() {
		this.setTitle("회원가입");
		this.setSize(750, 1334);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// 레이아웃
		this.setLayout(null);
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 750, 1334);
		layeredPane.setLayout(null);

		try {
			img = ImageIO.read(new File("image/gameOff.png")); // 이미지 변경부분
		} catch (IOException e) {
			System.out.println("Fail to load image");
			System.exit(0);
		}
		// 패널
		Mypanel panel = new Mypanel();
		panel.setBounds(0, 0, 750, 1334);
		// 아이디
		IDTextField = Utility.createTextField(100, 100, 280, 30, false, Color.BLACK);
		layeredPane.add(IDTextField); // 패널에 놓기

		// 비밀번호
		passwordField = Utility.createPasswordField(100, 150, 280, 30, false, Color.BLACK);
		
		layeredPane.add(passwordField);
		// 비밀번호확인
		passwordConfirmField = Utility.createPasswordField(100, 200, 280, 30, false, Color.BLACK);
		layeredPane.add(passwordConfirmField);
		// 이름
		NameTextField = Utility.createTextField(100, 250, 280, 30, false, Color.BLACK);
		layeredPane.add(NameTextField);
		// 나이
		AgeTextField = Utility.createTextField(100, 300, 280, 30, false, Color.BLACK);
		layeredPane.add(AgeTextField);
		// 성
		rdbt1 = new JRadioButton("F");
		rdbt2 = new JRadioButton("M");
		// 버튼 그룹화

		genderButton.add(rdbt1);
		genderButton.add(rdbt2);
		rdbt1.setBounds(100, 350, 31, 31);
		rdbt2.setBounds(133, 350, 35, 31);
		layeredPane.add(rdbt1);
		layeredPane.add(rdbt2);

		// 키
		HeightTextField = Utility.createTextField(100, 400, 280, 30, false, Color.BLACK);
		layeredPane.add(HeightTextField);
		// 몸무게
		WeightTextField =  Utility.createTextField(100, 450, 280, 30, false, Color.BLACK);
		layeredPane.add(WeightTextField);
		// 목표
		GoalTextField =  Utility.createTextField(100, 500, 280, 30, false, Color.BLACK);
		layeredPane.add(GoalTextField);

		// 확인버튼
		Ybt = Utility.createButton("회원가입 완료", 100, 600, 115, 23);
		layeredPane.add(Ybt);// 패널에 넣기

		// 취소버튼
		Nbt = Utility.createButton("회원가입 취소", 217, 600, 115, 23);	
		layeredPane.add(Nbt);// 패널에 넣기

		// 중복확인
		checkDuplicate = new JButton("중복확인");
		checkDuplicate.setBounds(400, 100, 90, 30);
		layeredPane.add(checkDuplicate);

		layeredPane.add(panel);
		add(layeredPane);
		setVisible(true);

		Nbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}

		});

		Ybt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id = IDTextField.getText();
				String temp_age = AgeTextField.getText();
				int age = Integer.parseInt(temp_age);

				String name = NameTextField.getText();

				String temp_height = HeightTextField.getText();
				float height = Float.parseFloat(temp_height);

				String temp_weight = WeightTextField.getText();
				float weight = Float.parseFloat(temp_weight);

				String temp_goal = GoalTextField.getText();
				float goal = Float.parseFloat(temp_goal);

				char[] pass = passwordField.getPassword();
				String pw = new String(pass);

				char[] passconfirm = passwordConfirmField.getPassword();
				String pwconf = new String(passconfirm);

				Enumeration<AbstractButton> enums = genderButton.getElements();
				String gender = null;

				while (enums.hasMoreElements()) {
					AbstractButton ab = enums.nextElement();
					JRadioButton jb = (JRadioButton) ab;

					if (jb.isSelected())
						gender = jb.getText().trim();
				}

				if (id.equals("")) {
					JOptionPane.showMessageDialog(null, "ID field EMPTY!!");
				} else if (temp_age.equals("")) {
					JOptionPane.showMessageDialog(null, "AGE field EMPTY!!");
				} else if (name.equals("")) {
					JOptionPane.showMessageDialog(null, "NAME field EMPTY!!");
				} else if (temp_height.equals("")) {
					JOptionPane.showMessageDialog(null, "HEIGHT field EMPTY!!");
				} else if (temp_weight.equals("")) {
					JOptionPane.showMessageDialog(null, "WEIGHT field EMPTY!!");
				} else if (temp_goal.equals("")) {
					JOptionPane.showMessageDialog(null, "Goal field EMPTY!!");
				} else if (pw.equals("")) {
					JOptionPane.showMessageDialog(null, "PASSWORD field EMPTY!!");
				} else if (pwconf.equals("")) {
					JOptionPane.showMessageDialog(null, "PASSWORDCONFIRM field EMPTY!!");
				} else if (pw.equals(pwconf) == false) {
					JOptionPane.showMessageDialog(null, "PASSWORDCONFIRM field and PASSWORD field differ!!");
				} else {
					if (!duplicate) {
						boolean valid = listener
								.onClickSignUp(new User(id, pw, name, age, gender, height, weight, goal));
						if (valid) {
							JOptionPane.showMessageDialog(null, "추카포카 회원가입 성공");
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "회원가입 실패라능 ㅇㅅㅇ");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "중복체크를 해주세요");
					}
				}
			}
		});

		checkDuplicate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (IDTextField.getText().length() > 0) {
					duplicate = listener.onCheckDuplicate(IDTextField.getText());
					if (duplicate) {
						JOptionPane.showMessageDialog(null, "중복됨 딴거 쓰셈 ㅋ");
					} else {
						JOptionPane.showMessageDialog(null, "쓰셈ㅋ");
					}
				} else {
					JOptionPane.showMessageDialog(null, "아이디를 입력하세요");
				}
			}
		});

	}

	class Mypanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

}
