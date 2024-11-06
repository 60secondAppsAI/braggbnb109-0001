package com.braggbnb109.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="preferences")
@Getter @Setter @NoArgsConstructor
public class Preference {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="preference_id")
	private Integer preferenceId;
    
  	@Column(name="preference_type")
	private String preferenceType;
    
  	@Column(name="value")
	private String value;
    
	




}
