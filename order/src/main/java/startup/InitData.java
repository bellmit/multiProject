package startup;

import domain.*;
import service.*;
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

    @Inject
    OrderStatusService oss;

    @Inject
    LocalOrderService los;

    @Inject
    DeliveryOrderService dos;

    @Inject
    AddressService as;


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
            "Eend kebab schotel",
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

    private String[] orderStatus = {
            "Paid",
            "Is being made",
            "Waiting for deliverer",
            "Is being delivered",
            "Done"
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


        //create orderstatusses
        createOrderStatus(orderStatus);

        //create addresses
        Address address = new Address("Rachelmodel", "456A", "Eindhoven", "9191OP");

        //create localOrder


        //create deliveryOrder

    }

    private void addProducts(String[] products, int cat){
        for (String product : products) {
            Product p = new Product(product, cs.find(categories[cat]), types, Double.valueOf(df.format(ThreadLocalRandom.current().nextDouble(priceMin, priceMax))), 9.0);
            ps.create(p);
        }
    }

    private void createOrderStatus(String[] orderStatusses){
        for(String orderStatus : orderStatusses){
            OrderStatus os = new OrderStatus(orderStatus);
            oss.create(os);
        }
    }




}
