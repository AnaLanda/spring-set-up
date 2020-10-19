package spring.setup.dao.impl;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.setup.dao.UserDao;
import spring.setup.model.User;

@Repository
public class UserDaoImpl implements UserDao {
    private Logger log;
    private SessionFactory sessionFactory;

    public UserDaoImpl(Logger log, SessionFactory sessionFactory) {
        this.log = log;
        this.sessionFactory = sessionFactory;
    }

    public void add(User user) {
        Transaction transaction = null;
        Session session = null;
        log.info("Trying to add the user " + user + " to the DB.");
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            log.info("Successfully added the user " + user + "to the DB.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to add the user "
                    + user + " to the DB.", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<User> findAllUsers() {
        log.info("Trying to get all users from the DB.");
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            List<User> users = query.getResultList();
            users.forEach(log::info);
            return users;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get a list of all users.", e);
        }
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setLogger(Logger log) {
        this.log = log;
    }
}
