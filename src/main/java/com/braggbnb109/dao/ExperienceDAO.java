package com.braggbnb109.dao;

import java.util.List;

import com.braggbnb109.dao.GenericDAO;
import com.braggbnb109.domain.Experience;





public interface ExperienceDAO extends GenericDAO<Experience, Integer> {
  
	List<Experience> findAll();
	






}


