package com.dao.impl;

import com.dao.UserDao;
import com.model.User;
import com.util.HashUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserHibDaoImpl implements UserDao {

    private SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(UserHibDaoImpl.class);

    @Autowired
    public UserHibDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        Session session = sessionFactory.getCurrentSession();
        String saltedPassword = HashUtil.getSaltedPassword(user.getPassword(), user.getSalt());
        user.setPassword(saltedPassword);
        user.setConfirmPassword(saltedPassword);
        session.save(user);
        logger.info(user + " was added to DB");
    }

    @Override
    public void remove(Long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        if (user != null) {
            session.delete(user);
            logger.info(user + " was deleted from DB");
        }
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        String saltedPassword = HashUtil.getSaltedPassword(user.getPassword(), user.getSalt());
        user.setPassword(saltedPassword);
        user.setConfirmPassword(saltedPassword);
        session.update(user);
        logger.info(user + " was updated in DB");
    }

    @Override
    public Optional<User> getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> getByLogin(String login) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where login = :login");
        query.setParameter("login", login);
        User user = (User) query.uniqueResult();
        return Optional.ofNullable(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM User").list();
    }

    @Override
    public Optional<User> getByLoginOrEmail(String login, String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where login = :login OR email = :email");
        query.setParameter("login", login);
        query.setParameter("email", email);
        query.setMaxResults(1);
        User user = (User) query.uniqueResult();
        return Optional.ofNullable(user);
    }
}
