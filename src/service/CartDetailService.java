package service;

import java.util.List;

import model.CartDetail;
import repository.CartDetailRepository;

public class CartDetailService {
	
	private final CartDetailRepository cartDetailRepo = new CartDetailRepository();

	public boolean create(CartDetail cartDetail) {
		try {
			cartDetailRepo.create(cartDetail);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	

	public boolean update(CartDetail cartDetail) {
		try {
			cartDetailRepo.update(cartDetail);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public CartDetail getById(Integer id) {
		return cartDetailRepo.getById(id);
	}
	
	public CartDetail getByCartIdAndTiviId(Integer cartId, Integer tiviId) {
		return cartDetailRepo.getByCartIdAndTiviId(cartId, tiviId);
	}
	

	public List<CartDetail> getByCartId(Integer cartId) {
		return cartDetailRepo.getByCartId(cartId);
	}
}
