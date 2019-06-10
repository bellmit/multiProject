package service;

import dao.interfaces.LocalOrderDao;
import domain.LocalOrder;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class LocalOrderService {
    @Inject
    private LocalOrderDao ld;

    public LocalOrder create(LocalOrder a) {
        return ld.create(a);
    }

    public LocalOrder find(String id) {
        return ld.find(id);
    }

    public LocalOrder edit(LocalOrder a) {
        return ld.edit(a);
    }

    public void delete(LocalOrder a) {
        LocalOrder b = ld.find(a.getId());
        ld.delete(a);
    }

    public List<LocalOrder> getAll(String userId) {
        return ld.getAll(userId);
    }
}
