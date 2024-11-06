package com.braggbnb109.dao;

import java.util.List;

import com.braggbnb109.dao.GenericDAO;
import com.braggbnb109.domain.Location;





public interface LocationDAO extends GenericDAO<Location, Integer> {
  
	List<Location> findAll();
	






}


