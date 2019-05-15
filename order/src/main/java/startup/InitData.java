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
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Startup
@Singleton
public class InitData {
    @Inject
    ProductService ps;

    @Inject
    CategoryService cs;

    private final int priceMin = 0;
    private final int priceMax = 20;
    private DecimalFormat df = new DecimalFormat(".##");

    private String[] categories = {
            "Broodjes",
            "Schotel",
            "Drinks",
    };

    private String[] broodjes = {
            "Doner Kebab",
            "Durum",
            "Kapsalon"
    };

    private String[] schotels = {
            "Eend kebab schotel lmfao",
            "Shit schotel",
            "Mix schotel"
    };

    private String[] drinks = {
            "Fanta",
            "Cola",
            "Bier",
            "Koffie",
            "Cassis"
    };

    private List<OrderType> types;
    private List<String[]> cats;

    @PostConstruct
    public void init(){
        types = new ArrayList<>();
        types.add(OrderType.DELIVERY);
        types.add(OrderType.LOCAL);

        cats = new ArrayList<>();
        cats.add(broodjes);
        cats.add(schotels);
        cats.add(drinks);

        //create categories
        for (String category : categories) {
            Category c = new Category();
            c.setName(category);
            cs.create(c);
        }

        //add products for each category
        for(int i = 0; i < cats.size(); i++){
            addProducts(cats.get(i), i);
        }
    }

    private void addProducts(String[] products, int cat){
        for (String product : products) {
            Product p = new Product(product, cs.find(categories[cat]), types, Double.valueOf(df.format(ThreadLocalRandom.current().nextDouble(priceMin, priceMax))), 9.0);
            ps.create(p);
        }
    }
}
