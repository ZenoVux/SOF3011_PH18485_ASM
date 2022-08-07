package service;


import model.Account;
import repository.AccountRepository;

public class AccountService {
	
	private final AccountRepository accountRepo = new AccountRepository();
	
	public boolean createAccount(Account account) {
		try {
			accountRepo.create(account);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Account getByUsername(String username) {
		return accountRepo.getByUsername(username);
	}
}
