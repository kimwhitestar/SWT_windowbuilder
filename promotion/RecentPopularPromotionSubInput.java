package WindowBuilder.promotion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class RecentPopularPromotionSubInput extends JFrame {
  @SuppressWarnings("rawtypes")
  private JComboBox cmbInterest;
  private JTextField txtSubject;
  private JTextField txtOrigin;
  private JTextField txtAddImg;
  private RecentPopularPromotionServiceImpl implPromotion;
  private RecentPopularPromotionVO voPromotion;
  private InterestPartServiceImpl implInterest;
  @SuppressWarnings("rawtypes")
  private List listInterest;
  private String[] arrInterest;
  private String directoryImage = "I:/JavaGreen/Java/works/0329_mysqlConnect/src/WindowBuilder/images";
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public RecentPopularPromotionSubInput() {
    super("SWT 최신 소식 홍보물 등록 - Sub");
    setSize(800, 600);
    setLocationRelativeTo(null);
    setResizable(false);

    getContentPane().setLayout(null);
    
    JPanel panel = new JPanel();
    panel.setBounds(0, 0, 780, 580);
    getContentPane().add(panel);
    panel.setLayout(null);
    
    JLabel lblTitle = new JLabel("최신 소식 홍보물 등록");
    lblTitle.setBounds(12, 25, 760, 80);
    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
    lblTitle.setFont(new Font("굴림", Font.BOLD, 25));
    panel.add(lblTitle);
    
    JLabel lblInterest = new JLabel("관심분야");
    lblInterest.setBounds(61, 128, 88, 31);
    lblInterest.setFont(new Font("굴림", Font.PLAIN, 18));
    lblInterest.setLabelFor(cmbInterest);
    panel.add(lblInterest);
    
    JLabel lblSubject = new JLabel("제      목");
    lblSubject.setBounds(61, 192, 88, 31);
    lblSubject.setFont(new Font("굴림", Font.PLAIN, 18));
    lblSubject.setLabelFor(txtSubject);
    panel.add(lblSubject);
    
    JLabel lblOrigin = new JLabel("출      처");
    lblOrigin.setBounds(61, 257, 88, 31);
    lblOrigin.setFont(new Font("굴림", Font.PLAIN, 18));
    lblOrigin.setLabelFor(txtOrigin);
    panel.add(lblOrigin);
    
    JLabel lblPicture = new JLabel("사      진");
    lblPicture.setBounds(60, 321, 88, 31);
    lblPicture.setFont(new Font("굴림", Font.PLAIN, 18));
    lblPicture.setLabelFor(txtAddImg);
    panel.add(lblPicture);
    
    implInterest = new InterestPartServiceImpl();
    listInterest = implInterest.search();
    arrInterest = new String[listInterest.size()];
    for (int i=0; i<listInterest.size(); i++) {
      InterestPartVO vo = (InterestPartVO)listInterest.get(i);
      arrInterest[i] = vo.getPartName();
    }
    System.out.println("arrInterest = " + arrInterest);
    
    cmbInterest = new JComboBox();
    cmbInterest.setModel(new DefaultComboBoxModel(arrInterest));
    cmbInterest.setBounds(160, 125, 250, 37);
    cmbInterest.setBackground(Color.WHITE);
    cmbInterest.setFont(new Font("굴림", Font.PLAIN, 18));
    cmbInterest.setFocusable(true);
    panel.add(cmbInterest);
    
    txtSubject = new JTextField();
    txtSubject.setBounds(161, 190, 560, 38);
    txtSubject.setText("제목을 입력하세요");
    txtSubject.setFont(new Font("굴림", Font.PLAIN, 18));
    panel.add(txtSubject);
    txtSubject.setColumns(10);
    
    txtOrigin = new JTextField();
    txtOrigin.setBounds(161, 255, 560, 38);
    txtOrigin.setText("출처를 입력하세요");
    txtOrigin.setFont(new Font("굴림", Font.PLAIN, 18));
    txtOrigin.setColumns(10);
    panel.add(txtOrigin);
    
    txtAddImg = new JTextField();
    txtAddImg.setBounds(160, 317, 425, 38);
    txtAddImg.setText("사진을 추가하세요");
    txtAddImg.setFont(new Font("굴림", Font.PLAIN, 18));
    txtAddImg.setColumns(10);
    panel.add(txtAddImg);
    
    JButton btnAddImg = new JButton("사진 추가");
    btnAddImg.setFont(new Font("굴림", Font.PLAIN, 18));
    btnAddImg.setBounds(600, 319, 120, 37);
    panel.add(btnAddImg);
    
    JButton btnSave = new JButton("저장");
    btnSave.setFont(new Font("굴림", Font.PLAIN, 18));
    btnSave.setBounds(225, 478, 120, 37);
    panel.add(btnSave);
    
    JButton btnExit = new JButton("닫기");
    btnExit.setFont(new Font("굴림", Font.PLAIN, 18));
    btnExit.setBounds(436, 478, 120, 37);
    panel.add(btnExit);
    
    JLabel lblMsgBox = new JLabel("등록할 홍보물을 입력하세요");
    lblMsgBox.setForeground(Color.RED);
    lblMsgBox.setFont(new Font("굴림", Font.ITALIC, 18));
    lblMsgBox.setBounds(65, 394, 655, 31);
    panel.add(lblMsgBox);
    
    JLabel lblRequired1 = new JLabel("*");
    lblRequired1.setForeground(Color.RED);
    lblRequired1.setFont(new Font("굴림", Font.PLAIN, 25));
    lblRequired1.setBounds(45, 137, 15, 15);
    panel.add(lblRequired1);
    
    JLabel lblRequired2 = new JLabel("*");
    lblRequired2.setForeground(Color.RED);
    lblRequired2.setFont(new Font("굴림", Font.PLAIN, 25));
    lblRequired2.setBounds(45, 201, 15, 15);
    panel.add(lblRequired2);
    
    JLabel lblRequired3 = new JLabel("*");
    lblRequired3.setForeground(Color.RED);
    lblRequired3.setFont(new Font("굴림", Font.PLAIN, 25));
    lblRequired3.setBounds(45, 266, 15, 15);
    panel.add(lblRequired3);
    
    JLabel lblRequired4 = new JLabel("*");
    lblRequired4.setForeground(Color.RED);
    lblRequired4.setFont(new Font("굴림", Font.PLAIN, 25));
    lblRequired4.setBounds(45, 330, 15, 15);
    panel.add(lblRequired4);
    
    setVisible(true);

    implPromotion = new RecentPopularPromotionServiceImpl();
    voPromotion = new RecentPopularPromotionVO();
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
    txtAddImg.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        txtAddImg.setText("");
      }
      @Override
      public void focusLost(FocusEvent e) {
        if (0 == txtAddImg.getText().trim().length()) txtAddImg.setText("사진을 입력하세요");
        //else voPromotion.setPicture(transByteCodeForPicture(txtAddImg.getText()));
      }
    });
    
    // 사진추가 버튼 이벤트
    btnAddImg.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new RecentPopularPromotionSubFileDownload();
        
//인코딩:binary->ascii변환 byte[] bytesPicture = transByteCodeForPicture("I:/JavaGreen/Java/works/0329_mysqlConnect/images/englishAndImage.jpg");
//String strPicture = extractString(decodingBase64(bytesPicture));
        txtAddImg.setText("I:/JavaGreen/Java/works/0329_mysqlConnect/src/WindowBuilder/images/englishAndImage.jpg");
        byte[][] bytesContent = extractBytes(txtAddImg.getText());
        voPromotion.setPicture(bytesContent[1]);//화일전체(사진,문자)
        voPromotion.setContent(extractString(bytesContent[0]));//사진에서 영문자만 String으로 추출
      }
    });
    // 저장버튼 클릭 이벤트
    btnSave.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        voPromotion.setPartCode(((InterestPartVO)listInterest.get(cmbInterest.getSelectedIndex())).getPartCode());//관심분야 콤보선택
        if (null == voPromotion.getSubject()) lblMsgBox.setText("제목은 필수 입력항목입니다");
        else if (null == voPromotion.getOrigin()) lblMsgBox.setText("출처는 필수 입력항목입니다");
        else if (null == voPromotion.getPicture()) lblMsgBox.setText("사진은 필수 입력항목입니다");
        else {
          int result = 0;
          try {
            result = implPromotion.insert(voPromotion);
          } catch(SQLException e2) {
            System.out.println("SQL 에러 : " + e2.getMessage());
            try {
              voPromotion.setContent("사진을 바이너리로 변환 후 문자추출했으나 에러가 났음. input sql 저장 확인을 위해 임의의 값으로 내용설정 후 테스트했음");
              result = implPromotion.insert(voPromotion);
            } catch(SQLException e3) {
              System.out.println("SQL 에러 : " + e3.getMessage());
            }
          }
          if (0 < result) lblMsgBox.setText("홍보물이 등록되었습니다"); 
          else lblMsgBox.setText("홍보물을 등록할 수 없습니다"); 
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
  
  //첨부된 사진을 byte[]로 변경
  /* 참고 웹사이트 : https://lee1535.tistory.com/43 */
//  private byte[] transByteCodeForPicture(String path) {
//    return encodingBase64(extractBytes(path));
//  }
  /* 참고 웹사이트 : https://lee1535.tistory.com/43 */
  @SuppressWarnings("resource")
  private byte[][] extractBytes(String imageName) {
    /*### byte 문자열 비교 테스트*/
    byte test1 = 'F';
    char test2 = 'F';
    if (test1 == test2) System.out.println("byte 'F'와 char 'F'는 같다");
    /* byte 문자열 비교 테스트###*/
    
    byte[] binaryForEnglish = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    byte[] binaryForNumber = {1,2,3,4,5,6,7,8,9,0};
    byte[] binaryForMark = {'~','!','@','#','$','%','^','&','*','(',')','-','+','_','=','`','"',';',':','{','}','[',']','?','/','.',',','<','>','|'};
    byte[][] binaries = {binaryForEnglish, binaryForNumber, binaryForMark};
    byte[] outputEnglish = null;
    byte[] outputPicture = null;
    byte[][] outputBytes = { outputEnglish, outputPicture };
    
    try {
      File imgPath = new File(imageName);
      FileInputStream input = null;
      ByteArrayOutputStream output = null;
      
      /*화일의 사진과 글자를 모두 binary로 변환*/
      input = new FileInputStream(imgPath);
      output = new ByteArrayOutputStream();
      int len = 0, idx = 0;
      byte[] buf = new byte[1024];//사진에서 추출할 문자만 1024문자
      while ((len = input.read(buf)) != -1) {//한번에 배열길이 1024만큼씩의 문자를 읽음, 읽을 문자가 없으면 -1
        output.write(buf, 0, len);//화일의 사진과 글자를 Binary로 작성
      }
      outputPicture = output.toByteArray();
      outputBytes[1] = outputPicture;//화일 전체(사진,글자) binary
      input.close();
      output.close();
      
      /*영문글자 추출*/
      input = new FileInputStream(imgPath);
      output = new ByteArrayOutputStream();
      len = 0; idx = 0;
      buf = new byte[1024];//사진에서 추출할 문자만 1024문자
      while ((len = input.read(buf)) != -1) {//한번에 배열길이 1024만큼씩의 문자를 읽음, 읽을 문자가 없으면 -1
        for (int i=0; i<binaries.length; i++) {
          for (int j=0; j<binaries[i].length; j++) {
            if (buf[idx] == binaries[i][j]) {
              output.write(buf, 0, len);
            }
          }
        }
        idx++;
      }
      outputEnglish = output.toByteArray();
      outputBytes[0] = outputEnglish;//영문자 Binary
      input.close();
      output.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return outputBytes;
  }
  //binary data -> ascii data로 인코딩
  //ascii변환없이 binary만으로도 사용가능하지만, 보안상이나 ajax에서 쓰기도 한다고 함
  /* 참고 웹사이트 : https://lee1535.tistory.com/43 */
//  private byte[] encodingBase64(byte[] targetBytes) {
//    Encoder encoder = Base64.getEncoder();
//    return encoder.encode(targetBytes);
//  }
  @SuppressWarnings("resource")
  private String extractString(byte[] englishBites) {
    StringBuffer strBuff = new StringBuffer("");
    try {
      InputStream inputStream = new ByteArrayInputStream(englishBites);//byte[] outputEnglish
      Reader reader = new InputStreamReader(inputStream);//문자변환보조스트림
      int len = 0, idx = 0;
      char[] cbuf = new char[1024];
      while ((len = reader.read(cbuf)) != -1) {
        strBuff.append(new String(cbuf, 0, len));
      }
System.out.println("new String(cbuf, 0, len); = "+ strBuff.toString());
      inputStream.close();
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return strBuff.toString();
  }
  @SuppressWarnings("resource")
  private void extractFile(byte[] targetBytes) {
    try {
      ByteArrayInputStream input = null;
      FileOutputStream output = new FileOutputStream("I:/JavaGreen/Java/works/0329_mysqlConnect/src/WindowBuilder/images/output.txt");
      
      input = new ByteArrayInputStream(targetBytes, 0, targetBytes.length);
      int len = 0;
      byte[] buf = new byte[1024];//사진에서 추출한 문자만 1024문자
      
      while ((len = input.read(buf)) != -1) {//Binary data 읽기
        output.write(buf, 0, len);//output.txt화일에 쓰기
      }
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  //ascii data -> binary data로 디코딩
//  private byte[] decodingBase64(byte[] targetBytes) {
//    Decoder decoder = Base64.getDecoder();
//    return decoder.decode(targetBytes);
//  }
  
  public static void main(String[] args) {
    new RecentPopularPromotionSubInput();
  }
}
