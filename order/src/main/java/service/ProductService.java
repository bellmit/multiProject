package service;

import dao.interfaces.ProductDao;
import domain.Product;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ProductService {
    @Inject
    private ProductDao pd;

    public Product create(Product a){
        return pd.create(a);
    }

    public Product find(String id){
        return pd.find(id);
    }

    public Product edit(Product a){
        return pd.edit(a);
    }

    public void delete(Product a){
        pd.delete(a);
    }

    public List<Product> getAll(){
        return pd.getAll();
    }
}
