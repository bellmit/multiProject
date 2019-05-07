package startup;

import domain.Product;
import service.ProductService;
import util.OrderType;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Startup
@Singleton
public class InitData {
    @Inject
    ProductService ps;

    @PostConstruct
    public void init(){
        List<OrderType> types = new ArrayList<>();
        types.add(OrderType.DELIVERY);
        types.add(OrderType.LOCAL);

        Product p = new Product("nibba", types, 10.0, 7.0);
        ps.create(p);



    }
}
