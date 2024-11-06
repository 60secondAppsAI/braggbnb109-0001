package com.braggbnb109.dao;

import java.util.List;

import com.braggbnb109.dao.GenericDAO;
import com.braggbnb109.domain.Property;





public interface PropertyDAO extends GenericDAO<Property, Integer> {
  
	List<Property> findAll();
	






}


