package com.app.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.bank.entity.Account;

public interface AccountRespository extends JpaRepository<Account, Long> {

}
