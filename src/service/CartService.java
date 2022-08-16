package service;

import java.math.BigDecimal;
import java.util.List;

import model.Cart;
import model.CartStatus;
import repository.CartRepository;

public class CartService {
	
	private final CartRepository cartRepo = new CartRepository();
	
	public boolean create(Cart cart) {
		try {
			cartRepo.create(cart);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean update(Cart cart) {
		try {
			cartRepo.update(cart);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Cart getById(Integer id) {
		return cartRepo.getById(id);
	}


	public Cart getLastByAccountId(Integer accountId) {
		return cartRepo.getLastByAccountId(accountId);
	}
	
	public BigDecimal getTotalMoney(Integer cartId) {
		return cartRepo.getTotalMoney(cartId);
	}

	public List<Cart> getAllByAccountId(Integer accountId) {
		return cartRepo.getAllByAccountId(accountId);
	}
}
