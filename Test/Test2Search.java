package WindowBuilder.Test;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Test2Search extends JFrame {
  private JTextField txtName;
  private JTextField txtAge;
  private final ButtonGroup btnGroupGender = new ButtonGroup();
  Test2DAO dao = new Test2DAO();
  Test2VO vo = new Test2VO();
  private JTextField txtGender;
  private JTextField txtJoinday;
  private JTextField txtSearch;
  
  public Test2Search(Test2VO vo) {
    this();
    this.vo = vo;
    txtSearch.setText(String.valueOf(vo.getIdx()));
    txtName.setText(vo.getName());
    txtAge.setText(String.valueOf(vo.getAge()));
    txtGender.setText(vo.getGender());
    txtJoinday.setText(vo.getJoinday());
  }
  public Test2Search() {
    super("SWT 개별 회원 검색 학습");
    setSize(500, 500);
    //윈도우 중앙에 화면프레임 출력
    setLocationRelativeTo(null); //화면프레임 가운데 정렬
    setResizable(false);

    JPanel panel = new JPanel();
    panel.setBounds(0, 0, 600, 400);
    getContentPane().add(panel);
    panel.setLayout(null);
    
    JLabel lblNewLabel = new JLabel("개별 회원 조회 결과");
    panel.add(lblNewLabel);

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
    txtName.setEditable(false);
    txtName.setBounds(90, 101, 256, 27);
    txtName.setFont(new Font("굴림", Font.PLAIN, 16));
    panel.add(txtName);
    txtName.setColumns(10);
    
    txtAge = new JTextField();
    txtAge.setEditable(false);
    txtAge.setBounds(90, 143, 256, 27);
    lblAge.setLabelFor(txtAge);
    txtAge.setFont(new Font("굴림", Font.PLAIN, 16));
    txtAge.setColumns(10);
    panel.add(txtAge);
    
    String[] yy = new String[30];//년
    String[] mm = new String[12];//월
    String[] dd = new String[31];//일
    
    for(int i=0; i<=22; i++) yy[i] = String.valueOf(i + 2000);
    for(int i=0; i<12; i++) mm[i] = String.valueOf(i + 1);
    for(int i=0; i<31; i++) dd[i] = String.valueOf(i + 1);
    
    JButton btnClose = new JButton("닫기");
    btnClose.setBounds(152, 362, 91, 23);
    btnClose.setFont(new Font("굴림", Font.PLAIN, 16));
    panel.add(btnClose);
    
    JLabel lblTestSearch = new JLabel("회 원 조 회 폼");
    lblTestSearch.setBounds(90, 10, 256, 48);
    lblTestSearch.setFont(new Font("굴림", Font.PLAIN, 28));  
    panel.add(lblTestSearch);
    
    txtGender = new JTextField();
    txtGender.setFont(new Font("굴림", Font.PLAIN, 16));
    txtGender.setEditable(false);
    txtGender.setColumns(10);
    txtGender.setBounds(90, 191, 256, 27);
    panel.add(txtGender);
    
    txtJoinday = new JTextField();
    txtJoinday.setFont(new Font("굴림", Font.PLAIN, 16));
    txtJoinday.setEditable(false);
    txtJoinday.setColumns(10);
    txtJoinday.setBounds(90, 232, 256, 27);
    panel.add(txtJoinday);
    
    JLabel lblMsg = new JLabel("조회할 고유번호를 입력하세요");
    lblMsg.setBounds(12, 278, 342, 29);
    panel.add(lblMsg);
    
    txtSearch = new JTextField();
    txtSearch.setBounds(12, 304, 218, 27);
    panel.add(txtSearch);
    txtSearch.setColumns(10);
    
    JButton btnUpdate = new JButton("수정");
    btnUpdate.setBounds(345, 306, 91, 23);
    panel.add(btnUpdate);
    
    JButton btnNewButton_1 = new JButton("조회");
    btnNewButton_1.setBounds(242, 306, 91, 23);
    panel.add(btnNewButton_1);

    setVisible(true);

    //수정버튼 클릭
    
    //조회버튼 클릭
    btnUpdate.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String search = txtSearch.getText();
        if(search.trim().equals("")) {
          JOptionPane.showMessageDialog(null, "고유번호를 입력하세요");
          txtSearch.requestFocus();
        } else {
          if(!Pattern.matches("^[0-9]*$", search)) {
            JOptionPane.showMessageDialog(null, "고유번호는 숫자를 입력하세요");
            txtSearch.setText("");
            txtSearch.requestFocus();
          } else {
            //고유번호 검색
            Test2VO vo = dao.getSearch(Integer.parseInt(search));
            if(0 == vo.getIdx()) {
              JOptionPane.showMessageDialog(null, "검색한 고유번호가 없습니다. 다시 검색하세요");
              txtSearch.requestFocus();
            } else {
              txtName.setText(vo.getName());
              txtAge.setText(String.valueOf(vo.getAge()));
              txtGender.setText(vo.getGender());
              txtJoinday.setText(vo.getJoinday());
            }
          }          
        }
      }
    });
    //창닫기클릭 이벤트
    btnClose.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();//main창 위에 sub창에서 회원가입창이 열렸으므로, sub창 현재창만 닫기
      }
    });
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  public static void main(String[] args) {
    new Test2Search();
  }
}
