package WindowBuilder.Test;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Test2Main extends JFrame {

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Test2Main() {
    super("SWT 회원관리 메인화면 학습");
    setSize(800, 600);
    setLocationRelativeTo(null);
    setResizable(false);
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setLayout(null);
    
    JPanel panel = new JPanel();
    panel.setBounds(0, 0, 800, 600);
    getContentPane().add(panel);
    panel.setLayout(null);
    
    JLabel lblSubject = new JLabel("회원관리프로그램(v1.0)");
    lblSubject.setHorizontalAlignment(SwingConstants.CENTER);
    lblSubject.setFont(new Font("굴림", Font.BOLD, 24));
    lblSubject.setBounds(50, 35, 700, 80);
    panel.add(lblSubject);
    
//    JLabel lblNewLabel_1 = new JLabel("New label");
//    URL imgUrl = this.getClass().getClassLoader().getResource("2.jpg");
//    lblNewLabel_1.setIcon(new ImageIcon(imgUrl));
//    lblNewLabel_1.setBounds(48, 129, 683, 313);
//    panel.add(lblNewLabel_1);
    
    JButton btnInput = new JButton("회원등록");
    btnInput.setFont(new Font("굴림", Font.PLAIN, 18));
    btnInput.setBounds(45, 483, 139, 47);
    panel.add(btnInput);
    
    JButton btnSearch = new JButton("개별조회");
    btnSearch.setFont(new Font("굴림", Font.PLAIN, 18));
    btnSearch.setBounds(229, 483, 139, 47);
    panel.add(btnSearch);
    
    JButton btnList = new JButton("전체조회");
    btnList.setFont(new Font("굴림", Font.PLAIN, 18));
    btnList.setBounds(413, 483, 139, 47);
    panel.add(btnList);
    
    JButton btnExit = new JButton("종  료");
    btnExit.setFont(new Font("굴림", Font.PLAIN, 18));
    btnExit.setBounds(597, 483, 139, 47);
    panel.add(btnExit);
    
    setVisible(true);
    
    /* 아래로 메소드 처리부 */

    // 회원등록
    btnInput.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new Test2Input();
      }
    });
    
    // 개별조회
    btnSearch.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new Test2Search();
      }
    });
    
    //전체조회
    btnList.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new Test2List();
      }
    });
    
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
    new Test2Main();
  }
}
