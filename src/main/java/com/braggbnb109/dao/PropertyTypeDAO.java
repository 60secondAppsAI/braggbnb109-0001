package com.braggbnb109.dao;

import java.util.List;

import com.braggbnb109.dao.GenericDAO;
import com.braggbnb109.domain.PropertyType;





public interface PropertyTypeDAO extends GenericDAO<PropertyType, Integer> {
  
	List<PropertyType> findAll();
	






}


