package com.dao.impl;

import com.dao.BasketDao;
import com.model.Basket;
import com.model.Product;
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
public class BasketHibDaoImpl implements BasketDao {

    private SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(BasketHibDaoImpl.class);

    @Autowired
    public BasketHibDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Basket basket) {
        Session session = sessionFactory.getCurrentSession();
        session.save(basket);
        logger.info(basket + " was added to DB");
    }

    @Override
    public void addProduct(Basket basket, Product product) {
        Session session = sessionFactory.getCurrentSession();
        basket.getProductList().add(product);
        session.update(basket);
        logger.info("Added " + product + " in basket " + basket);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<Basket> getBasketByUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<Basket> query = session.createQuery(
                "from Basket where user = :user order by id desc");
        query.setParameter("user", user);
        List list = query.getResultList();
        if (list.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of((Basket) list.get(0));
        }
    }
}
