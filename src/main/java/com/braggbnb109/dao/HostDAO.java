package com.braggbnb109.dao;

import java.util.List;

import com.braggbnb109.dao.GenericDAO;
import com.braggbnb109.domain.Host;





public interface HostDAO extends GenericDAO<Host, Integer> {
  
	List<Host> findAll();
	






}


