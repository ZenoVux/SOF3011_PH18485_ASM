package repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import model.Account;
import utility.HibernateUtil;

public class AccountRepository {
	
	public List<Account> getAll() {
		List<Account> accounts = new ArrayList<>();
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Query<Account> query = session.createQuery("FROM Account ac", Account.class);
		
		accounts = query.getResultList();
		
		session.close();
		return accounts;
	}

	public Account getById(Integer id) {
		Account account = null;
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		account = session.find(Account.class, id);
		session.close();
		return account;
	}

	public Account getByUsername(String username) {
		List<Account> accounts = getAll();
		for (Account account : accounts) {
			if (account.getUsername().equalsIgnoreCase(username)) {
				return account;
			}
		}
		return null;
	}
	
	public void create(Account account) {
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		try {
			session.save(account);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		session.close();
	}
}
