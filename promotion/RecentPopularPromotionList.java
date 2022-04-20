package WindowBuilder.promotion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class RecentPopularPromotionList extends JFrame {
  @SuppressWarnings("rawtypes")
  private JComboBox cmbInterest;
  private InterestPartServiceImpl implInterest;
  private List<InterestPartVO> listInterest;
  private String[][] arrInterest = {{},{}};;
  private JTextField txtSubjectOROrigin;
  private JTable tblPromotion;
  private RecentPopularPromotionServiceImpl implPromotion;
  private RecentPopularPromotionVO vo;
  @SuppressWarnings("rawtypes")
  private Vector title;//테이블 title
  @SuppressWarnings("rawtypes")
  private Vector vData;//테이블 조회 data
  private List<RecentPopularPromotionVO> updateOrDeleteList;//테이블 update or delete data
  private JTextField txtFromDate;
  private JTextField txtToDate;

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public RecentPopularPromotionList() {
    super("SWT 최신 소식 홍보물 목록 조회 - Sub");
    setSize(1024, 768);
    setLocationRelativeTo(null);
    setResizable(false);

    getContentPane().setLayout(null);
    
    JPanel panel = new JPanel();
    panel.setBounds(0, 0, 1010, 725);
    getContentPane().add(panel);
    panel.setLayout(null);
    
    JLabel lblTitle = new JLabel("최신 소식 홍보물 목록");
    lblTitle.setBounds(12, 10, 985, 80);
    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
    lblTitle.setFont(new Font("굴림", Font.BOLD, 25));
    panel.add(lblTitle);
    
    JLabel lblInterest = new JLabel("관심분야");
    lblInterest.setBounds(12, 92, 88, 31);
    lblInterest.setFont(new Font("굴림", Font.PLAIN, 18));
    panel.add(lblInterest);
    
    implInterest = new InterestPartServiceImpl();
    listInterest = implInterest.search();

    arrInterest[0] = new String[listInterest.size()+1];//관심분야코드
    arrInterest[1] = new String[listInterest.size()+1];//관심분야이름
    arrInterest[0][0] = "";
    arrInterest[1][0] = "-관심 분야-";
    for (int i=1; i<=listInterest.size(); i++) {
      InterestPartVO vo = (InterestPartVO)listInterest.get(i-1);
      arrInterest[0][i] = String.valueOf(vo.getPartCode());
      arrInterest[1][i] = vo.getPartName();
    }
    
    cmbInterest = new JComboBox();
    cmbInterest.setBounds(105, 89, 218, 37);
    cmbInterest.setModel(new DefaultComboBoxModel(arrInterest[1]));
    cmbInterest.setBackground(Color.WHITE);
    cmbInterest.setFont(new Font("굴림", Font.PLAIN, 18));
    panel.add(cmbInterest);
    
    JLabel lblCreateDate = new JLabel("작성일 (예: 2022-01-01)");
    lblCreateDate.setFont(new Font("굴림", Font.PLAIN, 18));
    lblCreateDate.setBounds(378, 92, 218, 31);
    panel.add(lblCreateDate);
    
    txtFromDate = new JTextField();
    txtFromDate.setFont(new Font("굴림", Font.PLAIN, 18));
    txtFromDate.setColumns(10);
    txtFromDate.setBounds(594, 88, 176, 38);
    panel.add(txtFromDate);
    
    JLabel lblTermSimbol = new JLabel("~");
    lblTermSimbol.setFont(new Font("굴림", Font.PLAIN, 18));
    lblTermSimbol.setBounds(770, 88, 50, 37);
    lblTermSimbol.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(lblTermSimbol);
    
    txtToDate = new JTextField();
    txtToDate.setFont(new Font("굴림", Font.PLAIN, 18));
    txtToDate.setColumns(10);
    txtToDate.setBounds(821, 88, 176, 38);
    panel.add(txtToDate);
    
    JComboBox cmbSubOROrg = new JComboBox();
    cmbSubOROrg.setBounds(12, 143, 78, 38);
    cmbSubOROrg.setModel(new DefaultComboBoxModel(new String[] {"제목", "출처"}));
    cmbSubOROrg.setBackground(Color.WHITE);
    cmbSubOROrg.setForeground(Color.BLACK);
    cmbSubOROrg.setFont(new Font("굴림", Font.PLAIN, 18));
    panel.add(cmbSubOROrg);
    
    txtSubjectOROrigin = new JTextField();
    txtSubjectOROrigin.setBounds(105, 143, 541, 38);
    txtSubjectOROrigin.setFont(new Font("굴림", Font.PLAIN, 18));
    panel.add(txtSubjectOROrigin);
    txtSubjectOROrigin.setColumns(10);

    JButton btnSearch = new JButton("조회");
    btnSearch.setFont(new Font("굴림", Font.PLAIN, 18));
    btnSearch.setBounds(658, 143, 80, 38);
    panel.add(btnSearch);
    
    JButton btnUpdate = new JButton("수정");
    btnUpdate.setFont(new Font("굴림", Font.PLAIN, 18));
    btnUpdate.setBounds(744, 143, 80, 38);
    panel.add(btnUpdate);
    
    JButton btnDelete = new JButton("삭제");
    btnDelete.setFont(new Font("굴림", Font.PLAIN, 18));
    btnDelete.setBounds(831, 143, 80, 38);
    panel.add(btnDelete);
    
    JButton btnExit = new JButton("닫기");
    btnExit.setFont(new Font("굴림", Font.PLAIN, 18));
    btnExit.setBounds(917, 143, 80, 38);
    panel.add(btnExit);
    
    //Empty Table 
    //타이틀 Vector타입 
    title = new Vector();
    title.add("관심분야");
    title.add("제목");
    title.add("출처");
    title.add("작성일");
    title.add("내용");
    //DefaultTableModel에 검색된 Vector List 올림
    DefaultTableModel defaultTableModel = new DefaultTableModel(null, title);
    //JTable에 DefaultTableModel 올림
    tblPromotion = new JTable(defaultTableModel);
    tblPromotion.setCellSelectionEnabled(true);
    tblPromotion.setColumnSelectionAllowed(true);
    tblPromotion.setFont(new Font("굴림", Font.PLAIN, 18));
    
    //JScrollPane에 JTable 올림
    JScrollPane scrollPane = new JScrollPane(tblPromotion);
    scrollPane.setBounds(12, 228, 985, 500);
    panel.add(scrollPane);

    JLabel lblMsgBox = new JLabel("조회 조건을 입력하세요");
    lblMsgBox.setForeground(Color.RED);
    lblMsgBox.setFont(new Font("굴림", Font.ITALIC, 18));
    lblMsgBox.setBounds(22, 189, 980, 38);
    panel.add(lblMsgBox);
    
    setVisible(true);
    
    implPromotion = new RecentPopularPromotionServiceImpl();
    updateOrDeleteList = new ArrayList<>();//update list
    
    // 조회버튼 클릭 이벤트
    btnSearch.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        vo = new RecentPopularPromotionVO();//Search VO
        
        if (0 < cmbInterest.getSelectedIndex())
          vo.setPartCode(((InterestPartVO)listInterest.get(cmbInterest.getSelectedIndex()-1)).getPartCode());
        if (0 < txtFromDate.getText().trim().length())
          vo.setCreateDateFrom(txtFromDate.getText());
        if (0 < txtToDate.getText().trim().length())
          vo.setCreateDateTo(txtToDate.getText());
        String srtSubjectOROrigin = (String)cmbSubOROrg.getSelectedItem();
        if (0 < txtSubjectOROrigin.getText().trim().length()) {
          if ("제목".equals(srtSubjectOROrigin)) 
            vo.setSubject(txtSubjectOROrigin.getText());
          else if ("출처".equals(srtSubjectOROrigin))
            vo.setOrigin(txtSubjectOROrigin.getText());
        }

        //데이타 Vector타입
        vData = implPromotion.searchList(vo);
        for (int i=0; i<vData.size(); i++) {
          for (int j=1; j<arrInterest[0].length; j++) {
            //조회된 데이타에서 관심분야를 코드에서 이름으로 변경
            if (arrInterest[0][j].equals(String.valueOf(((Vector)vData.get(i)).get(0)))) {//관심분야코드
              ((Vector)vData.get(i)).set(0, arrInterest[1][j]);//관심분야이름
            }
          }
        }
        //DefaultTableModel에 검색된 Vector List 올림
        defaultTableModel.setDataVector(vData, title);

        JOptionPane.showMessageDialog(null, vData.size() + "건 조회됬습니다");
        lblMsgBox.setText(vData.size() + "건 조회됬습니다");

        //tblPromotion.addColumn(new TableColumn(0, 50, null, null));//checkbox column 추가
        //tblPromotion.setColumnModel(DefaultTableColumnModel);;
      }
    });
//    //######## 테이블에 마우스이벤트가 적용안되요ㅜㅜ
//    //테이블 컬럼 마우스 클릭 이벤트
//    tblPromotion.addMouseListener(new MouseAdapter() {
//      @Override
//      public void mouseClicked(MouseEvent e) {
//System.out.println("##### mouseClicked");  
///* 테이블에서 여러 rows를 선택후 update
//        //마우스클릭 table의 4번째 column 작성일 CREATE_DATE의 값
//        String strCreateDate = defaultTableModel.getValueAt(tblPromotion.getSelectedRow(), 3).toString();
//        String strSubject = defaultTableModel.getValueAt(tblPromotion.getSelectedRow(), 1).toString();
//        String strOrigin = defaultTableModel.getValueAt(tblPromotion.getSelectedRow(), 2).toString();
//        RecentPopularPromotionVO updateVO = new RecentPopularPromotionVO();//update vo
//        updateVO.setCreateDate(strCreateDate);//update vo 조건
//        updateVO.setSubject(strSubject);//update vo 항목 
//        updateVO.setOrigin(strOrigin);//update vo 항목
//        updateOrDeleteList.add(updateVO);//update list
//        
//        //tblContent.getColumnModel().getColumn(3).setMaxWidth(60);
//*/
//      }
//    });
    
    //테이블 컬럼 마우스 클릭 이벤트
    tblPromotion.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        //마우스클릭table row 의 CreateDate값
        String strCreateDate = (String)((Vector)vData.get(tblPromotion.getSelectedRow())).get(3);
//System.out.println("수정할 row의 CreateDate=" + strCreateDate);
        RecentPopularPromotionVO updateVO = new RecentPopularPromotionVO();//update vo
        updateVO.setCreateDate(strCreateDate);//update vo 조건 : 작성일
        //테이블에서 선택한 row에 해당하는 data를 상세화면윈도우창을 띄워 상세조회 후 상세내용표시
        new RecentPopularPromotionSubSearch(updateVO);
      }
    });

    // 수정버튼 클릭 이벤트
    btnUpdate.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
System.out.println("[수정이벤트] 여러건 선택해서 수정하고 싶으나 테이블에 체크박스 추가하는게 어려워 수정이벤트는 작성하지않았음");  
//여러건 선택해서 수정하고 싶으나 테이블에 체크박스 추가하는게 어려워 수정이벤트는 작성하지않았음
//        int result = 0;
//        for (int i=0; i<updateOrDeleteList.size(); i++) {
//          result += implPromotion.update((RecentPopularPromotionVO)updateOrDeleteList.get(i));//update
//        }
//        if (0 < result) {
//          //수정메세지
//          JOptionPane.showMessageDialog(null, result + "건 수정됬습니다");
//          lblMsgBox.setText(result + "건 수정됬습니다");
//        }
      }
    });
    
    // 삭제버튼 클릭 이벤트
    btnDelete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
/*
        //체크박스 삭제
        int result = 0;
        for (int i=0; i<updateOrDeleteList.size(); i++) {
          result += implPromotion.delete((RecentPopularPromotionVO)updateOrDeleteList.get(i));//update
        }
//        if (0 < result) //삭제메세지
*/
        String strCreateDate = (String)((Vector)vData.get(tblPromotion.getSelectedRow())).get(3);
        RecentPopularPromotionVO deleteVO = new RecentPopularPromotionVO();//update vo
        deleteVO.setCreateDate(strCreateDate);//update vo 조건 : 작성일
        int result = implPromotion.delete(deleteVO);//update
        if (0 < result) {
          //삭제메세지
          JOptionPane.showMessageDialog(null, result + "건 삭제됬습니다");
          lblMsgBox.setText(result + "건 삭제됬습니다");
          
          //삭제 후 재조회
          vData = implPromotion.searchList(vo);//데이타 Vector타입
          for (int i=0; i<vData.size(); i++) {
            for (int j=1; j<arrInterest[0].length; j++) {
              //조회된 데이타에서 관심분야를 코드에서 이름으로 변경
              if (arrInterest[0][j].equals(String.valueOf(((Vector)vData.get(i)).get(0)))) {//관심분야코드
                ((Vector)vData.get(i)).set(0, arrInterest[1][j]);//관심분야이름
              }
            }
          }
          //DefaultTableModel에 검색된 Vector List 올림
          defaultTableModel.setDataVector(vData, title);
        }
      }
    });
    
    // X버튼 클릭 이벤트
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
  
  public static void main(String[] args) {
    new RecentPopularPromotionList();
  }
}
