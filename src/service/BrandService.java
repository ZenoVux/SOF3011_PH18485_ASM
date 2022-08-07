package service;

import java.util.List;

import model.Brand;
import repository.BrandRepository;

public class BrandService {

	private final BrandRepository brandRepo = new BrandRepository();
	
	public List<Brand> getAll() {
		return brandRepo.getAll();
	}

	public Brand getById(Integer id) {
		return brandRepo.getById(id);
	}
}
