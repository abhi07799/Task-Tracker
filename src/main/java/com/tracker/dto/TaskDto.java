package com.tracker.dto;

import java.util.Date;

import com.tracker.entity.EmployeeEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto
{
	private Long id;
	private String taskTitle;
	private String taskDesc;
	private String taskCreatedBy;
	private Date taskAdded;
	private Date taskDueDate;
	private String taskStatus;
	private Date taskCompleted;
	private String taskComment;
	private EmployeeEntity taskAssignedTo;
}
