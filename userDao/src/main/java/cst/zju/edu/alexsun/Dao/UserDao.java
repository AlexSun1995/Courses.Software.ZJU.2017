package cst.zju.edu.alexsun.Dao;
import cst.zju.edu.alexsun.model.User;
import cst.zju.edu.alexsun.util.HibernateUtil;
import org.hibernate.Session;

public class UserDao implements IUserDao {
    public void addUser(User user) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            // handle the exception
            throw new RuntimeException(e);
        } finally {
            HibernateUtil.close(session);

        }
    }

    public User getUserByName(String userName) {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.openSession();
            user = (User) session.createQuery("from User where name = ?")
                    .setParameter(0, userName).uniqueResult();

        } catch (Exception e) {
            // handle the exception
            throw new RuntimeException(e);
        } finally {
            HibernateUtil.close(session);
        }
        return user;
    }

    public void delete(User user) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();

            session.beginTransaction();

            session.delete(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            // handle the exception
            throw new RuntimeException(e);
        } finally {
            HibernateUtil.close(session);

        }
    }
}
