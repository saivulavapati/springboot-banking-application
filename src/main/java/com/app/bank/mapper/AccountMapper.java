package com.app.bank.mapper;

import com.app.bank.dto.AccountDto;
import com.app.bank.entity.Account;

public class AccountMapper {
	
	public static Account mapToAccount(AccountDto accountDto) {
		return new Account(
				accountDto.getId(),
				accountDto.getAccountHolderName(),
				accountDto.getBalance()
				);
	}
	
	public static AccountDto mapToAccoountDto(Account account) {
		return new AccountDto(
				account.getId(),
				account.getAccountHolderName(),
				account.getBalance());
	}

}
