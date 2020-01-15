package com.okta.springbootvue.entity;

import java.time.LocalDateTime;

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
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;


import lombok.*;
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
	private  long id;
	
	@NotNull(message = "MEMBER is NULL/EMPTY")
		@ManyToOne(fetch = FetchType.EAGER, targetEntity = Member.class)
		@JoinColumn(name = "MEMBER_ID")
	private Member reservefor;
	
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}",message = "Pattern is not MATCHED")
	@NotNull(message = "LOCALDATETIME is NULL/EMPTY")
	@Future(message="DATE and TIME can not be PAST!")
		@Column(name = "RESERVE_DATE_AND_TIME")
	private LocalDateTime reservedateandtime;

	@Pattern(regexp = "\\d+",message = "Can not be CHARACTER or ZERO")
	@NotNull(message = "RESERVESEATS is NULL/EMPTY")
	@Min(value = 1 , message = "Seats can not be EMPTY!")
	@Positive(message = "Cant not be NEGATIVE!")
		@Column(name = "SEATS")
	private Integer reserveseats;
	
	@NotNull(message = "TABLES is NULL/EMPTY")
		@ManyToOne(fetch = FetchType.EAGER, targetEntity = tables.class)
		@JoinColumn(name = "table_id",insertable = true)
	private tables has;
		
	@NotNull(message = "SERVICES is NULL/EMPTY")
		@ManyToOne(fetch = FetchType.EAGER,targetEntity = services.class)
		@JoinColumn(name = "service_ID",insertable = true)
	private services serviceto;	
	
}