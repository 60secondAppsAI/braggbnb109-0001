package com.braggbnb109.dao;

import java.util.List;

import com.braggbnb109.dao.GenericDAO;
import com.braggbnb109.domain.Availability;





public interface AvailabilityDAO extends GenericDAO<Availability, Integer> {
  
	List<Availability> findAll();
	






}


