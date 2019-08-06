package com.dao.impl;

import com.dao.CodeDao;
import com.model.Code;
import com.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CodeHibDaoImpl implements CodeDao {

    private SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(CodeHibDaoImpl.class);

    @Autowired
    public CodeHibDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Code code) {
        Session session = sessionFactory.getCurrentSession();
        session.save(code);
        logger.info(code + " was added to DB");
    }

    @Override
    public Optional<Code> getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Code code = session.get(Code.class, id);
        return Optional.ofNullable(code);
    }

    @Override
    public Optional<Code> getLastCodeForUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Code where user = :user order by id desc ");
        query.setParameter("user", user);
        query.setMaxResults(1);
        Code code = (Code) query.uniqueResult();
        return Optional.ofNullable(code);
    }
}
