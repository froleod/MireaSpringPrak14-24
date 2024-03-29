package com.ldf.springprak14.Service;

import com.ldf.springprak14.Entity.Market;
import com.ldf.springprak14.Entity.Product;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final SessionFactory sessionFactory;
    private Session session;

    @PostConstruct
    public void init() {
        session = sessionFactory.openSession();
    }

    public Product createProduct(Product product){
        session.beginTransaction();
        session.persist(product);
        session.getTransaction().commit();
        return product;
    }

    public List<Product> getAllProducts(){
        return session.createQuery("from Product", Product.class).getResultList();
    }

    public void deleteProduct(Long id){
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        if (product != null) {
            session.detach(product);
        }
        session.getTransaction().commit();
    }

    public Market getMarketByProduct(Long productId) {
        Product product = session.get(Product.class, productId);
        return product != null ? product.getMarket() : null;
    }

    public void addProductToMarket(Long productId, Long marketId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Product product = session.get(Product.class, productId);
            Market market = session.get(Market.class, marketId);
            if (product != null && market != null) {
                market.getProducts().add(product);
                session.merge(market);
            }
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public List filterProducts(String name, Integer price) {
        try {
            Session session = sessionFactory.openSession(); // Получаем объект Session
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder(); // Используем его для получения CriteriaBuilder
            CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
            Root<Product> root = criteriaQuery.from(Product.class);
            List<Predicate> predicates = new ArrayList<>();
            if (name != null) {
                predicates.add(criteriaBuilder.equal(root.get("name"), name));
            }
            if (price != null) {
                predicates.add(criteriaBuilder.equal(root.get("price"), price));
            }

            criteriaQuery.select(root).where(predicates.toArray(new Predicate[0]));
            Query query = session.createQuery(criteriaQuery);
            return query.getResultList();
//            return session.createQuery((CriteriaDelete) criteriaQuery).getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


}