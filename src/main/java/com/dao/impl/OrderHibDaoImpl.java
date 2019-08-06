package com.dao.impl;

import com.dao.OrderDao;
import com.model.Order;
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
public class OrderHibDaoImpl implements OrderDao {

    private SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(OrderHibDaoImpl.class);

    @Autowired
    public OrderHibDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.save(order);
        logger.info(order + " was added to DB");
    }

    @Override
    public Optional<Order> getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Order order = session.get(Order.class, id);
        return Optional.ofNullable(order);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<Order> getLastOrderForUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<Order> query = session.createQuery("from Order where user = :user order by id desc");
        query.setParameter("user", user);
        List list = query.getResultList();
        if (list.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of((Order) list.get(0));
        }
    }
}
