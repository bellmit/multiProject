package service;

import dao.interfaces.AddressDao;
import domain.Address;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.UUID;

@Stateless
public class AddressService {
    @Inject
    private AddressDao ad;

    public void create(Address a){
        ad.create(a);
    }

    public Address find(UUID id){
        return ad.find(id);
    }

    public void edit(Address a){
        ad.edit(a);
    }

    public void delete(Address a){
        ad.delete(a);
    }
}
