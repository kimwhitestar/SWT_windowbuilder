package WindowBuilder.promotion;

import java.util.List;

public class InterestPartServiceImpl implements InterestPartService {
  private InterestPartDAO dao = new InterestPartDAO();
  public static List<InterestPartVO> listVO;
  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List search() {
    listVO = dao.search();
    return listVO;
  }

  @Override
  public int insert(InterestPartVO vo) {
    return dao.insert(vo);
  }

  @Override
  public int delete(InterestPartVO vo) {
    return dao.delete(vo);
  }
}