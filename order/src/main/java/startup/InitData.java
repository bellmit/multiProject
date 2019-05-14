package startup;

import domain.Category;
import domain.Product;
import service.CategoryService;
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

    @Inject
    CategoryService cs;

    @PostConstruct
    public void init(){
        String[] categories = {
                "Oon",
                "Twee",
                "Blabla",
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8"
        };

        String[] products = {
                "bla"
        };

        List<OrderType> types = new ArrayList<>();
        types.add(OrderType.DELIVERY);
        types.add(OrderType.LOCAL);

        Product p = new Product("nibba", types, 10.0, 7.0);
        ps.create(p);

        //create categories
        Category c = new Category();
        c.setName("Drinks");
        cs.create(c);

        Category c1 = new Category();
        c1.setName("Broodjes");
        cs.create(c1);


        //test list persistence
        List<Category> cats = new ArrayList<>();

        System.out.println("catsize: " + categories.length);

        for(int i = 0; i < categories.length; i++){

            Category c3 = new Category();
            //c3.setName("cat"+i);
            c3.setName(categories[i]);


            cats.add(c3);
            cs.create(cats.get(i));

        }
    }
}
