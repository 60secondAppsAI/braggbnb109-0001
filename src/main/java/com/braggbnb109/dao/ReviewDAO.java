package com.braggbnb109.dao;

import java.util.List;

import com.braggbnb109.dao.GenericDAO;
import com.braggbnb109.domain.Review;





public interface ReviewDAO extends GenericDAO<Review, Integer> {
  
	List<Review> findAll();
	






}


