package com.tracker.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "tasks")
public class TaskEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "task_title")
	private String taskTitle;
	
	@Column(name = "task_desc", length = 500)
	private String taskDesc;
	
	@Column(name = "task_created_by")
	private String taskCreatedBy;
	
	@Column(name = "task_added")
	private Date taskAdded;
	
	@Column(name = "task_due_date")
	private Date taskDueDate;
	
	@Column(name = "task_status")
	private String taskStatus;
	
	@Column(name = "task_completed")
	private Date taskCompleted;
	
	@Column(name = "task_comment")
	private String taskComment;
	
	@ManyToOne
	@JoinColumn(name = "task_assigned_to")
	@JsonBackReference 
	private EmployeeEntity taskAssignedTo;
	
}
