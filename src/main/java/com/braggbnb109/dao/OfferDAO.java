package com.braggbnb109.dao;

import java.util.List;

import com.braggbnb109.dao.GenericDAO;
import com.braggbnb109.domain.Offer;





public interface OfferDAO extends GenericDAO<Offer, Integer> {
  
	List<Offer> findAll();
	






}


