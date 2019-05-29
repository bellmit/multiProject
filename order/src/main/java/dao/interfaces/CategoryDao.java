package dao.interfaces;

import domain.Category;

import java.util.List;

public interface CategoryDao extends BaseDao<Category> {
    List<Category> getAll();
}
