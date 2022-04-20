package WindowBuilder.promotion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class RecentPopularPromotionSubSearch extends JFrame {
  @SuppressWarnings("rawtypes")
  private JComboBox cmbInterest;
  @SuppressWarnings("rawtypes")
  private DefaultComboBoxModel defaultCombo;
  private List<InterestPartVO> listInterest;
  private String[][] arrInterest = {{},{}};;
  private JTextField txtSubject;
  private JTextField txtOrigin;
  private JTextArea txtAreaContent; 
  private JLabel lblMsgBox;
  private RecentPopularPromotionServiceImpl implPromotion;
  private RecentPopularPromotionVO voPromotion;
  
  public RecentPopularPromotionSubSearch(RecentPopularPromotionVO searchVO) {
    this();
    implPromotion = new RecentPopularPromotionServiceImpl();
    voPromotion = implPromotion.search(searchVO);
    //lblImg = voPromotion.getPicture();//db에서 가져온 사진(byte코드)을 outputstream으로 읽은 후, 화면 label 사진영역에 표시-미작성
    int partCode = voPromotion.getPartCode();
//System.out.println("InterestPartServiceImpl.listVO.size() = "+InterestPartServiceImpl.listVO.size());   
//System.out.println("defaultCombo.getSize() = "+ defaultCombo.getSize());
    for (int i=0; i<defaultCombo.getSize(); i++) {
      if (partCode == Integer.parseInt(arrInterest[0][i])) 
        cmbInterest.setSelectedIndex(i);
    }
    txtSubject.setText(voPromotion.getSubject());
    txtOrigin.setText(voPromotion.getOrigin());
    txtAreaContent.setText(voPromotion.getContent());
  }
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
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
    
    listInterest = InterestPartServiceImpl.listVO;
    arrInterest[0] = new String[listInterest.size()];//관심분야코드
    arrInterest[1] = new String[listInterest.size()];//관심분야이름
    for (int i=0; i<listInterest.size(); i++) {
      InterestPartVO vo = (InterestPartVO)listInterest.get(i);
      arrInterest[0][i] = String.valueOf(vo.getPartCode());
      arrInterest[1][i] = vo.getPartName();
    }
    defaultCombo = new DefaultComboBoxModel(arrInterest[1]);
    cmbInterest = new JComboBox();
    cmbInterest.setBounds(105, 305, 218, 30);
    cmbInterest.setBackground(Color.WHITE);
    cmbInterest.setFont(new Font("굴림", Font.PLAIN, 18));
    cmbInterest.setModel(defaultCombo);
    panel.add(cmbInterest);

    
    txtSubject = new JTextField();
    txtSubject.setBounds(105, 339, 862, 25);
    txtSubject.setFont(new Font("굴림", Font.PLAIN, 15));
    panel.add(txtSubject);
    
    txtOrigin = new JTextField();
    txtOrigin.setBounds(105, 367, 862, 25);
    txtOrigin.setFont(new Font("굴림", Font.PLAIN, 15));
    panel.add(txtOrigin);
    
    txtAreaContent = new JTextArea();
    txtAreaContent.setEditable(false);
    txtAreaContent.setEnabled(false);

    //JScrollPane에 JTextArea 올림
    JScrollPane scrollPane = new JScrollPane(txtAreaContent);
    scrollPane.setBounds(105, 396, 862, 218);
    panel.add(scrollPane);
    
    lblMsgBox = new JLabel("");
    lblMsgBox.setForeground(Color.RED);
    lblMsgBox.setFont(new Font("굴림", Font.ITALIC, 15));
    lblMsgBox.setBounds(38, 643, 938, 25);
    panel.add(lblMsgBox);
    
    JButton btnSave = new JButton("저장");
    btnSave.setFont(new Font("굴림", Font.PLAIN, 16));
    btnSave.setBounds(303, 678, 120, 30);
    panel.add(btnSave);
    
    JButton btnDelete = new JButton("삭제");
    btnDelete.setFont(new Font("굴림", Font.PLAIN, 16));
    btnDelete.setBounds(452, 678, 120, 30);
    panel.add(btnDelete);
    
    JButton btnExit = new JButton("닫기");
    btnExit.setFont(new Font("굴림", Font.PLAIN, 16));
    btnExit.setBounds(599, 678, 120, 30);
    panel.add(btnExit);
    
    setVisible(true);

    // 저장버튼 클릭이벤트
    btnSave.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int partCode = ((InterestPartVO)listInterest.get(cmbInterest.getSelectedIndex())).getPartCode();
        if (voPromotion.getPartCode() != partCode) 
          voPromotion.setPartCode(partCode);//관심분야 콤보선택
        
        if (null == voPromotion.getSubject() 
            || 0 == txtSubject.getText().trim().length()) {
          lblMsgBox.setText("제목을 입력하세요");
        } else {
          if (! voPromotion.getSubject().equals(txtSubject.getText())) voPromotion.setSubject(txtSubject.getText());
        }
        
        if (null == voPromotion.getOrigin()
            || 0 == txtOrigin.getText().trim().length()) {
          lblMsgBox.setText("출처를 입력하세요");
        } else {
          if (! voPromotion.getOrigin().equals(txtOrigin.getText())) voPromotion.setOrigin(txtOrigin.getText());
        }
        
        int result = implPromotion.update(voPromotion);
        if (0 < result) lblMsgBox.setText("홍보물을 수정했습니다"); 
        else lblMsgBox.setText("홍보물을 수정할 수 없습니다"); 
      }
    });
    
    //삭제버튼 클릭이벤트
    btnDelete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int result = implPromotion.delete(voPromotion);//delete
        if (0 < result) {
          //삭제메세지
          lblMsgBox.setText("홍보물을 삭제했습니다");
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
  
//  /**
//   * 첨부된 사진을 byte[]로 변경
//   */
//  private byte[] transByteCodeForPicture(String path) {
//    return null;
//  }
}
