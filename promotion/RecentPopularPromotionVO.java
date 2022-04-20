package WindowBuilder.promotion;

public class RecentPopularPromotionVO {
  /** 생성일 */
  private String createDate = null;//yyyyMMddhhmms 13자리
  /** 생성일 From */
  private String createDateFrom = null;//yyyyMMddhhmms 13자리
  /** 생성일 To */
  private String createDateTo = null;//yyyyMMddhhmms 13자리
//  /** 관심분야VO */
//  private List<InterestPartVO> partVO = null;
  /** 관심분야 코드 */
  private int partCode;
  /** 관심분야 이름 */
  private String partName = null;
  /** 제목 */
  private String subject = null;
  /** 출처 */
  private String origin = null;
  /** 내용 */
  private String content = null;
  /** 사진(크기 조정) */
  private byte[] picture = null;
  /** 원본 사진 */
  private byte[] pictureOrg = null;
  
  public String getCreateDate() {
    return createDate;
  }
  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }
  public String getCreateDateFrom() {
    return createDateFrom;
  }
  public void setCreateDateFrom(String createDateFrom) {
    this.createDateFrom = createDateFrom;
  }
  public String getCreateDateTo() {
    return createDateTo;
  }
  public void setCreateDateTo(String createDateTo) {
    this.createDateTo = createDateTo;
  }
//  public List<InterestPartVO> getPartVO() {
//    return partVO;
//  }
//  public void setPartVO(List<InterestPartVO> partVO) {
//    this.partVO = partVO;
//  }
  public int getPartCode() {
    return partCode;
  }
  public void setPartCode(int partCode) {
    this.partCode = partCode;
  }
  public String getPartName() {
    return partName;
  }
  public void setPartName(String partName) {
    this.partName = partName;
  }
  public String getSubject() {
    return subject;
  }
  public void setSubject(String subject) {
    this.subject = subject;
  }
  public String getOrigin() {
    return origin;
  }
  public void setOrigin(String origin) {
    this.origin = origin;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public byte[] getPicture() {
    return picture;
  }
  public void setPicture(byte[] picture) {
    this.picture = picture;
  }
  public byte[] getPictureOrg() {
    return pictureOrg;
  }
  public void setPictureOrg(byte[] pictureOrg) {
    this.pictureOrg = pictureOrg;
  }
}