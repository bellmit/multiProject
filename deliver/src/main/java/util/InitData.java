package util;

import service.interfaces.IDeliveryService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Singleton
@Startup
public class InitData {
    @Inject
    private IDeliveryService deliveryService;

    @PostConstruct
    public void initTestDeliveries() {
        String string = "Dit zijn slechts test strings die als orderId worden gebruikt, normaal zijn dit UUIDs";
        String[] list1 = string.split(" ");

        Set<String> orderIds = new HashSet<>(Arrays.asList(list1));
        deliveryService.addDelivery(orderIds);

        String string2 = "En dit is een andere delivery";
        String[] list2 = string2.split(" ");

        Set<String> orderIds2 = new HashSet<>(Arrays.asList(list2));
        deliveryService.addDelivery(orderIds2);
    }
}