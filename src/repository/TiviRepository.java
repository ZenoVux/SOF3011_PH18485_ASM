package repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Tivi;
import utility.HibernateUtil;

public class TiviRepository {

	public List<Tivi> getAll() {
		List<Tivi> tivis = new ArrayList<>();
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Query<Tivi> query = session.createQuery("FROM Tivi tv", Tivi.class);

		tivis = query.getResultList();

		session.close();
		return tivis;
	}

	public Tivi getById(Integer id) {
		Tivi tivi = null;
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();

		tivi = session.find(Tivi.class, id);

		session.close();
		return tivi;
	}
	
	public List<Tivi> getByFilter(String name, String quantity, BigDecimal priceMin, BigDecimal priceMax, Boolean deleted, int first, int max) {
		String hql = "FROM Tivi tv WHERE tv.name LIKE :name";
		if (priceMin != null && priceMax != null) {
			hql += " AND tv.price BETWEEN :priceMin AND :priceMax";
		}
		if (deleted != null) {
			hql += " AND tv.deleted = :deleted";
		}
		if (quantity != null) {
			if (quantity.equalsIgnoreCase("DESC")) {
				hql += " ORDER BY tv.quantity DESC";
			}
			if (quantity.equalsIgnoreCase("ASC")) {
				hql += " ORDER BY tv.quantity ASC";
			}
		}
		List<Tivi> tivis = new ArrayList<>();
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Query<Tivi> query = session.createQuery(hql, Tivi.class);
		
		query.setParameter("name", "%" + name + "%");
		if (priceMin != null && priceMax != null) {
			query.setParameter("priceMin", priceMin);
			query.setParameter("priceMax", priceMax);
		}
		if (deleted != null) {
			query.setParameter("deleted", deleted);
		}
		query.setFirstResult(first);
		query.setMaxResults(max);
		tivis = query.getResultList();
		session.close();
		return tivis;
	}
	
	public int getCountByFilter(String name, BigDecimal priceMin, BigDecimal priceMax, Boolean deleted) {
		String hql = "SELECT COUNT(*) FROM Tivi tv WHERE tv.name LIKE :name";
		if (priceMin != null && priceMax != null) {
			hql += " AND tv.price BETWEEN :priceMin AND :priceMax";
		}
		if (deleted != null) {
			hql += " AND tv.deleted = :deleted";
		}
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", "%" + name + "%");
		if (priceMin != null && priceMax != null) {
			query.setParameter("priceMin", priceMin);
			query.setParameter("priceMax", priceMax);
		}
		if (deleted != null) {
			query.setParameter("deleted", deleted);
		}
		int count = ((Long) query.uniqueResult()).intValue();
		session.close();
		return count;
	}
	
	public List<Tivi> getByFilterIndex(String name, String quantity, BigDecimal priceMin, BigDecimal priceMax, Boolean deleted, int first, int max) {
		String hql = "FROM Tivi tv WHERE tv.name LIKE :name";
		if (priceMin != null && priceMax != null) {
			hql += " AND tv.price BETWEEN :priceMin AND :priceMax";
		}
		if (deleted != null) {
			hql += " AND tv.deleted = :deleted";
		}
		hql +=  " AND tv.quantity > 0";
		if (quantity != null) {
			if (quantity.equalsIgnoreCase("DESC")) {
				hql += " ORDER BY tv.quantity DESC";
			}
			if (quantity.equalsIgnoreCase("ASC")) {
				hql += " ORDER BY tv.quantity ASC";
			}
		}
		List<Tivi> tivis = new ArrayList<>();
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Query<Tivi> query = session.createQuery(hql, Tivi.class);
		
		query.setParameter("name", "%" + name + "%");
		if (priceMin != null && priceMax != null) {
			query.setParameter("priceMin", priceMin);
			query.setParameter("priceMax", priceMax);
		}
		if (deleted != null) {
			query.setParameter("deleted", deleted);
		}
		query.setFirstResult(first);
		query.setMaxResults(max);
		tivis = query.getResultList();
		session.close();
		return tivis;
	}
	
	public int getCountByFilterIndex(String name, BigDecimal priceMin, BigDecimal priceMax, Boolean deleted) {
		String hql = "SELECT COUNT(*) FROM Tivi tv WHERE tv.name LIKE :name";
		if (priceMin != null && priceMax != null) {
			hql += " AND tv.price BETWEEN :priceMin AND :priceMax";
		}
		if (deleted != null) {
			hql += " AND tv.deleted = :deleted";
		}
		hql +=  " AND tv.quantity > 0";
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", "%" + name + "%");
		if (priceMin != null && priceMax != null) {
			query.setParameter("priceMin", priceMin);
			query.setParameter("priceMax", priceMax);
		}
		if (deleted != null) {
			query.setParameter("deleted", deleted);
		}
		int count = ((Long) query.uniqueResult()).intValue();
		session.close();
		return count;
	}

	public int getCount() {
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Query query = session.createQuery("SELECT COUNT(*) FROM Tivi tv");
		int count = ((Long) query.uniqueResult()).intValue();
		session.close();
		return count;
	}

	public List<Tivi> searchByPage(int first, int max) {
		List<Tivi> tivis = new ArrayList<>();
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Query<Tivi> query = session.createQuery("FROM Tivi tv ORDER BY tv.id", Tivi.class);
		query.setFirstResult(first);
		query.setMaxResults(max);

		tivis = query.getResultList();

		session.close();
		return tivis;
	}

	public void create(Tivi tivi) {
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		try {
			session.save(tivi);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		session.close();
	}

	public void update(Tivi tivi) {
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(tivi);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new RuntimeException(e);
		}
		session.close();
	}

	public void delete(Tivi tivi) {
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(tivi);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new RuntimeException(e);
		}
		session.close();
	}
}
