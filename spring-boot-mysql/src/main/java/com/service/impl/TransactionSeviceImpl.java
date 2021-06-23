package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.TUser;
import com.repository.TUserRepository;
import com.service.TransactionSevice;

@Service
public class TransactionSeviceImpl implements TransactionSevice {

	@Autowired
	TUserRepository repository;

	// @Transactional
	@Override
	public void testTransaction() {
		repository.save(new TUser("Huyen")); // OK
		repository.save(new TUser("Thien")); // OK
		repository.save(new TUser("Hoang Thien")); // ERROR
	}

}
