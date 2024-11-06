package com.braggbnb109.dao;

import java.util.List;

import com.braggbnb109.dao.GenericDAO;
import com.braggbnb109.domain.Preference;





public interface PreferenceDAO extends GenericDAO<Preference, Integer> {
  
	List<Preference> findAll();
	






}


