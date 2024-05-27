package com.tracker.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.dto.EmployeeDto;
import com.tracker.dto.TaskDto;
import com.tracker.entity.EmployeeEntity;
import com.tracker.entity.TaskEntity;
import com.tracker.repository.EmployeeRespository;
import com.tracker.repository.TaskRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class EmployeeService
{
	@Autowired
	private EmployeeRespository empRepo;

	@Autowired
	private TaskRepository taskRepo;

	@Autowired
	private ModelMapper mapper;

	public List<TaskDto> getAllTasks(EmployeeEntity employee)
	{

		try
		{
			log.info("getting all tasks of employee");

			List<TaskEntity> allTasks = taskRepo.findAllByTaskAssignedTo(employee);

			List<TaskDto> allTask = new ArrayList<>();

			for (TaskEntity task : allTasks)
			{
				allTask.add(mapper.map(task, TaskDto.class));
			}

			return allTask;
		} catch (Exception ex)
		{
			log.error("error occured while getting all tasks of an employee - " + ex.getLocalizedMessage());
			ex.printStackTrace();
		}
		return null;
	}

	public List<TaskDto> findTaskByName(String taskName)
	{

		try
		{
			log.info("finding tasks ny name ");

			List<TaskEntity> allTasks = taskRepo.findByTaskTitle(taskName);
			List<TaskDto> allTask = new ArrayList<>();

			for (TaskEntity task : allTasks)
			{
				allTask.add(mapper.map(task, TaskDto.class));
			}

			return allTask;
		} catch (Exception ex)
		{
			log.error("error occured while finding tasks ny name - " + ex.getLocalizedMessage());
			ex.printStackTrace();
		}
		return null;
	}

	public EmployeeDto getProfileDetails(Long id)
	{
		try
		{
			log.info("getting employee profile details");
			return mapper.map(empRepo.findById(id), EmployeeDto.class);
		} catch (Exception ex)
		{
			log.error("error occured while getting profile details - " + ex.getLocalizedMessage());
		}
		return null;
	}

	public TaskDto updateTask(Long taskId, TaskDto taskDto)
	{
		try
		{
			log.info("updating task...");
			Optional<TaskEntity> taskOptional = taskRepo.findById(taskId);

			if (!taskOptional.isPresent())
			{
				throw new Exception();
			}

			TaskEntity task = taskOptional.get();

			if (taskDto.getTaskStatus().equalsIgnoreCase("Completed"))
			{
				task.setTaskComment(taskDto.getTaskComment());
				task.setTaskStatus("Completed");
				task.setTaskCompleted(new Date());
			} else
			{
				task.setTaskComment(taskDto.getTaskComment());
			}

			return mapper.map(taskRepo.save(task), TaskDto.class);

		} catch (Exception ex)
		{
			log.error("error occured while updating task - " + ex.getLocalizedMessage());
			ex.printStackTrace();
		}
		return null;
	}

	public List<EmployeeDto> getAllEmployees()
	{
		try
		{
			log.info("getting all employees");
			List<EmployeeEntity> allEmps = empRepo.findAll();
			
			List<EmployeeDto> allEmp = new ArrayList<>();
			
			for(EmployeeEntity emp:allEmps)
			{
				allEmp.add(mapper.map(emp, EmployeeDto.class));
			}
			
			return allEmp;
		} 
		catch (Exception ex)
		{
			log.error("error occured while getting all employees - " + ex.getLocalizedMessage());
			ex.printStackTrace();
		}
		return null;
	}

	public EmployeeDto addNewEmployee(EmployeeDto empDto)
	{
		try
		{
			log.info("adding new employee");
			
			EmployeeEntity emp = mapper.map(empDto, EmployeeEntity.class);
			
			return mapper.map(empRepo.save(emp), EmployeeDto.class);
		} catch (Exception ex)
		{
			log.error("error occured while creating new employee - " + ex.getLocalizedMessage());
			ex.printStackTrace();
		}
		return null;
	}

	public void saveDummy()
	{
		EmployeeEntity emp1 = new EmployeeEntity();
		EmployeeEntity emp2 = new EmployeeEntity();

		emp1.setEmployeeName("demon");
		emp1.setEmployeeMail("demon@mail");
		emp1.setEmployeeMobile("9874563210");

		emp2.setEmployeeName("ghost");
		emp2.setEmployeeMail("ghost@mail");
		emp2.setEmployeeMobile("0321456987");

		empRepo.save(emp1);
		empRepo.save(emp2);
	}
}
