package service;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import model.Tivi;
import repository.TiviRepository;

public class TiviService {

	private final TiviRepository tiviRepo = new TiviRepository();

	public List<Tivi> getAll() {
		return tiviRepo.getAll();
	}

	public Tivi getById(Integer id) {
		return tiviRepo.getById(id);
	}
	
	public int getCount() {
		return tiviRepo.getCount();
	}
	public int getCountByFilter(String name, BigDecimal priceMin, BigDecimal priceMax, Boolean deleted) {
		return tiviRepo.getCountByFilter(name, priceMin, priceMax, deleted);
	}
	
	public List<Tivi> getByFilter(String name, String quantity, BigDecimal priceMin, BigDecimal priceMax, Boolean deleted, int first, int max) {
		return tiviRepo.getByFilter(name, quantity, priceMin, priceMax, deleted, first, max);
	}
	
	public List<Tivi> searchByPage(int page, int limit) {
		return tiviRepo.searchByPage(page * limit, limit);
	}

	public boolean createTivi(Tivi tivi) {
		try {
			tiviRepo.create(tivi);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateTivi(Tivi tivi) {
		try {
			tiviRepo.update(tivi);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean deleteTivi(Tivi tivi) {
		try {
			tiviRepo.update(tivi);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
