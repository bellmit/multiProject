import dao.interfaces.CouponDao;
import dao.jpa.CouponDaoJpa;
import domain.Coupon;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import service.CouponService;
import util.CouponType;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.transaction.UserTransaction;

@RunWith(Arquillian.class)
public class CouponIT {
    @Inject
    CouponService cs;

    @Resource
    private UserTransaction utx;
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClass(CouponService.class)
                .addClass(Coupon.class)
                .addClass(CouponDao.class)
                .addClass(CouponDaoJpa.class)
                .addClass(CouponType.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void SimpleTest(){
        Assert.assertEquals("","");
    }

}
