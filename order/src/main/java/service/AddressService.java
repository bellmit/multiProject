package service;

import dao.interfaces.AddressDao;
import domain.Address;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AddressService {
    @Inject
    private AddressDao ad;

    public Address create(Address a){
        return ad.create(a);
    }

    public Address find(String id){
        return ad.find(id);
    }

    public Address edit(Address a){
        return ad.edit(a);
    }

    public void delete(Address a){
        ad.delete(a);
    }
}
