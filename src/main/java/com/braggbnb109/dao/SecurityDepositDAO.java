package com.braggbnb109.dao;

import java.util.List;

import com.braggbnb109.dao.GenericDAO;
import com.braggbnb109.domain.SecurityDeposit;





public interface SecurityDepositDAO extends GenericDAO<SecurityDeposit, Integer> {
  
	List<SecurityDeposit> findAll();
	






}


