package com.okta.springbootvue.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
	@Data
	@Entity
	@Getter @Setter
	@NoArgsConstructor
	@ToString
	@EqualsAndHashCode
	@Table(name="RESERVATIONS")

public class reservations {
		@Id    
	    @SequenceGenerator(name="RESERVATIONS_SEQ",sequenceName="RESERVATIONS_SEQ")               
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RESERVATIONS_SEQ")  
	    @Column(name="RESERVATIONS_ID",unique = true, nullable = true)	
	private @NonNull Long id;
	
		@ManyToOne(fetch = FetchType.EAGER, targetEntity = Member.class)
		@JoinColumn(name = "MEMBER_ID")
	private member reservefor;
	
		@Column(name = "RESERVE_DATE_AND_TIME")
	private Date reservedateandtime;
	
		@ManyToOne(fetch = FetchType.EAGER, targetEntity = tables.class)
		@JoinColumn(name = "table_id",insertable = true)
	private tables has;
		
		@ManyToOne(fetch = FetchType.EAGER,targetEntity = services.class)
		@JoinColumn(name = "service_ID",insertable = true)
	private services serviceto;	
	
}