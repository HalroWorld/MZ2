package mz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mz.dao.MZ_dbConn;

public class MZ_home_p1_test {

	private JFrame frame;
	private JButton btn_K;
	private JButton btn_C, btn_F, btn_AZ, btn_J, btn_D;
	private JTextField search;
	

	public MZ_home_p1_test() {
		main_P1();
	}

	public void main_P1() {
		// 프레임 호출(이미지 추가, 컬러 추가, 사이즈 및 위치 조정)
		frame = new JFrame();
		// 프레임 사이즈 지정(x, y,너비,높이)
		frame.setBounds(100, 100, 1102, 999);
		// 배경색 흰색
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		// x버튼 닫기 기능
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 아이콘 이미지
		frame.setIconImage(new ImageIcon("./src/Img/mzduck.png").getImage());
		// 이름 지정
		frame.setTitle("맛-ZIP");
		// 레이아웃을 보더레이아웃으로 지정하고 상하좌우 여백을 50씩 줍니다.
		frame.getContentPane().setLayout(new BorderLayout(50, 50));
		// 프레임 사이즈 고정
		frame.setResizable(false);
		// frame2 창 띄우자마자 센터로 옮김	
		frame.setLocationRelativeTo(null);
		// 오리 이미지 라벨 생성(이미지 추가, 컬러 추가, 사이즈 및 위치 조정, 프레임에 추가)
		JLabel MZ_logo = new JLabel("");
		// 로고 위치 지정
		// MZ_logo.setBounds(33, 36, 1002, 124);
		// 로고 이미지 지정 및 불러오기
		MZ_logo.setIcon(new ImageIcon("./src/Img/mzducklogo1.png"));
		// 로고 가운데 정렬
		MZ_logo.setHorizontalAlignment(SwingConstants.CENTER);
		// 로고 라벨을 layout의 상단에 위치
		frame.getContentPane().add(MZ_logo, BorderLayout.NORTH);

		// 프레임의 BoardLayout 센터에 패널을 만들고 상하좌우 여백을 50씩 줍니다.
		JPanel cenPn = new JPanel(new BorderLayout(50, 50));
		// cnePn의 배경 색을 흰색으로 지정합니다.
		cenPn.setBackground(new Color(255, 255, 255));

		// cenPn의 BoardLayout 센터의 윗부분에 텍스트 필드를 넣기 위해 패널을 만듭니다.
		JPanel cenPnNth = new JPanel();
		// cenPnNth 패널의 배경 색을 흰색으로 지정합니다.
		cenPnNth.setBackground(new Color(255, 255, 255));
		// 텍스트 필드의 배치를 위해서 그리드를 만들어줍니다.
		cenPnNth.setLayout(new GridBagLayout());
		// 그리드 사이즈를 변동 가능하기 위해 생성자를 만들어줍니다.
		GridBagConstraints gbc = new GridBagConstraints();
		// x, y축을 다 채웁니다.
		gbc.fill = GridBagConstraints.BOTH;
		// cenPnNth 패널에 추가할 서치 텍스트 필드 생성
		search = new JTextField();
		// 서치 필드의 글꼴 지정
		search.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		// 그리드의 사이즈 지정(5:1) 텍스트 필드는 5의 비율
		gbc.weightx = 0.5;// 비율이 0.5:0.1이므로 버튼의 크기는 가로축으로 5배
		gbc.gridx = 0;
		gbc.gridy = 0; // 버튼이 두개로 0,0 기준으로 생성
		// cenPnNth 패널에 서치 필드 추가
		cenPnNth.add(search, gbc);
		// 텍스트 필드 옆에 배치할 버튼을 만들어줍니다.
		JButton searchBtn = new JButton();
		// 버튼에 이미지를 넣어줍니다.
		searchBtn.setIcon(new ImageIcon("./src/Img/se.png"));
		// 버튼의 색상을 흰색으로 만들어줍니다.
		searchBtn.setBackground(Color.WHITE);
		// 그리드의 사이즈 지정(5:1) 검색 버튼은 1의 비율
		gbc.weightx = 0.1; // 비율이 0.5:0.1이므로 버튼의 크기는 가로축으로 1배
		gbc.gridx = 1;
		gbc.gridy = 0; // 버튼이 두개로 1,0 가 버튼 생성시작점
		// cenPnNth 패널에 검색 버튼 추가
		cenPnNth.add(searchBtn, gbc);
		// cenPn 패널에 서치 텍스트 필드와 검색 버튼 추가
		cenPn.add("North", cenPnNth);

		// 6개의 버튼을 배치하기 위한 cenSixBtnPn 패널을 만들고 그리드 레이아웃(2행 3열)을 지정합니다.
		JPanel cenSixBtnPn = new JPanel();
		cenSixBtnPn.setBackground(Color.WHITE);
		cenSixBtnPn.setLayout(new GridLayout(2, 3));

		List<JButton> btnList = new ArrayList<>();

		String str[] = { "k", "c", "f", "a", "j", "d" };

		btnList.add(btn_K);
		btnList.add(btn_C);
		btnList.add(btn_F);
		btnList.add(btn_AZ);
		btnList.add(btn_J);
		btnList.add(btn_D);
		int i = 0;

		for (JButton btn : btnList) {

			btn = new JButton(str[i]);
			btn.setForeground(new Color(255, 255, 255, 0));
			btn.setBorderPainted(false);
			btn.setBackground(Color.WHITE);
			btn.setIcon(new ImageIcon("./src/Img/" + str[i] + ".png"));
			btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MZ_main.code = e.getActionCommand();
					try {
						new MZ_list_p2();
						frame.setVisible(false);
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
					
				}
			});
			cenSixBtnPn.add(btn);
			i++;
		}

		cenPn.add("Center", cenSixBtnPn);

		// 아래쪽에 Q&A, HOME, 리뷰 버튼을 배치하기 위한 패널 추가 및 바탕 흰색
		JPanel southThreeBtnPn = new JPanel();
		southThreeBtnPn.setBackground(Color.WHITE);

		southThreeBtnPn.setLayout(new GridLayout(1, 3));
		// Q&A를 위한 버튼을 만들고 글꼴 지정, 배경색 흰색, 이미지를 넣어줍니다.
		JButton btn_QNA = new JButton("Q&A");
		btn_QNA.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 30));
		btn_QNA.setBackground(Color.WHITE);
		// 마우스 호버효과
		btn_QNA.addMouseListener(new MouseAdapter() {
			@Override
			// 마우스 올리면 색이 바뀝니다.
			public void mouseEntered(MouseEvent e) {
				btn_QNA.setBackground(new Color(255, 242, 197));
				btn_QNA.repaint();
			}

			@Override
			// 마우스가 버튼을 벗어나면 흰색으로 돌아옵니다.
			public void mouseExited(MouseEvent e) {
				btn_QNA.setBackground(Color.WHITE);
			}
		});
		// 버튼 활성화 테두리 없애기
		btn_QNA.setBorderPainted(false);
		// 아래쪽에 위치한 southThreeBtnPn 패널에 버튼을 추가합니다.
		southThreeBtnPn.add(btn_QNA);

		// HOME을 위한 버튼을 만들고 배경색 흰색, 글꼴 지정, 이미지를 넣어줍니다.
		JButton btn_Home = new JButton("");
		btn_Home.setIcon(new ImageIcon("./src/Img/home.png"));
		btn_Home.setBackground(Color.WHITE);
		// 마우스 호버효과
		btn_Home.addMouseListener(new MouseAdapter() {
			// 마우스 올리면 색이 바뀝니다.
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_Home.setBackground(new Color(255, 242, 197));
				btn_Home.repaint();
			}

			// 마우스가 버튼을 벗어나면 흰색으로 돌아옵니다.
			@Override
			public void mouseExited(MouseEvent e) {
				btn_Home.setBackground(Color.WHITE);
			}
		});
		// 버튼 활성화 테두리 없애기
		btn_Home.setBorderPainted(false);
		// 아래쪽에 위치한 southThreeBtnPn 패널에 버튼을 추가합니다.
		southThreeBtnPn.add(btn_Home);

		// 리뷰를 위한 버튼을 만들고 배경색 흰색, 글꼴 지정, 이미지를 넣어줍니다.
		JButton btn_Re = new JButton("리뷰");
		btn_Re.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 30));
		btn_Re.setBackground(Color.WHITE);
		// 마우스 호버효과
		btn_Re.addMouseListener(new MouseAdapter() {
			// 마우스 올리면 색이 바뀝니다.
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_Re.setBackground(new Color(255, 242, 197));
				btn_Re.repaint();
			}

			// 마우스가 버튼을 벗어나면 흰색으로 돌아옵니다.
			@Override
			public void mouseExited(MouseEvent e) {
				btn_Re.setBackground(Color.WHITE);
			}
		});
		// 버튼 활성화 테두리 없애기
		btn_Re.setBorderPainted(false);
		// 아래쪽에 위치한 southThreeBtnPn 패널에 버튼을 추가합니다.
		southThreeBtnPn.add(btn_Re);

		// southThreeBtnPn 패널을 cenPn 패널 아래쪽에 추가합니다.
		cenPn.add("South", southThreeBtnPn);

		// 프레임의 센터에 cenPn 패널을 넣어줍니다.
		frame.add("Center", cenPn);

		// 여백을 만들어주기 위해서 패널을 동/서/남으로 만들어주고 배경 흰색으로 변경해 frame에 넣는다.
		JPanel eastPn = new JPanel();
		eastPn.setBackground(Color.WHITE);
		JPanel westPn = new JPanel();
		westPn.setBackground(Color.WHITE);
		JPanel southPn = new JPanel();
		southPn.setBackground(Color.WHITE);
		frame.add("East", eastPn);
		frame.add("West", westPn);
		frame.add("South", southPn);

		// 프레임이 나타나게 만듬
		frame.setVisible(true);
	}
}