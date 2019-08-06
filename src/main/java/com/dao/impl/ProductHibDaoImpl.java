package com.dao.impl;

import com.dao.ProductDao;
import com.model.Product;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductHibDaoImpl implements ProductDao {

    private SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(ProductHibDaoImpl.class);

    @Autowired
    public ProductHibDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.save(product);
        logger.info(product + " was added to DB");
    }

    @Override
    public void remove(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);
        if (product != null) {
            session.delete(product);
            logger.info(product + " was deleted from DB");
        }
    }

    @Override
    public void update(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.update(product);
        logger.info(product + " was updated in DB");
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Product").list();
    }

    @Override
    public Optional<Product> getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);
        return Optional.ofNullable(product);
    }
}
