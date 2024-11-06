package com.braggbnb109.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.braggbnb109.dao.GenericDAO;
import com.braggbnb109.service.GenericService;
import com.braggbnb109.service.impl.GenericServiceImpl;
import com.braggbnb109.dao.HostDAO;
import com.braggbnb109.domain.Host;
import com.braggbnb109.dto.HostDTO;
import com.braggbnb109.dto.HostSearchDTO;
import com.braggbnb109.dto.HostPageDTO;
import com.braggbnb109.dto.HostConvertCriteriaDTO;
import com.braggbnb109.dto.common.RequestDTO;
import com.braggbnb109.dto.common.ResultDTO;
import com.braggbnb109.service.HostService;
import com.braggbnb109.util.ControllerUtils;





@Service
public class HostServiceImpl extends GenericServiceImpl<Host, Integer> implements HostService {

    private final static Logger logger = LoggerFactory.getLogger(HostServiceImpl.class);

	@Autowired
	HostDAO hostDao;

	


	@Override
	public GenericDAO<Host, Integer> getDAO() {
		return (GenericDAO<Host, Integer>) hostDao;
	}
	
	public List<Host> findAll () {
		List<Host> hosts = hostDao.findAll();
		
		return hosts;	
		
	}

	public ResultDTO addHost(HostDTO hostDTO, RequestDTO requestDTO) {

		Host host = new Host();

		host.setHostId(hostDTO.getHostId());


		host.setName(hostDTO.getName());


		host.setEmail(hostDTO.getEmail());


		host.setPhoneNumber(hostDTO.getPhoneNumber());


		host.setRegistrationDate(hostDTO.getRegistrationDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		host = hostDao.save(host);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Host> getAllHosts(Pageable pageable) {
		return hostDao.findAll(pageable);
	}

	public Page<Host> getAllHosts(Specification<Host> spec, Pageable pageable) {
		return hostDao.findAll(spec, pageable);
	}

	public ResponseEntity<HostPageDTO> getHosts(HostSearchDTO hostSearchDTO) {
	
			Integer hostId = hostSearchDTO.getHostId(); 
 			String name = hostSearchDTO.getName(); 
 			String email = hostSearchDTO.getEmail(); 
 			String phoneNumber = hostSearchDTO.getPhoneNumber(); 
   			String sortBy = hostSearchDTO.getSortBy();
			String sortOrder = hostSearchDTO.getSortOrder();
			String searchQuery = hostSearchDTO.getSearchQuery();
			Integer page = hostSearchDTO.getPage();
			Integer size = hostSearchDTO.getSize();

	        Specification<Host> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, hostId, "hostId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, email, "email"); 
			
			spec = ControllerUtils.andIfNecessary(spec, phoneNumber, "phoneNumber"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("email")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("phoneNumber")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Host> hosts = this.getAllHosts(spec, pageable);
		
		//System.out.println(String.valueOf(hosts.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(hosts.getTotalPages()));
		
		List<Host> hostsList = hosts.getContent();
		
		HostConvertCriteriaDTO convertCriteria = new HostConvertCriteriaDTO();
		List<HostDTO> hostDTOs = this.convertHostsToHostDTOs(hostsList,convertCriteria);
		
		HostPageDTO hostPageDTO = new HostPageDTO();
		hostPageDTO.setHosts(hostDTOs);
		hostPageDTO.setTotalElements(hosts.getTotalElements());
		return ResponseEntity.ok(hostPageDTO);
	}

	public List<HostDTO> convertHostsToHostDTOs(List<Host> hosts, HostConvertCriteriaDTO convertCriteria) {
		
		List<HostDTO> hostDTOs = new ArrayList<HostDTO>();
		
		for (Host host : hosts) {
			hostDTOs.add(convertHostToHostDTO(host,convertCriteria));
		}
		
		return hostDTOs;

	}
	
	public HostDTO convertHostToHostDTO(Host host, HostConvertCriteriaDTO convertCriteria) {
		
		HostDTO hostDTO = new HostDTO();
		
		hostDTO.setHostId(host.getHostId());

	
		hostDTO.setName(host.getName());

	
		hostDTO.setEmail(host.getEmail());

	
		hostDTO.setPhoneNumber(host.getPhoneNumber());

	
		hostDTO.setRegistrationDate(host.getRegistrationDate());

	

		
		return hostDTO;
	}

	public ResultDTO updateHost(HostDTO hostDTO, RequestDTO requestDTO) {
		
		Host host = hostDao.getById(hostDTO.getHostId());

		host.setHostId(ControllerUtils.setValue(host.getHostId(), hostDTO.getHostId()));

		host.setName(ControllerUtils.setValue(host.getName(), hostDTO.getName()));

		host.setEmail(ControllerUtils.setValue(host.getEmail(), hostDTO.getEmail()));

		host.setPhoneNumber(ControllerUtils.setValue(host.getPhoneNumber(), hostDTO.getPhoneNumber()));

		host.setRegistrationDate(ControllerUtils.setValue(host.getRegistrationDate(), hostDTO.getRegistrationDate()));



        host = hostDao.save(host);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public HostDTO getHostDTOById(Integer hostId) {
	
		Host host = hostDao.getById(hostId);
			
		
		HostConvertCriteriaDTO convertCriteria = new HostConvertCriteriaDTO();
		return(this.convertHostToHostDTO(host,convertCriteria));
	}







}
