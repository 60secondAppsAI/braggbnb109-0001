package com.braggbnb109.dao;

import java.util.List;

import com.braggbnb109.dao.GenericDAO;
import com.braggbnb109.domain.Amenity;





public interface AmenityDAO extends GenericDAO<Amenity, Integer> {
  
	List<Amenity> findAll();
	






}


