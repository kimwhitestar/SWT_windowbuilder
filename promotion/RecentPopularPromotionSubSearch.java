package WindowBuilder.promotion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class RecentPopularPromotionSubSearch extends JFrame {
  @SuppressWarnings("rawtypes")
  private JTextField txtInterest;
  private JTextField txtSubject;
  private JTextField txtOrigin;
  private JTextArea txtAreaContent; 
  private JLabel lblMsgBox;
  private RecentPopularPromotionServiceImpl implPromotion;
  private RecentPopularPromotionVO searchVO;
  private RecentPopularPromotionVO voPromotion;
  
  public RecentPopularPromotionSubSearch(RecentPopularPromotionVO searchVO) {
    this();
    implPromotion = new RecentPopularPromotionServiceImpl();
    voPromotion = new RecentPopularPromotionVO();
    voPromotion = implPromotion.search(this.searchVO);
//    lblImg = voPromotion.getPicture();
    int partCode = voPromotion.getPartCode();
    for (int i=0; i<InterestPartServiceImpl.listVO.size(); i++) {
      if (searchVO.getPartCode() == InterestPartServiceImpl.listVO.get(i).getPartCode())
        txtInterest.setText(InterestPartServiceImpl.listVO.get(i).getPartName());
    }
    txtSubject.setText(voPromotion.getSubject());
    txtOrigin.setText(voPromotion.getOrigin());
    txtAreaContent.setText(voPromotion.getContent());
  }
  
  public RecentPopularPromotionSubSearch() {
    super("SWT 최신 소식 홍보물 상세조회 - Sub");
    setSize(1024, 768);
    setLocationRelativeTo(null);
    setResizable(false);

    getContentPane().setLayout(null);
    
    JPanel panel = new JPanel();
    panel.setBounds(0, 0, 1010, 740);
    getContentPane().add(panel);
    panel.setLayout(null);
    
    JLabel lblTitle = new JLabel("최신 소식 홍보물 상세조회");
    lblTitle.setBounds(12, 0, 1000, 50);
    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
    lblTitle.setFont(new Font("굴림", Font.BOLD, 20));
    panel.add(lblTitle);

    JLabel lblImg = new JLabel("");
    /*
    URL imgUrl = this.getClass().getClassLoader().getResource("nimoZ.jpg");
    lblImg.setIcon(new ImageIcon(imgUrl));
    */
    //ImageIcon("D:\\JavaGreen\\html5_css3_javascript\\images\\2.jpg")
    lblImg.setBounds(38, 42, 930, 260);
    panel.add(lblImg);
    
    JLabel lblInterest = new JLabel("관심분야");
    lblInterest.setBounds(38, 312, 88, 25);
    lblInterest.setFont(new Font("굴림", Font.PLAIN, 15));
    panel.add(lblInterest);
    
    JLabel lblSubject = new JLabel("제      목");
    lblSubject.setBounds(38, 339, 88, 25);
    lblSubject.setFont(new Font("굴림", Font.PLAIN, 15));
    panel.add(lblSubject);
    
    JLabel lblOrigin = new JLabel("출      처");
    lblOrigin.setBounds(38, 367, 88, 25);
    lblOrigin.setFont(new Font("굴림", Font.PLAIN, 15));
    panel.add(lblOrigin);
    
    JLabel lblContent = new JLabel("내      용");
    lblContent.setFont(new Font("굴림", Font.PLAIN, 15));
    lblContent.setBounds(38, 394, 88, 25);
    panel.add(lblContent);
    
    txtInterest = new JTextField();
    txtInterest.setEditable(false);
    txtInterest.setEnabled(false);
    txtInterest.setBounds(105, 312, 862, 25);
    txtInterest.setFont(new Font("굴림", Font.PLAIN, 15));
    panel.add(txtInterest);
    
    txtSubject = new JTextField();
    txtSubject.setBounds(105, 339, 862, 25);
    txtSubject.setFont(new Font("굴림", Font.PLAIN, 15));
    panel.add(txtSubject);
    txtSubject.setColumns(10);
    
    txtOrigin = new JTextField();
    txtOrigin.setBounds(105, 367, 862, 25);
    txtOrigin.setFont(new Font("굴림", Font.PLAIN, 15));
    txtOrigin.setColumns(10);
    panel.add(txtOrigin);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(105, 396, 862, 218);
    panel.add(scrollPane);
    
    txtAreaContent = new JTextArea();
    txtAreaContent.setEditable(false);
    txtAreaContent.setEnabled(false);
    scrollPane.setViewportView(txtAreaContent);
    
    lblMsgBox = new JLabel("");
    lblMsgBox.setForeground(Color.RED);
    lblMsgBox.setFont(new Font("굴림", Font.ITALIC, 15));
    lblMsgBox.setBounds(38, 643, 938, 25);
    panel.add(lblMsgBox);
    
    JButton btnUpdate = new JButton("수정");
    btnUpdate.setFont(new Font("굴림", Font.PLAIN, 16));
    btnUpdate.setBounds(282, 678, 120, 30);
    panel.add(btnUpdate);
    
    JButton btnSave = new JButton("저장");
    btnSave.setFont(new Font("굴림", Font.PLAIN, 16));
    btnSave.setBounds(440, 678, 120, 30);
    panel.add(btnSave);
    
    JButton btnExit = new JButton("닫기");
    btnExit.setFont(new Font("굴림", Font.PLAIN, 16));
    btnExit.setBounds(600, 678, 120, 30);
    panel.add(btnExit);
    
    setVisible(true);

    
    txtSubject.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        txtSubject.setText("");
      }
      @Override
      public void focusLost(FocusEvent e) {
        if (0 == txtSubject.getText().trim().length()) txtSubject.setText("제목을 입력하세요");
        else voPromotion.setSubject(txtSubject.getText());
      }
    });
    txtOrigin.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        txtOrigin.setText("");
      }
      @Override
      public void focusLost(FocusEvent e) {
        if (0 == txtOrigin.getText().trim().length()) txtOrigin.setText("출처를 입력하세요");
        else voPromotion.setOrigin(txtOrigin.getText());
      }
    });
    // 저장버튼 클릭 이벤트
    btnSave.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (null == voPromotion.getSubject()) lblMsgBox.setText("제목은 필수 입력항목입니다");
        else if (null == voPromotion.getOrigin()) lblMsgBox.setText("출처는 필수 입력항목입니다");
        else {
          int result = implPromotion.update(voPromotion);
          if (0 < result) lblMsgBox.setText("홍보물이 수정되었습니다"); 
          else lblMsgBox.setText("홍보물을 수정할 수 없습니다"); 
        }
      }
    });
    // X버튼 클릭
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // 종료버튼 클릭
    btnExit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
    // 종료버튼에서 엔터키를 누르면 종료.
    btnExit.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        dispose();
      }
    });    
  }
  
  /**
   * 첨부된 사진을 byte[]로 변경
   */
  private byte[] transByteCodeForPicture(String path) {
    return null;
  }
  
  public static void main(String[] args) {
    RecentPopularPromotionVO vo = new RecentPopularPromotionVO();
    vo.setCreateDate("2022-04-04 13:12:15");
    new RecentPopularPromotionSubSearch(vo);
  }
}
