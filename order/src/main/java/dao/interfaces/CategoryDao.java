package dao.interfaces;

import domain.Category;
import domain.DeliveryOrder;

import java.util.List;

public interface CategoryDao extends BaseDao<Category> {
    List<Category> getAll();
}
