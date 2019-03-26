package startup;

import domain.Product;
import service.ProductService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class InitData {
    @Inject
    ProductService ps;



    @PostConstruct
    public void init(){

        Product p = new Product();
        p.setName("nigga");

        ps.create(p);



//        em = emf.createEntityManager();
//        tx = em.getTransaction();
//
//        pd = new ProductDaoJpa();
//        pd.setEm(em);
//
//        tx.begin();
//
//        Product p = new Product();
//        p.setName("nigga");
//        pd.create(p);
//        tx.commit();
    }
}
