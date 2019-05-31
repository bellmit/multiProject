package dao.interfaces;

import domain.Ping;

public interface PingDao {
    Ping create(Ping object);
    void delete(Ping object);
}