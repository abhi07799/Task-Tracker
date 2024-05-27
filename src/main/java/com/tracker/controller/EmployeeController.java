package com.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.dto.TaskDto;
import com.tracker.entity.EmployeeEntity;
import com.tracker.service.EmployeeService;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/v1/employee/")
public class EmployeeController
{
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("getProfile/{id}")
	public ResponseEntity<?> getProfileDetails(@PathVariable Long id)
	{
		return ResponseEntity.ok(empService.getProfileDetails(id));
	}
	
	@GetMapping("getAllAssignedTasks")
	public ResponseEntity<?> getAllAssignedTasks(@RequestBody EmployeeEntity employee)
	{
		return ResponseEntity.ok(empService.getAllTasks(employee));
	}
	
	@PutMapping("updateTask/{taskId}")
	public ResponseEntity<?> updateTask(@PathVariable Long taskId, @RequestBody TaskDto taskDto)
	{
		return ResponseEntity.ok(empService.updateTask(taskId, taskDto));
	}
}
