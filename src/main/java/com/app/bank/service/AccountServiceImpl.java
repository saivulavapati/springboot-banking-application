package com.app.bank.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.bank.dto.AccountDto;
import com.app.bank.entity.Account;
import com.app.bank.mapper.AccountMapper;
import com.app.bank.repository.AccountRespository;

@Service
public class AccountServiceImpl implements AccountService {
	
	private AccountRespository accountRespository;
	
	public AccountServiceImpl(AccountRespository accountRespository) {
		this.accountRespository = accountRespository;
	}



	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRespository.save(account);
		AccountDto savedAccountDto = AccountMapper.mapToAccoountDto(savedAccount);
		return savedAccountDto;
	}



	@Override
	public AccountDto getAccountById(Long id) {
		Account account= accountRespository.findById(id).orElseThrow(()-> new RuntimeException("Account doesn't Exist"));
		AccountDto accountDto = AccountMapper.mapToAccoountDto(account);
		return accountDto;
	}



	@Override
	public AccountDto deposit(Long id, Double amount) {
		Account account = accountRespository.findById(id).orElseThrow(()-> new RuntimeException("Account doesn't find with Id: "+id));
		double balance = account.getBalance();
		account.setBalance(balance+amount);
		Account updatedAccount = accountRespository.save(account);
		AccountDto uppdatedAccountDto = AccountMapper.mapToAccoountDto(updatedAccount);
		return uppdatedAccountDto;
	}



	@Override
	public AccountDto withdrawl(Long id, Double amount) {
		Account account = accountRespository.findById(id).orElseThrow(()-> new RuntimeException("Account doesn't find with Id: "+id));
		double balance = account.getBalance();
		if(balance > amount) {
			account.setBalance(balance-amount);
		}else {
			throw new RuntimeException("Insufficiet funds");
		}
		
		Account updatedAccount = accountRespository.save(account);
		AccountDto uppdatedAccountDto = AccountMapper.mapToAccoountDto(updatedAccount);
		return uppdatedAccountDto;
	}



	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRespository.findAll();
		List<AccountDto> accountDtos = accounts.stream().map((account)->AccountMapper.mapToAccoountDto(account)).collect(Collectors.toList());
		return accountDtos;
	}



	@Override
	public void deleteAccountById(Long id) {
		Account account = accountRespository.findById(id).orElseThrow(()-> new RuntimeException("Account doesn't exist with ID "+id));
		accountRespository.deleteById(id);
		
		
	}

}
