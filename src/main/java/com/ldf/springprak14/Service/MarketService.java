package com.ldf.springprak14.Service;

import com.ldf.springprak14.Entity.Market;
import com.ldf.springprak14.Entity.Product;
import com.ldf.springprak14.Repo.MarketRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketService {
    private final SessionFactory sessionFactory;
    private Session session;

    @PostConstruct
    public void init() {
        session = sessionFactory.openSession();
    }

    public Market createMarket(Market market){
        session.beginTransaction();
        session.save(market);
        session.getTransaction().commit();
        return market;
    }

    public List<Market> getAllMarkets(){
        return session.createQuery("from Market", Market.class).getResultList();
    }

    public void deleteMarket(Long id){
        session.beginTransaction();
        Market market = session.get(Market.class, id);
        if (market != null) {
            session.detach(market);
        }
        session.getTransaction().commit();
    }

    public List<Product> getProductsByMarket(Long marketId) {
        Market market = session.get(Market.class, marketId);
        return market != null ? market.getProducts() : null;
    }

    public void addProductToMarket(Long productId, Long marketId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Product product = session.get(Product.class, productId);
            Market market = session.get(Market.class, marketId);
            if (product != null && market != null) {
                product.setMarket(market);
                session.merge(product);
            }
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
