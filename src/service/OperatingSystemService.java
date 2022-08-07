package service;

import java.util.List;

import model.OperatingSystem;
import repository.OperatingSystemRepository;

public class OperatingSystemService {


	private final OperatingSystemRepository osRepo = new OperatingSystemRepository();
	
	public List<OperatingSystem> getAll() {
		return osRepo.getAll();
	}

	public OperatingSystem getById(Integer id) {
		return osRepo.getById(id);
	}
}
