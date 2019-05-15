package dao.interfaces;

import domain.LocalOrder;

import java.util.List;

public interface LocalOrderDao extends BaseDao<LocalOrder> {
    List<LocalOrder> getAll(String userId);
}
