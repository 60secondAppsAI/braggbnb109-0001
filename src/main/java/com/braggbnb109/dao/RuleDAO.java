package com.braggbnb109.dao;

import java.util.List;

import com.braggbnb109.dao.GenericDAO;
import com.braggbnb109.domain.Rule;





public interface RuleDAO extends GenericDAO<Rule, Integer> {
  
	List<Rule> findAll();
	






}


