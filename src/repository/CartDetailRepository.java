package repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Brand;
import model.CartDetail;
import utility.HibernateUtil;

public class CartDetailRepository {

	public void create(CartDetail cartDetail) {
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		try {
			session.save(cartDetail);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		session.close();
	}

	public void update(CartDetail cartDetail) {
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(cartDetail);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new RuntimeException(e);
		}
		session.close();
	}

	public CartDetail getById(Integer id) {
		CartDetail cartDetail = null;
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		cartDetail = session.find(CartDetail.class, id);
		session.close();
		return cartDetail;
	}
	
	public CartDetail getByCartIdAndTiviId(Integer cartId, Integer tiviId) {
		CartDetail cartDetail = null;
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Query<CartDetail> query = session.createQuery("FROM CartDetail cd WHERE cd.cart.id = :cartId AND cd.tivi.id = :tiviId", CartDetail.class);
		query.setParameter("cartId", cartId);
		query.setParameter("tiviId", tiviId);
		query.setMaxResults(1);
		List<CartDetail> cartDetails = query.getResultList();
		if (cartDetails.size() > 0) {
			cartDetail = cartDetails.get(0); 
		}
		session.close();
		return cartDetail;
	}

	public List<CartDetail> getByCartId(Integer cartId) {
		List<CartDetail> cartDetails = new ArrayList<>();
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Query<CartDetail> query = session.createQuery("FROM CartDetail cd WHERE cd.cart.id = :cartId AND cd.deleted=false", CartDetail.class);
		query.setParameter("cartId", cartId);
		cartDetails = query.getResultList();

		session.close();
		return cartDetails;
	}
}
