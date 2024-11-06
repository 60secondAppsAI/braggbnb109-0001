package com.braggbnb109.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb109.domain.Transaction;
import com.braggbnb109.dto.TransactionDTO;
import com.braggbnb109.dto.TransactionSearchDTO;
import com.braggbnb109.dto.TransactionPageDTO;
import com.braggbnb109.dto.TransactionConvertCriteriaDTO;
import com.braggbnb109.service.GenericService;
import com.braggbnb109.dto.common.RequestDTO;
import com.braggbnb109.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface TransactionService extends GenericService<Transaction, Integer> {

	List<Transaction> findAll();

	ResultDTO addTransaction(TransactionDTO transactionDTO, RequestDTO requestDTO);

	ResultDTO updateTransaction(TransactionDTO transactionDTO, RequestDTO requestDTO);

    Page<Transaction> getAllTransactions(Pageable pageable);

    Page<Transaction> getAllTransactions(Specification<Transaction> spec, Pageable pageable);

	ResponseEntity<TransactionPageDTO> getTransactions(TransactionSearchDTO transactionSearchDTO);
	
	List<TransactionDTO> convertTransactionsToTransactionDTOs(List<Transaction> transactions, TransactionConvertCriteriaDTO convertCriteria);

	TransactionDTO getTransactionDTOById(Integer transactionId);







}





