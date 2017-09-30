import cst.zju.edu.alexsun.Dao.IUserDao;
import cst.zju.edu.alexsun.Dao.UserDao;
import cst.zju.edu.alexsun.model.User;
import cst.zju.edu.alexsun.util.EntitiesHelp;
import cst.zju.edu.alexsun.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserDaoTest {
     private IUserDao userDao;
     private Session session = null;

     @Before
    public void setUp() throws Exception{
        userDao = new UserDao();
        session = HibernateUtil.openSession();
        session = HibernateUtil.openSession();
        session.beginTransaction();
        session.save(new User(1,"admin","Ningbo"));
        session.getTransaction().commit();
     }

     @Test
     public void testGetUserByName() throws Exception{
        User tu = userDao.getUserByName("admin");
         EntitiesHelp.assertUser(tu);
     }

     @Test
    public void testLoad() throws Exception{

    }

    @Test
    public void testAdd() throws Exception{
        User user = new User(2,"Elder","Yangzhou");
        userDao.addUser(user);
    }

    @After
    public void tearDown() throws Exception{

    }
}
