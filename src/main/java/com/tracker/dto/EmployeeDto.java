package com.tracker.dto;

import java.util.List;

import com.tracker.entity.TaskEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class EmployeeDto
{
	private Long id;
	private String employeeName;
	private String employeeMail;
	private String employeeMobile;
	private List<TaskEntity> tasks;
}
