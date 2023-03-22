package mz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MZ_addStore {
	private JFrame frame;
	private JLabel foodCodeLabel, storenameLabel, storeimageLabel, storeimage2Label, storeimage3Label, storeaddr;
	private JTextField foodCodeField, storenameField, storeaddrField;
	private JFileChooser storeimagechooser, storeimage2chooser, storeimage3chooser;
	private JButton CancelButton;
	private JButton signupButton;

	public MZ_addStore() {
		addStore();
	}

	public void addStore() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 900);
		frame.getContentPane().setLayout(new GridLayout(3, 1, 0, 0));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon("./src/Img/mzduck.png").getImage());
		frame.setTitle("맛-ZIP");

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(panel);
		JLabel MZ_logo = new JLabel("");

		// 로고 이미지 지정 및 불러오기
		MZ_logo.setIcon(new ImageIcon("./src/Img/mzducklogo1.png"));
		// 로고 가운데 정렬
		MZ_logo.setHorizontalAlignment(SwingConstants.CENTER);
		MZ_logo.setVerticalAlignment(SwingConstants.BOTTOM);
		panel.add(MZ_logo, BorderLayout.CENTER);

		JPanel panel2 = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		panel2.setBackground(new Color(255, 255, 255));

		frame.getContentPane().add(panel2);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		foodCodeLabel = new JLabel("종류");
		foodCodeLabel.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 22));
		panel2.add(foodCodeLabel, c);

		c.insets = new Insets(0, 10, 0, 0);
		c.gridx = 1;
		c.gridy = 0;
		String[] choices = { "한식", "중식", "일식", "양식", "A-Z", "디저트" };
		JComboBox<String> foodCodeField = new JComboBox<>(choices);
		foodCodeField.setBorder(new LineBorder(new Color(228, 228, 228), 2, true));
		foodCodeField.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 22));
		foodCodeField.setBackground(new Color(255, 255, 255));
		panel2.add(foodCodeField, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(20, 0, 0, 0);
		storenameLabel = new JLabel("가게이름");
		storenameLabel.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 22));
		panel2.add(storenameLabel, c);

		c.insets = new Insets(20, 10, 0, 0);
		c.gridx = 1;
		c.gridy = 1;
		storenameField = new JTextField();
		storenameField.setBorder(new LineBorder(new Color(228, 228, 228), 2, true));
		storenameField.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 22));
		storenameField.setForeground(Color.BLACK);
		panel2.add(storenameField, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(20, 0, 0, 0);
		storeimageLabel = new JLabel("이미지 1");
		storeimageLabel.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 22));
		panel2.add(storeimageLabel, c);

		c.insets = new Insets(20, 10, 0, 0);
		c.gridx = 1;
		c.gridy = 2;
		JButton btn = new JButton();
		btn.setText("이미지 업로드");
		btn.setBorder(new LineBorder(new Color(228, 228, 228), 2, true));
		btn.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 22));
		btn.setForeground(Color.BLACK);
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				storeimagechooser = new JFileChooser();
				storeimagechooser
						.addChoosableFileFilter(new FileNameExtensionFilter("그림파일(*.jpg, *.bmp, *.png)", "jpg", "bmp", "png"));
				int returnValue = storeimagechooser.showSaveDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = storeimagechooser.getSelectedFile();
					System.out.println("저장할 파일: " + selectedFile.getAbsolutePath());
				} else if (returnValue == JFileChooser.CANCEL_OPTION) {
					System.out.println("취소 또는 닫기를 눌렀군요");
				}
			}
		});

		panel2.add(btn, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(20, 0, 0, 0);
		storeimage2Label = new JLabel("이미지 2");
		storeimage2Label.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 22));
		panel2.add(storeimage2Label, c);

		c.insets = new Insets(20, 10, 0, 0);
		c.gridx = 1;
		c.gridy = 3;

		JButton btn2 = new JButton();
		btn2.setText("이미지 업로드");
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				storeimage2chooser = new JFileChooser();
				storeimage2chooser
						.addChoosableFileFilter(new FileNameExtensionFilter("그림파일(*.jpg, *.bmp, *.png)", "jpg", "bmp", "png"));
				storeimage2chooser.setBorder(new LineBorder(new Color(228, 228, 228), 2, true));
				storeimage2chooser.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 22));
				storeimage2chooser.setForeground(Color.BLACK);
			}
		});
		panel2.add(btn2, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(20, 0, 0, 0);
		storeimage3Label = new JLabel("이미지 3(긴 사진)");
		storeimage3Label.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 22));
		panel2.add(storeimage3Label, c);

		c.insets = new Insets(20, 10, 0, 0);
		c.gridx = 1;
		c.gridy = 4;
		JButton btn3 = new JButton();
		btn3.setText("이미지 업로드");
		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				storeimage3chooser = new JFileChooser();
				storeimage3chooser
						.addChoosableFileFilter(new FileNameExtensionFilter("그림파일(*.jpg, *.bmp, *.png)", "jpg", "bmp", "png"));
				storeimage3chooser.setBorder(new LineBorder(new Color(228, 228, 228), 2, true));
				storeimage3chooser.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 22));
				storeimage3chooser.setForeground(Color.BLACK);
			}
		});
		panel2.add(btn3, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(20, 0, 0, 0);
		storeaddr = new JLabel("주소 링크");
		storeaddr.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 22));
		panel2.add(storeaddr, c);

		c.insets = new Insets(20, 10, 0, 0);
		c.gridx = 1;
		c.gridy = 5;
		storeaddrField = new JTextField();
		storeaddrField.setBorder(new LineBorder(new Color(228, 228, 228), 2, true));
		storeaddrField.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 22));
		storeaddrField.setForeground(Color.BLACK);
		panel2.add(storeaddrField, c);

		JPanel panel3 = new JPanel();
		panel3.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(panel3);

		CancelButton = new JButton("취소");
		CancelButton.setForeground(new Color(2, 2, 2));
		CancelButton.setBackground(new Color(255, 255, 255));
		CancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MZ_login();
				frame.dispose();
			}
		});

		panel3.add(CancelButton);

		signupButton = new JButton("등록");
		signupButton.setBackground(new Color(2, 2, 2));
		signupButton.setBackground(new Color(255, 255, 255));
		panel3.add(signupButton);

	}

	public static void main(String[] args) {
		new MZ_addStore();
	}
}
