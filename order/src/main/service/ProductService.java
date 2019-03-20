package service;

import dao.interfaces.ProductDao;
import domain.Product;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.UUID;

@Stateless
public class ProductService {
    @Inject
    private ProductDao pd;

    public void create(Product a){
        pd.create(a);
    }

    public Product find(UUID id){
        return pd.find(id);
    }

    public void edit(Product a){
        pd.edit(a);
    }

    public void delete(Product a){
        pd.delete(a);
    }
}
