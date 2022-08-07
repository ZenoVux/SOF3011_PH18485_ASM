package repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import model.Brand;
import utility.HibernateUtil;

public class BrandRepository {

	public List<Brand> getAll() {
		List<Brand> brands = new ArrayList<>();
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Query<Brand> query = session.createQuery("FROM Brand b", Brand.class);

		brands = query.getResultList();

		session.close();
		return brands;
	}

	public Brand getById(Integer id) {
		Brand brand = null;
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();

		brand = session.find(Brand.class, id);

		session.close();
		return brand;
	}

}
