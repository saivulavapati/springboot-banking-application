package com.app.bank.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.bank.dto.AccountDto;
import com.app.bank.service.AccountService;

@RestController
@RequestMapping("/api/bank/accounts")
public class AccountController {
	
	private AccountService accountService;
	
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}


	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		AccountDto savedAccount = accountService.createAccount(accountDto);
		return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
		AccountDto accountById = accountService.getAccountById(id);
		return new ResponseEntity<AccountDto>(accountById,HttpStatus.OK);
	}
	
	@PutMapping("deposit/{id}")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String,Long> request) {
		double amount = request.get("amount");
		AccountDto accountDto = accountService.deposit(id, amount);
		return new ResponseEntity<>(accountDto,HttpStatus.OK);
	}
	
	@PutMapping("withdraw/{id}")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String,Long> request) {
		double amount = request.get("amount");
		AccountDto accountDto = accountService.withdrawl(id, amount);
		return new ResponseEntity<>(accountDto,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		List<AccountDto> allAccounts = accountService.getAllAccounts();
		return new ResponseEntity<>(allAccounts,HttpStatus.OK);
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteAccountById(@PathVariable Long id){
		accountService.deleteAccountById(id);
		return new ResponseEntity<>("Account successfully deleted with Id "+id,HttpStatus.OK);
	}

}
