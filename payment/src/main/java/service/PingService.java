package service;

import javax.ejb.Stateless;

@Stateless
public class PingService {

    public boolean ping() {
        return true;
    }
}
