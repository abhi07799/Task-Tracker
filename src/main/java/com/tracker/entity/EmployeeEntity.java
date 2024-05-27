package com.tracker.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "employee")
public class EmployeeEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "employee_name")
	private String employeeName;
	
	@Column(name = "employee_mail")
	private String employeeMail;
	
	@Column(name = "employee_mobile")
	private String employeeMobile;
	
	@OneToMany(mappedBy = "taskAssignedTo")
	@JsonManagedReference
	private List<TaskEntity> tasks;
}
