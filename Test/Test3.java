package WindowBuilder.Test;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Test3 extends JFrame {
  private JTextField txtMid;
  private JPasswordField txtPwd;
  private final ButtonGroup btnGroupGender = new ButtonGroup();
  private JTable tblContent;
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Test3() {
    super("SWT ");
    setTitle("SWT 컴포넌트 출력 학습");
    setSize(800, 1000);
    //윈도우 중앙에 화면프레임 출력
    setLocationRelativeTo(null); //화면프레임 가운데 정렬
    setResizable(false); //화면프레임 고정
    
//    //화면레이아웃
//    FlowLayout layout = new FlowLayout();
//    setLayout(layout);

    JPanel pn2 = new JPanel();
    pn2.setBounds(12, 10, 762, 36);
    getContentPane().add(pn2);
    pn2.setLayout(null);
    
    JLabel lblDisplay = new JLabel("출력창");
    lblDisplay.setForeground(Color.RED);
    lblDisplay.setBounds(0, 0, 600, 36);
    pn2.add(lblDisplay);
    
    JButton btnExit = new JButton("종료");
    btnExit.setBounds(682, 0, 80, 36);
    pn2.add(btnExit);
    
    JPanel pn1 = new JPanel();
    pn1.setBackground(Color.LIGHT_GRAY);
    pn1.setBounds(12, 56, 762, 60);
    getContentPane().add(pn1);
    pn1.setLayout(null);
    
    JLabel lblMid = new JLabel("아이디");
    lblMid.setFont(new Font("굴림", Font.PLAIN, 16));
    lblMid.setBounds(14, 9, 70, 34);
    pn1.add(lblMid);
    
    txtMid = new JTextField();
    lblMid.setLabelFor(txtMid);
    txtMid.setFont(new Font("굴림", Font.PLAIN, 16));
    txtMid.setBounds(96, 10, 150, 34);
    pn1.add(txtMid);
    txtMid.setColumns(10);
    
    JLabel lblPwd = new JLabel("비밀번호");
    lblPwd.setBounds(270, 10, 70, 34);
    pn1.add(lblPwd);
    lblPwd.setFont(new Font("굴림", Font.PLAIN, 16));
    
    txtPwd = new JPasswordField();
    lblPwd.setLabelFor(txtPwd);
    txtPwd.setBounds(352, 10, 150, 34);
    pn1.add(txtPwd);
    
    JButton btn1 = new JButton("아이디 출력");
    btn1.setBounds(514, 10, 120, 34);
    pn1.add(btn1);
    
    getContentPane().setLayout(null);
    
    JPanel pn3 = new JPanel();
    pn3.setBounds(12, 120, 762, 60);
    getContentPane().add(pn3);
    pn3.setLayout(null);
    
    JRadioButton rdMale = new JRadioButton("남자");
    btnGroupGender.add(rdMale);
    rdMale.setFont(new Font("굴림", Font.PLAIN, 16));
    rdMale.setBounds(8, 6, 80, 38);
    pn3.add(rdMale);
    
    JRadioButton rdFemale = new JRadioButton("여자");
    rdFemale.setSelected(true);
    btnGroupGender.add(rdFemale);
    rdFemale.setFont(new Font("굴림", Font.PLAIN, 16));
    rdFemale.setBounds(121, 6, 80, 38);
    pn3.add(rdFemale);
    
    JButton btnGender = new JButton("성별 출력");
    btnGender.setFont(new Font("굴림", Font.PLAIN, 16));
    btnGender.setBounds(233, 11, 120, 33);
    pn3.add(btnGender);
    
    JPanel panel = new JPanel();
    panel.setBounds(12, 190, 335, 350);
    getContentPane().add(panel);
    panel.setLayout(null);
    
    JComboBox cmbJob = new JComboBox();
    cmbJob.setModel(new DefaultComboBoxModel(new String[] {"학생", "회사원", "군인", "의사", "변호사", "기타"}));
    cmbJob.setFont(new Font("굴림", Font.PLAIN, 16));
    cmbJob.setBounds(12, 27, 150, 33);
    panel.add(cmbJob);
    
    JButton btnJob = new JButton("직업 출력");
    btnJob.setFont(new Font("굴림", Font.PLAIN, 16));
    btnJob.setBounds(12, 70, 150, 33);
    panel.add(btnJob);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(12, 113, 150, 227);
    panel.add(scrollPane);
    
    JList list = new JList();
    list.setModel(new AbstractListModel() {
      String[] values = new String[] {"학생", "회사원", "공무원", "가사", "변호사", "의사", "군인", "자영업", "프로그래머", "프리랜서", "은행원", "기타"};
      public int getSize() {
        return values.length;
      }
      public Object getElementAt(int index) {
        return values[index];
      }
    });
    scrollPane.setViewportView(list);
    
    JScrollPane scrollPane_1 = new JScrollPane();
    scrollPane_1.setBounds(174, 115, 150, 225);
    panel.add(scrollPane_1);
    
    JTextArea txtAreaContent = new JTextArea();
    scrollPane_1.setViewportView(txtAreaContent);
    
    JButton btnJob2 = new JButton("직업 출력2");
    btnJob2.setFont(new Font("굴림", Font.PLAIN, 16));
    btnJob2.setBounds(174, 71, 150, 33);
    panel.add(btnJob2);
    
    JPanel pn5 = new JPanel();
    pn5.setBounds(359, 190, 335, 350);
    getContentPane().add(pn5);
    pn5.setLayout(null);
    
    JCheckBox chkCard1 = new JCheckBox("국민카드");
    chkCard1.setBounds(34, 40, 107, 23);
    pn5.add(chkCard1);
    
    JCheckBox chkCard2 = new JCheckBox("새마을금고카드");
    chkCard2.setBounds(176, 40, 107, 23);
    pn5.add(chkCard2);
    
    JCheckBox chkCard3 = new JCheckBox("신한카드");
    chkCard3.setBounds(34, 86, 107, 23);
    pn5.add(chkCard3);
    
    JCheckBox chkCard4 = new JCheckBox("축협카드");
    chkCard4.setBounds(176, 86, 107, 23);
    pn5.add(chkCard4);
    
    JCheckBox chkCard5 = new JCheckBox("신협카드");
    chkCard5.setBounds(34, 136, 107, 23);
    pn5.add(chkCard5);
    
    JCheckBox chkCard6 = new JCheckBox("우리카드");
    chkCard6.setBounds(176, 136, 107, 23);
    pn5.add(chkCard6);
    
    JButton btnCard = new JButton("선택 카드 출력");
    btnCard.setFont(new Font("굴림", Font.PLAIN, 16));
    btnCard.setBounds(34, 294, 150, 33);
    pn5.add(btnCard);
    
    JPanel pn6 = new JPanel();
    pn6.setBounds(12, 552, 762, 199);
    getContentPane().add(pn6);
    pn6.setLayout(null);
    
    
    /* JTable을 디자인탭에 올리는 순서?
       1. Panel-> absolute layout -> JScrollPane -> ViewPort에 JTable
       2. DB연결 DefaultTableModel(Vector형식자료)객체 준비
       3. 준비된 Vector자료를 DefaultTableModel객체에 올린다
       4. DefaultTableModel객체를 JTable에 올린다
       5. JTable을 JScrollPane에 올린다
       6. JScrollPane을 패널에 올린다
     */
    
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true); //화면프레임 표시

    //출력버튼클릭 이벤트
    btn1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String mid = txtMid.getText();
        String pwd = txtPwd.getText();//password component라는 것을 알리는 밑줄
        if(mid.equals("") || pwd.equals("")) {
          JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 입력하세요");
          if(mid.equals("")) txtMid.requestFocus();//커서위치
          else txtPwd.requestFocus();//커서위치
        }
        else {
          lblDisplay.setText("아이디 : "+mid+", 비밀번호 : "+pwd);
        }
      }
    });
    //성별출력버튼클릭 이벤트
    btnGender.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(rdMale.isSelected()) lblDisplay.setText("남자선택");
        else lblDisplay.setText("여자선택");
      }
    });
    //직업출력버튼클릭 이벤트
    btnJob.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        lblDisplay.setText(cmbJob.getSelectedItem().toString());
      }
    });
    //직업출력버튼2클릭 이벤트
    btnJob2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //선택된 직업을 textArea로 출력
        //txtAreaContent.setText(cmbJob.getSelectedItem().toString());
        txtAreaContent.append(cmbJob.getSelectedItem().toString()+"\n");
      }
    });
    //선택카드출력버튼클릭 이벤트
    btnCard.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String card = "";
        if(chkCard1.isSelected()) card = card + chkCard1.getText() + "/";
        if(chkCard2.isSelected()) card = card + chkCard1.getText() + "/";
        if(chkCard3.isSelected()) card = card + chkCard1.getText() + "/";
        if(chkCard4.isSelected()) card = card + chkCard1.getText() + "/";
        if(chkCard5.isSelected()) card = card + chkCard1.getText() + "/";
        if(chkCard6.isSelected()) card = card + chkCard1.getText() + "/";
        lblDisplay.setText("선택카드 : " + card);
      }
    });
    //JTable
    
    //JTable Title-Vector로 처리
    Vector title = new Vector();//Vector<>제너릭이 안된다고 함(선생님)
    title.add("번호");
    title.add("성명");
    title.add("나이");
    title.add("성별");
    title.add("입사일");
    
    //데이터를 벡터로 입력
    Test3DAO dao = new Test3DAO();
    Vector vData = dao.getList();
    System.out.println("vData : " + vData);

    //모델에 벡터자료를 담는다
    DefaultTableModel defaultTableModel = new DefaultTableModel();
    
    //DefaultTableModel을 JTable에 올린다
    tblContent = new JTable(defaultTableModel);
    //spContent.setViewportView(tblContent);

    //JTable을 JScrollPane에 올린다
    JScrollPane spContent = new JScrollPane(tblContent);
    spContent.setBounds(0, 0, 762, 189);
    
    //JScrollPane을 Panel에 올린다
    pn6.add(spContent);
    
    
    //종료버튼클릭 이벤트
    btnExit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
  }
  public static void main(String[] args) {
    new Test3();
  }
}
