package repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Cart;
import utility.HibernateUtil;

public class CartRepository {
	
	public void create(Cart cart) {
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		try {
			session.save(cart);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		session.close();
	}
	

	public void update(Cart cart) {
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(cart);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new RuntimeException(e);
		}
		session.close();
	}

	public Cart getById(Integer id) {
		Cart cart = null;
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		cart = session.find(Cart.class, id);
		session.close();
		return cart;
	}
	
	public Cart getLastByAccountId(Integer accountId) {
		Cart cart = null;
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Query<Cart> query = session.createQuery("FROM Cart c WHERE c.account.id = :accountId ORDER BY c.id", Cart.class);
		query.setParameter("accountId", accountId);
		query.setMaxResults(1);
		List<Cart> carts = query.getResultList();
		if (carts.size() > 0) {
			cart = carts.get(0);
		}
		session.close();
		return cart;
	}

}
