package WindowBuilder.promotion;

import java.util.Vector;

public class RecentPopularPromotionServiceImpl implements RecentPopularPromotionService {
  RecentPopularPromotionDAO dao = new RecentPopularPromotionDAO();
  
  @SuppressWarnings("rawtypes")
  @Override
  public Vector searchList(RecentPopularPromotionVO vo) {
    return dao.searchList(vo);
  }

  @Override
  public RecentPopularPromotionVO search(RecentPopularPromotionVO vo) {
    return dao.search(vo);
  }

  @Override
  public int insert(RecentPopularPromotionVO vo) {
    return dao.insert(vo);
  }

  @Override
  public int update(RecentPopularPromotionVO vo) {
    return dao.update(vo);
  }

  @Override
  public int delete(RecentPopularPromotionVO vo) {
    return dao.delete(vo);
  }
}
