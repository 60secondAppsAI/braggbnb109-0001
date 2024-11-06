package com.braggbnb109.dao;

import java.util.List;

import com.braggbnb109.dao.GenericDAO;
import com.braggbnb109.domain.Transaction;





public interface TransactionDAO extends GenericDAO<Transaction, Integer> {
  
	List<Transaction> findAll();
	






}


