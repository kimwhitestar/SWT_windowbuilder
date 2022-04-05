package WindowBuilder.Test;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Test2Input extends JFrame {
  private JTextField txtName;
  private JTextField txtAge;
  private final ButtonGroup btnGroupGender = new ButtonGroup();
  Test2DAO dao = new Test2DAO();
  Test2VO vo = new Test2VO();
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Test2Input() {
    super("SWT 회원관리 메인화면 학습");
    setSize(800, 600);
    
    //윈도우 중앙에 화면프레임 출력
    setLocationRelativeTo(null); //화면프레임 가운데 정렬
    setResizable(false);
    getContentPane().setLayout(null);

    JPanel panel = new JPanel();
    panel.setBounds(0, 0, 800, 500);
    getContentPane().add(panel);
    panel.setLayout(null);
    
    JLabel lblSubject = new JLabel("회 원 가 입 폼");
    lblSubject.setFont(new Font("굴림", Font.PLAIN, 28));
    lblSubject.setHorizontalAlignment(SwingConstants.CENTER);
    lblSubject.setBounds(0, 2, 700, 80);
    panel.add(lblSubject);
    
//    JLabel lblNewLabel_1 = new JLabel("New label");
//    URL imgUrl = this.getClass().getClassLoader().getResource("2.jpg");
//    lblNewLabel_1.setIcon(new ImageIcon(imgUrl));
//    lblNewLabel_1.setBounds(48, 129, 683, 313);
//    panel.add(lblNewLabel_1);
    
    JLabel lblName = new JLabel("성명");
    lblName.setBounds(12, 92, 76, 36);
    lblName.setFont(new Font("굴림", Font.PLAIN, 16));
    lblName.setLabelFor(txtName);
    panel.add(lblName);
    
    JLabel lblAge = new JLabel("나이");
    lblAge.setBounds(12, 138, 76, 36);
    lblAge.setFont(new Font("굴림", Font.PLAIN, 16));
    panel.add(lblAge);
    
    JLabel lblGender = new JLabel("성별");
    lblGender.setBounds(12, 186, 76, 36);
    lblGender.setFont(new Font("굴림", Font.PLAIN, 16));
    panel.add(lblGender);
    
    JLabel lblJoinday = new JLabel("입사일");
    lblJoinday.setBounds(12, 232, 76, 36);
    lblJoinday.setFont(new Font("굴림", Font.PLAIN, 16));
    panel.add(lblJoinday);
    
    txtName = new JTextField();
    txtName.setBounds(90, 101, 256, 27);
    txtName.setFont(new Font("굴림", Font.PLAIN, 16));
    panel.add(txtName);
    txtName.setColumns(10);
    
    txtAge = new JTextField();
    txtAge.setBounds(90, 143, 256, 27);
    lblAge.setLabelFor(txtAge);
    txtAge.setFont(new Font("굴림", Font.PLAIN, 16));
    txtAge.setColumns(10);
    panel.add(txtAge);
    
    JRadioButton rdbtnMale = new JRadioButton("남자");
    rdbtnMale.setBounds(90, 194, 113, 23);
    btnGroupGender.add(rdbtnMale);
    panel.add(rdbtnMale);
    
    JRadioButton rdbtnFemale = new JRadioButton("여자");
    rdbtnFemale.setBounds(233, 194, 113, 23);
    lblGender.setLabelFor(rdbtnFemale);
    rdbtnFemale.setSelected(true);
    btnGroupGender.add(rdbtnFemale);
    panel.add(rdbtnFemale);
    
    String[] yy = new String[30];//년
    String[] mm = new String[12];//월
    String[] dd = new String[31];//일
    
    for(int i=0; i<=22; i++) yy[i] = String.valueOf(i + 2000);
    for(int i=0; i<12; i++) mm[i] = String.valueOf(i + 1);
    for(int i=0; i<31; i++) dd[i] = String.valueOf(i + 1);
    
    JComboBox cmbJoindayY = new JComboBox(yy);
    cmbJoindayY.setBounds(93, 240, 110, 28);
    lblJoinday.setLabelFor(cmbJoindayY);
    cmbJoindayY.setFont(new Font("굴림", Font.PLAIN, 16));
    panel.add(cmbJoindayY);
    
    JComboBox cmbJoindayM = new JComboBox(mm);
    cmbJoindayM.setBounds(225, 240, 110, 28);
    cmbJoindayM.setFont(new Font("굴림", Font.PLAIN, 16));
    panel.add(cmbJoindayM);
    
    JComboBox cmbJoindayD = new JComboBox(dd);
    cmbJoindayD.setBounds(359, 240, 110, 28);
    cmbJoindayD.setFont(new Font("굴림", Font.PLAIN, 16));
    panel.add(cmbJoindayD);
    
    JButton btnInput = new JButton("가입");
    btnInput.setBounds(35, 317, 91, 23);
    btnInput.setFont(new Font("굴림", Font.PLAIN, 16));
    panel.add(btnInput);
    
    JButton btnExit = new JButton("닫기");
    btnExit.setBounds(273, 317, 91, 23);
    btnExit.setFont(new Font("굴림", Font.PLAIN, 16));
    panel.add(btnExit);
    
    JButton btnCancel = new JButton("취소");
    btnCancel.setBounds(153, 318, 91, 23);
    btnCancel.setFont(new Font("굴림", Font.PLAIN, 16));
    panel.add(btnCancel);
    
    setVisible(true);
    
    //가입버튼클릭 이벤트
    btnInput.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String name = txtName.getText();
        String age = txtAge.getText();
        String gender = "";
        String joindayY = cmbJoindayY.getSelectedItem().toString();
        String joindayM = cmbJoindayM.getSelectedItem().toString();
        String joindayD = cmbJoindayD.getSelectedItem().toString();
        String joinday = joindayY + "-" + joindayM + "-" + joindayD;
        if(name.trim().equals("")) {
          JOptionPane.showMessageDialog(null, "성명을 입력하세요");
          txtName.requestFocus();
        } else if(age.trim().equals("")) {
          JOptionPane.showMessageDialog(null, "나이을 입력하세요");
          txtAge.requestFocus();
        } else {
          if(!Pattern.matches("^[0-9]*$", age)) {
            JOptionPane.showMessageDialog(null, "나이는 숫자를 입력하세요");
            txtAge.requestFocus();
          } else {
            if(rdbtnMale.isSelected()) gender = "남자";
            else gender = "여자";
            
            //가입처리
            vo.setName(name);
            vo.setAge(Integer.parseInt(age));
            vo.setGender(gender);
            vo.setJoinday(joinday);

            dao.Test2Input(vo);
            JOptionPane.showMessageDialog(null, "회원가입되었습니다");
          
            txtName.setText("");
            txtAge.setText("");
            txtName.requestFocus();
          }
        }
      }
    });
    //취소버튼클릭 이벤트
    btnCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        txtName.setText("");
        txtAge.setText("");
        txtName.requestFocus();//커서위치
      }
    });
    //창닫기클릭 이벤트
    btnExit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();//main창 위에 sub창에서 회원가입창이 열렸으므로, sub창 현재창만 닫기
        //System.exit(0);
      }
    });
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  public static void main(String[] args) {
    new Test2Input();
  }
}
