package com.braggbnb109.dao;

import java.util.List;

import com.braggbnb109.dao.GenericDAO;
import com.braggbnb109.domain.Message;





public interface MessageDAO extends GenericDAO<Message, Integer> {
  
	List<Message> findAll();
	






}


