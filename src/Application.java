import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Account;
import model.Cart;
import model.Tivi;
import repository.TiviRepository;
import service.AccountService;
import service.CartDetailService;
import service.CartService;
import service.TiviService;
import utility.HibernateUtil;

public class Application {

	private static final AccountService accountService = new AccountService();
	private static final CartService cartService = new CartService();
	private static final CartDetailService cartDetailService = new CartDetailService();

	public static void main(String[] args) {
//		Account account = new Account();
//		account.setId(1);
//		account.setUsername("ph18485");
//		account.setPassword("ph18485");
//		account.setFullname("Vu Van Luan");
//		account.setRole(true);
//		
//		Tivi tivi = new Tivi();
//		tivi.setName("Samsung 55");
//		tivi.setCreateUser(account);

//		SessionFactory factory = HibernateUtil.getFACTORY();
//		Session session = factory.openSession();
//		Transaction transaction = session.beginTransaction();
//		session.save(tivi);
//		transaction.commit();
//		session.close();

//		SessionFactory factory = HibernateUtil.getFACTORY();
//		Session session = factory.openSession();
//		Tivi tivi = session.find(Tivi.class, 1);
//		System.out.println(tivi.getName() + " - " + tivi.getCreateUser().getFullname());
//		session.close();
//		String password = "zzAz1zz@";
//		if (!password.matches("[^A-Z]+")) {
//			System.out.println("Không chứa ký tự viết hoa");
//			return;
//		}
//		// 
//		if (password.matches("[a-z0-9]+")) {
//			System.out.println("Phải chứa ký tự đặc biệt");
//			return;
//		}
//		String name = "55";
//		String quantity = "";
//		Boolean deleted = true;
//		String hql = "FROM Tivi tv WHERE tv.name LIKE :name";
//		if (deleted != null) {
//			hql += " AND tv.deleted = :deleted";
//		}
//		if (quantity != null) {
//			if (quantity.equals("DESC")) {
//				hql += " ORDER BY tv.quantity DESC";
//			}
//			if (quantity.equals("ASC")) {
//				hql += " ORDER BY tv.quantity ASC";
//			}
//		}
//		List<Tivi> tivis = new ArrayList<>();
//		SessionFactory factory = HibernateUtil.getFACTORY();
//		Session session = factory.openSession();
//		Query<Tivi> query = session.createQuery(hql, Tivi.class);
//		query.setParameter("name", "%" + name + "%");
//		if (deleted != null) {
//			query.setParameter("deleted", deleted);
//		}
//		query.setFirstResult(0);
//		query.setMaxResults(10);
//		tivis = query.getResultList();
//		session.close();
//
//		for (Tivi tivi : tivis) {
//			System.out.println(tivi);
//		}
		
//		System.out.println(tiviService.getCountByFilter("", null, 14000000.0, 15000000.0, null));

//		Account account = accountService.getByUsername("admin");
//		Cart cart = new Cart();
//		cart.setAccount(account);
//		cart.setCompleted(false);
//		cartService.create(cart);
		
//		Cart cart = cartService.getLastByAccountId(1);
//		System.out.println(cart.getId());
		
//		cartDetailService.getByCartIdAndTiviId(1, 1);
		System.out.println(cartService.getTotalMoney(1));
	}

}
