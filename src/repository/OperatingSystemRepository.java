package repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import model.OperatingSystem;
import utility.HibernateUtil;

public class OperatingSystemRepository {

	public List<OperatingSystem> getAll() {
		List<OperatingSystem> oss = new ArrayList<>();
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Query<OperatingSystem> query = session.createQuery("FROM OperatingSystem o", OperatingSystem.class);

		oss = query.getResultList();

		session.close();
		return oss;
	}

	public OperatingSystem getById(Integer id) {
		OperatingSystem os = null;
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();

		os = session.find(OperatingSystem.class, id);

		session.close();
		return os;
	}
}
