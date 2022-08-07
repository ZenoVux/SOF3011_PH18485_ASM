package repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import model.ScreenType;
import utility.HibernateUtil;

public class ScreenTypeRepository {


	public List<ScreenType> getAll() {
		List<ScreenType> screenTypes = new ArrayList<>();
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Query<ScreenType> query = session.createQuery("FROM ScreenType ct", ScreenType.class);

		screenTypes = query.getResultList();

		session.close();
		return screenTypes;
	}

	public ScreenType getById(Integer id) {
		ScreenType screenType = null;
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();

		screenType = session.find(ScreenType.class, id);

		session.close();
		return screenType;
	}
}
