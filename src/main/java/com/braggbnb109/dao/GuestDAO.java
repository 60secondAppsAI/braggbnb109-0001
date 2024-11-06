package com.braggbnb109.dao;

import java.util.List;

import com.braggbnb109.dao.GenericDAO;
import com.braggbnb109.domain.Guest;





public interface GuestDAO extends GenericDAO<Guest, Integer> {
  
	List<Guest> findAll();
	






}


