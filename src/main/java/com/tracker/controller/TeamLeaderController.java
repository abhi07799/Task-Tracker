package com.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.dto.EmployeeDto;
import com.tracker.dto.TaskDto;
import com.tracker.service.EmployeeService;
import com.tracker.service.TaskService;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/v1/leader/")
public class TeamLeaderController
{
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("ram")
	public String Ram()
	{
		empService.saveDummy();
		return "Jai Bajrang Bali";
	}
		
	@PostMapping("addNewTask")
	public TaskDto addNewTask(@RequestBody TaskDto taskDto)
	{
		return taskService.addNewTask(taskDto);
	}
	
	@PostMapping("addNewEmployee")
	public EmployeeDto addNewEmployee(@RequestBody EmployeeDto emp)
	{
		return empService.addNewEmployee(emp);
	}
	
	@GetMapping("getTaskById/{id}")
	public ResponseEntity<?> getTaskById(@PathVariable Long id )
	{
		return ResponseEntity.ok(taskService.findTaskById(id));
	}
	
	@GetMapping("getTaskByName/{name}")
	public ResponseEntity<?> getTaskByName(@PathVariable("name") String title )
	{
		return ResponseEntity.ok(empService.findTaskByName(title));
	}
	
	@GetMapping("allTasks")
	public ResponseEntity<?> getAllTasks()
	{
		List<TaskDto> allTasks = taskService.getAllTasks();
		
		return ResponseEntity.ok(allTasks);
	}
	
	@GetMapping("allEmployees")
	public List<EmployeeDto> getAllEmployees()
	{
		List<EmployeeDto> allEmployees = empService.getAllEmployees();
		
		return allEmployees;
	}
	
	
}
