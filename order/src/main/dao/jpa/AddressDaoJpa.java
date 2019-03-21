package dao.jpa;

import dao.interfaces.AddressDao;
import domain.Address;

import javax.ejb.Stateless;

@Stateless
public class AddressDaoJpa extends BaseDaoJpa<Address> implements AddressDao {
    public AddressDaoJpa() {
        super(Address.class);
    }
}
