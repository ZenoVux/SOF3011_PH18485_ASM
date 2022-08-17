package repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Cart;
import model.CartStatus;
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
		Query<Cart> query = session.createQuery(
				"FROM Cart c WHERE c.account.id = :accountId AND c.status = :status ORDER BY c.id DESC", Cart.class);
		query.setParameter("accountId", accountId);
		query.setParameter("status", CartStatus.WAITING);
		query.setMaxResults(1);
		List<Cart> carts = query.getResultList();
		if (carts.size() > 0) {
			cart = carts.get(0);
		}
		session.close();
		return cart;
	}

	public BigDecimal getTotalMoney(Integer cartId) {
		BigDecimal total = null;
		String hql = "SELECT c.id, SUM(tv.price * cd.quantity) AS TotalPrice FROM Tivi tv"
				+ " JOIN CartDetail cd ON tv.id = cd.tivi.id JOIN Cart c ON cd.cart.id = c.id "
				+ " WHERE c.id = :cartId AND cd.deleted=false GROUP BY c.id";
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Query<Object[]> query = session.createQuery(hql, Object[].class);
		query.setParameter("cartId", cartId);
		List<Object[]> objects = query.getResultList();
		if (objects.size() > 0) {
			total = (BigDecimal) objects.get(0)[1];
		}
		session.close();
		return total;
	}

	public List<Cart> getAllByAccountId(Integer accountId) {
		List<Cart> carts = new ArrayList<>();
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Query<Cart> query = session.createQuery("FROM Cart c WHERE c.account.id = :accountId AND c.status = :status1"
				+ " OR c.account.id = :accountId AND c.status = :status2"
				+ " OR c.account.id = :accountId AND c.status = :status3", Cart.class);
		query.setParameter("accountId", accountId);
		query.setParameter("status1", CartStatus.COMFIRMED);
		query.setParameter("status2", CartStatus.COMPLETED);
		query.setParameter("status3", CartStatus.CANCEL);
		carts = query.getResultList();

		session.close();
		return carts;
	}

}
