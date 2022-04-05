package WindowBuilder.Test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Test2List extends JFrame {
//  private final ButtonGroup btnGroupGender = new ButtonGroup();
  Test2DAO dao = new Test2DAO();
  Test2VO vo = new Test2VO();
  @SuppressWarnings("rawtypes")
  Vector vData = new Vector();
  private JTextField txtSearch;
  private JTable table;
  
  @SuppressWarnings({ "rawtypes", "unchecked", "unused" })
  public Test2List() {
    super("SWT 전체 회원 검색 학습");
    setSize(800, 800);
    //윈도우 중앙에 화면프레임 출력
    setLocationRelativeTo(null); //화면프레임 가운데 정렬
    setResizable(false);

    JPanel panel = new JPanel();
    panel.setBounds(0, 0, 600, 400);
    getContentPane().add(panel);
    panel.setLayout(null);
    
    JPanel pn1 = new JPanel();
    pn1.setBounds(27, 31, 638, 55);
    panel.add(pn1);
    pn1.setLayout(null);
    
    JComboBox cmbSearch = new JComboBox();
    cmbSearch.setModel(new DefaultComboBoxModel(new String[] {"성명", "나이", "성별", "입사일"}));
    cmbSearch.setBounds(12, 10, 91, 35);
    pn1.add(cmbSearch);
    
    txtSearch = new JTextField();
    txtSearch.setBounds(115, 10, 168, 35);
    pn1.add(txtSearch);
    txtSearch.setColumns(10);
    
    JButton btnSearch = new JButton("조건조회");
    btnSearch.setBounds(295, 10, 103, 35);
    pn1.add(btnSearch);
    
    JButton btnList = new JButton("전체조회");
    btnList.setBounds(410, 10, 103, 35);
    pn1.add(btnList);
    
    JButton btnExit = new JButton("닫기");
    btnExit.setBounds(523, 10, 103, 35);
    pn1.add(btnExit);
    
    JPanel pnContent = new JPanel();
    pnContent.setBounds(27, 156, 637, 311);
    panel.add(pnContent);
    pnContent.setLayout(null);

    //타이틀 Vector타입 
    Vector title = new Vector();
    title.add("번호");
    title.add("성명");
    title.add("나이");
    title.add("성별");
    title.add("입사일");

    //데이타 Vector타입
    vData = dao.getList();
    
    //DefaultTableModel에 검색된 Vector List 올림
    DefaultTableModel defaultTableModel = new DefaultTableModel(vData, title);

    //JTable에 DefaultTableModel 올림
    table = new JTable(defaultTableModel);
    table.setColumnSelectionAllowed(true);
//  scrollPane.setViewportView(table);
    
    //JScrollPane에 JTable 올림
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBounds(12, 10, 613, 291);
    
    //Panel에 JScrollPane올림
    pnContent.add(scrollPane);
    
    String[] yy = new String[30];//년
    String[] mm = new String[12];//월
    String[] dd = new String[31];//일
    
    for(int i=0; i<=22; i++) yy[i] = String.valueOf(i + 2000);
    for(int i=0; i<12; i++) mm[i] = String.valueOf(i + 1);
    for(int i=0; i<31; i++) dd[i] = String.valueOf(i + 1);

    setVisible(true);

    //테이블 컬럼 마우스 클릭
    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        //마우스클릭table column 의 idx값
        String clickedIdx = defaultTableModel.getValueAt(table.getSelectedRow(), 0).toString();
        Test2VO vData = dao.getSearch(Integer.parseInt(clickedIdx));
        Test2Search search = new Test2Search(vData);
      }
    });

    //조건검색버튼클릭 이벤트
    btnSearch.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String searchItem = cmbSearch.getSelectedItem().toString();
        String txtStr = txtSearch.getText();
        if(txtStr.trim().equals("")) {
          JOptionPane.showMessageDialog(null, "검색할 내용을 입력하세요");
          txtSearch.requestFocus();
          return;
        }
        if(searchItem.equals("성명")) {
          vData = dao.getSearch("name", txtStr);
        }
        else if(searchItem.equals("나이")) {
          if(!Pattern.matches("^[0-9]*$", txtStr)) {
            JOptionPane.showMessageDialog(null, "나이는 숫자를 입력하세요");
            txtSearch.requestFocus();
          } else {
            vData = dao.getSearch("age", txtStr);
          }
        }
        else if(searchItem.equals("성별")) {
          vData = dao.getSearch("gender", txtStr);
        }
        else if(searchItem.equals("입사일")) {
          vData = dao.getSearch("joinday", txtStr);
        }
        if(0 == vData.size()) {
          JOptionPane.showMessageDialog(null, "검색된 자료가 없습니다");
        }
        else {
          defaultTableModel.setDataVector(vData, title);
        }
      }
    });
    //전체조회
    btnList.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        vData = dao.getList();
        defaultTableModel.setDataVector(vData, title);
      }
    });
    //닫기버튼클릭 이벤트
    btnExit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
    //X아이콘
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  public static void main(String[] args) {
    new Test2List();
  }
}
