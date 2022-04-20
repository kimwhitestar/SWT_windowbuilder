package WindowBuilder.promotion;

import java.util.List;

public interface InterestPartService {
  public List<InterestPartVO> search();
  public int insert(InterestPartVO vo);
  public int delete(InterestPartVO vo);
}
