package WindowBuilder.promotion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class RecentPopularPromotionMain extends JFrame {
  
  public RecentPopularPromotionMain() {
    super("SWT 최신 소식 홍보물 관리 - Main");
    setSize(1024, 768);
    setLocationRelativeTo(null);
    setResizable(false);

    getContentPane().setLayout(null);
    
    JPanel panel = new JPanel();
    panel.setBounds(0, 0, 1010, 725);
    getContentPane().add(panel);
    panel.setLayout(null);
    
    JLabel lblTitle = new JLabel("최신 소식 홍보물 관리 프로그램(v1.0)");
    lblTitle.setBounds(12, 10, 985, 250);
    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
    lblTitle.setFont(new Font("굴림", Font.BOLD, 50));
    panel.add(lblTitle);
    
    JButton btnNew = new JButton("등   록");
    btnNew.setFont(new Font("굴림", Font.PLAIN, 18));
    btnNew.setBounds(250, 500, 120, 60);
    panel.add(btnNew);

    JButton btnSearch = new JButton("조   회");
    btnSearch.setFont(new Font("굴림", Font.PLAIN, 18));
    btnSearch.setBounds(452, 500, 120, 60);
    panel.add(btnSearch);
    
    JButton btnExit = new JButton("닫   기");
    btnExit.setFont(new Font("굴림", Font.PLAIN, 18));
    btnExit.setBounds(650, 500, 120, 60);
    panel.add(btnExit);
    
    JLabel lblImage = new JLabel("");
    lblImage.setBounds(228, 186, 566, 228);
//    URL imgUrl = this.getClass().getClassLoader().getResource("nimo.jpg");
    lblImage.setIcon(new ImageIcon("I:/JavaGreen/Java/works/0329_mysqlConnect/images/nimoZ.jpg"));
    //ImageIcon("I:/JavaGreen/Java/works/0329_mysqlConnect/images/nimo.jpg")
    panel.add(lblImage);

    setVisible(true);

    // 등록 버튼 클릭 
    btnNew.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new RecentPopularPromotionSubInput();
      }
    });
    
    // 조회 버튼 클릭
    btnSearch.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new RecentPopularPromotionList();
      }
    });

    // X버튼 클릭 이벤트
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // 종료버튼 클릭
    btnExit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    
    // 종료버튼에서 엔터키를 누르면 종료.
    btnExit.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        System.exit(0);
      }
    });    
  }
  
  public static void main(String[] args) {
    new RecentPopularPromotionMain();
  }
}
