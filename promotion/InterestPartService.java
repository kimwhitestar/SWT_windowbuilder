package WindowBuilder.promotion;

import java.util.List;

public interface InterestPartService {
  @SuppressWarnings("rawtypes")
  public List search();
  public int insert(InterestPartVO vo);
  public int delete(InterestPartVO vo);
}
