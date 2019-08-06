package com.dao.impl;

import com.dao.CodeDao;
import com.model.Code;
import com.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
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
    @SuppressWarnings("unchecked")
    public Optional<Code> getLastCodeForUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<Code> query = session.createQuery("from Code where user = :user order by id desc ");
        query.setParameter("user", user);
        List list = query.getResultList();
        if (list.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of((Code) list.get(0));
        }
    }
}
