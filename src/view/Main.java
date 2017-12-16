package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import server.User;

public class Main extends JFrame {

	JTabbedPane jtp = null;
	JPanel jp = null;
	JPanel status_pan = null;
	JPanel food_pan = null;
	JPanel sport_pan = null;
	JPanel rank_pan = null;
	JButton logout = null;
	JButton modify = null;

	JTextField IDTextField = null;
	JTextField AgeTextField = null;
	JTextField NameTextField = null;
	JTextField GenderTextField = null;
	JTextField HeightTextField = null;
	JTextField WeightTextField = null;
	JTextField GoalTextField = null;

	private MainListener listener;

	public interface MainListener {
		User getUser();
	}

	public Main(MainListener listener) {
		this.listener = listener;
		initGUI();
	}

	public void initGUI() {
		jtp = new JTabbedPane();
		jp = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료하고 자원 반환

		// 패널 설정
		// 로그아웃 버튼
		logout = new JButton("Logout");
		logout.setBounds(1200, 5, 73, 25);
		jp.add(logout);

		// 정보수정버튼
		modify = new JButton("Modify");
		modify.setBounds(1120, 5, 74, 25);
		jp.add(modify);

		jp.setBounds(0, 0, 1300, 100);
		jtp.setBounds(0, 100, 1300, 700);

		// 탭설정
		status_pan = new status_pan("Test");

		// 음식
		food_pan = new food_pan();

		// 스포츠
		sport_pan = new sport_pan();

		// 랭킹
		rank_pan = new rank_pan();

		this.setLayout(null);
		jp.setLayout(null);
		this.getContentPane().add(jp);
		this.getContentPane().add(jtp);
		this.setTitle("Kill a Gram");
		this.setSize(1300, 800);
		this.setVisible(true);
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// dispose();
				// new Login();
			}
		});

		modify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// String id = ID;
				// String[] info = new String[7];
				// info= loadService.Load(id);
				// new Modify(info[0],info[1],info[2],info[3],info[4],info[5],info[6]);
			}

		});

	}

	class status_pan extends JPanel {

		public status_pan(String id) {
//			jtp.add("STATUS", new Status(listener.getUser().getId(),listener.getUser().getName(), String.valueOf(listener.getUser().getAge()),listener.getUser().getGender()
//					,String.valueOf(listener.getUser().getHeight()),String.valueOf(listener.getUser().getWeight()),String.valueOf(listener.getUser().getGoal_weight())));
			jtp.add("STATUS",new Status(listener.getUser()));

		}
	}

	class food_pan extends JPanel {

		public food_pan() {
			jtp.add("FOOD", new Food());
		}

	}

	class sport_pan extends JPanel {

		public sport_pan() {
			jtp.add("SPORT", new Sport());
		}

	}

	class rank_pan extends JPanel {

		public rank_pan() {

			jtp.add("RANK", new Rank());
		}

	}
}
