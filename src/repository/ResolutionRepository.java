package repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import model.Resolution;
import model.Tivi;
import utility.HibernateUtil;

public class ResolutionRepository {


	public List<Resolution> getAll() {
		List<Resolution> resolutions = new ArrayList<>();
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Query<Resolution> query = session.createQuery("FROM Resolution r", Resolution.class);

		resolutions = query.getResultList();

		session.close();
		return resolutions;
	}

	public Resolution getById(Integer id) {
		Resolution resolution = null;
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();

		resolution = session.find(Resolution.class, id);

		session.close();
		return resolution;
	}
}
