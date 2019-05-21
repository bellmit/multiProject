package dao.jpa;

import dao.interfaces.AddressDao;
import domain.Address;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AddressDaoJpa extends BaseDaoJpa<Address> implements AddressDao {

    @PersistenceContext
    private EntityManager em;

    public AddressDaoJpa() {
        super(Address.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
