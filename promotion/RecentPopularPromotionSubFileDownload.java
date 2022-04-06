package WindowBuilder.promotion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class RecentPopularPromotionSubFileDownload extends JFrame {

  /**
   * 이미지화일 목록을 담는 컴포넌트 
   */
  @SuppressWarnings("rawtypes")
  private JList listFiles;
  private String directoryImage = "I:/JavaGreen/Java/works/0329_mysqlConnect/src/WindowBuilder/images";
  private String pathSelectedImageFile = new String("");
  
  @SuppressWarnings("rawtypes")
  public RecentPopularPromotionSubFileDownload() {
    super("SWT 최신 소식 홍보물 관리 - Sub");
    setSize(800, 600);
    setLocationRelativeTo(null);
    setResizable(false);

    getContentPane().setLayout(null);
    
    JPanel panel = new JPanel();
    panel.setBounds(0, 0, 788, 588);
    getContentPane().add(panel);
    panel.setLayout(null);
    
    JLabel lblTitle = new JLabel("최신 소식 홍보물 등록 - File Download");
    lblTitle.setBounds(0, 52, 780, 30);
    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
    lblTitle.setFont(new Font("굴림", Font.BOLD, 30));
    panel.add(lblTitle);
    
    JButton btnNew = new JButton("선   택");
    btnNew.setFont(new Font("굴림", Font.PLAIN, 18));
    btnNew.setBounds(333, 452, 120, 38);
    panel.add(btnNew);

    JButton btnSearch = new JButton("조   회");
    btnSearch.setFont(new Font("굴림", Font.PLAIN, 18));
    btnSearch.setBounds(184, 452, 120, 38);
    panel.add(btnSearch);
    
    JButton btnExit = new JButton("닫   기");
    btnExit.setFont(new Font("굴림", Font.PLAIN, 18));
    btnExit.setBounds(483, 452, 120, 38);
    panel.add(btnExit);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(141, 143, 500, 265);
    panel.add(scrollPane);
    
    listFiles = new JList();
    listFiles.setFont(new Font("굴림", Font.PLAIN, 18));
    scrollPane.setViewportView(listFiles);
    
    JLabel lblPathImage = new JLabel("DEFAULT FOLDER> ... /WindowBuilder/images/");
    lblPathImage.setForeground(Color.GRAY);
    lblPathImage.setFont(new Font("굴림", Font.BOLD, 15));
    lblPathImage.setBounds(109, 102, 602, 38);
    panel.add(lblPathImage);
    
    setVisible(true);

    // 조회 버튼 클릭
    btnSearch.addActionListener(new ActionListener() {
      @SuppressWarnings("unchecked")
      public void actionPerformed(ActionEvent e) {
        listFiles.setListData(getListFileName());
      }
    });
    
    // 선택 버튼 클릭
    btnNew.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pathSelectedImageFile = directoryImage + "/" + (String)listFiles.getSelectedValue();
        dispose();
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
  
  /**
   * Image화일의 목록 작성
   * 화일의 확장자가 gif, jpg, jpeg에 해당하는 화일을 추출하여 화일목록을 반환한다
   * @return String[] Image화일목록
   */
  private String[] getListFileName() {
    //directory = "I:/JavaGreen/Java/works/0329_mysqlConnect/src/WindowBuilder/images"
    File dir = new File(this.directoryImage);
    File[] files = dir.listFiles();
    String fileName = null;
    String fileExtention = null;
    String[] extentions = {"gif", "jpg", "jpeg"};
    String[] fileNames = new String[files.length];
    int idx = -1;
    for (File file : files) {
      if (file.isFile()) { 
        fileName = file.getName();
        fileExtention = fileName.substring(fileName.lastIndexOf('.')+1, fileName.length());
        for (String extention : extentions) {
          if (extention.equals(fileExtention)) fileNames[++idx] = fileName;
        }
      }
    }
    return fileNames;
  }
  
  public static void main(String[] args) {
    new RecentPopularPromotionSubFileDownload();
  }
}
