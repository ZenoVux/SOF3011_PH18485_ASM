package service;

import java.util.List;

import model.ScreenType;
import repository.ScreenTypeRepository;

public class ScreenTypeService {


	private final ScreenTypeRepository screenTypeRepo = new ScreenTypeRepository();
	
	public List<ScreenType> getAll() {
		return screenTypeRepo.getAll();
	}

	public ScreenType getById(Integer id) {
		return screenTypeRepo.getById(id);
	}
}
