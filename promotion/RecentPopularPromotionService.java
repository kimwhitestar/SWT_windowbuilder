package WindowBuilder.promotion;

import java.sql.SQLException;
import java.util.Vector;

public interface RecentPopularPromotionService {
  @SuppressWarnings("rawtypes")
  public Vector searchList(RecentPopularPromotionVO vo);
  public RecentPopularPromotionVO search(RecentPopularPromotionVO vo);
  public int insert(RecentPopularPromotionVO vo) throws SQLException;
  public int update(RecentPopularPromotionVO vo);
  public int delete(RecentPopularPromotionVO vo);
}
