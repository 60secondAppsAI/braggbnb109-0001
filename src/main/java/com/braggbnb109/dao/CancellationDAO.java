package com.braggbnb109.dao;

import java.util.List;

import com.braggbnb109.dao.GenericDAO;
import com.braggbnb109.domain.Cancellation;





public interface CancellationDAO extends GenericDAO<Cancellation, Integer> {
  
	List<Cancellation> findAll();
	






}


