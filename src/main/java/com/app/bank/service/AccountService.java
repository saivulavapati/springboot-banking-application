package com.app.bank.service;

import java.util.List;

import com.app.bank.dto.AccountDto;

public interface AccountService {
	
	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposit(Long id, Double amount);
	
	AccountDto withdrawl(Long id, Double amount);
	
	List<AccountDto> getAllAccounts();
	
	void deleteAccountById(Long id);

}
