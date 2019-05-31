import dao.interfaces.BaseDao;
import dao.interfaces.CouponDao;
import dao.jpa.BaseDaoJpa;
import dao.jpa.CouponDaoJpa;
import domain.Coupon;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
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
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
                .addClass(BaseDao.class)
                .addClass(BaseDaoJpa.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void SimpleTest(){
        Assert.assertEquals("","");
    }

    @Test
    @RunAsClient
    public void getAllTest(@ArquillianResteasyResource final WebTarget webTarget){
        final Response response = webTarget.path("coupon/new")
                .request(MediaType.APPLICATION_JSON)
                .get();
        int status = response.getStatus();
        Assert.assertNotEquals("",status);
    }

}
