package service;

import java.util.List;

import model.Resolution;
import repository.ResolutionRepository;

public class ResolutionService {


	private final ResolutionRepository resolutionRepo = new ResolutionRepository();
	
	public List<Resolution> getAll() {
		return resolutionRepo.getAll();
	}

	public Resolution getById(Integer id) {
		return resolutionRepo.getById(id);
	}
}
