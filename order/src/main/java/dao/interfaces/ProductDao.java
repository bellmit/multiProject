package dao.interfaces;

import domain.Product;

import java.util.List;

public interface ProductDao extends BaseDao<Product>{
    List<Product> getAll();
    List<Product> getAllLocalProducts();
    List<Product> getAllDeliveryProducts();
}
