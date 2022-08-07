package service;

import model.Account;
import repository.AccountRepository;

public class AuthenticationService {
	
	private final AccountRepository accountRepo;
	
	public AuthenticationService() {
		accountRepo = new AccountRepository();
	}
	
	public Account authenticate(String username, String password) {
		// Kiểm tra tài khoản tồn tại
		Account account = accountRepo.getByUsername(username);
		if (account == null) {  // NULL tức là không tồn tại
			return null; // Không hợp lệ
		}
		// Kiểm tra mật khẩu trùng khớp
		if (!account.getPassword().equals(password)) {
			return null; // Không hợp lệ
		}
		return account; // Hợp lệ
	}
	
	public boolean checkUsername(String username) {
		return accountRepo.getByUsername(username) != null;
	}
}
