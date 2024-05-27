package com.tracker.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.dto.TaskDto;
import com.tracker.entity.EmployeeEntity;
import com.tracker.entity.TaskEntity;
import com.tracker.repository.EmployeeRespository;
import com.tracker.repository.TaskRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TaskService
{
	@Autowired
	private EmployeeRespository empRepo;

	@Autowired
	private TaskRepository taskRepo;

	@Autowired
	private ModelMapper mapper;

	public TaskDto addNewTask(TaskDto taskDto)
	{
		try
		{
			log.info("adding new task...");
			TaskEntity task = mapper.map(taskDto, TaskEntity.class);
			task.setTaskAdded(new Date());

			Date dueDate = new Date();
			dueDate.setDate(new Date().getDate() + 7);
			task.setTaskDueDate(dueDate);
			task.setTaskStatus("New");
			Optional<EmployeeEntity> empOptional = empRepo.findById(task.getTaskAssignedTo().getId());
			if (!empOptional.isPresent())
			{
				throw new Exception();
			}
			EmployeeEntity emp = empOptional.get();
			List<TaskEntity> tasks = new ArrayList<>();
			tasks.add(task);
			emp.setTasks(tasks);
			empRepo.save(emp);
			log.info("new task added successfully");
			return mapper.map(taskRepo.save(task), TaskDto.class);

		} catch (Exception ex)
		{
			log.error("error occured while adding new task - " + ex.getLocalizedMessage());
			ex.printStackTrace();
		}
		return null;
	}

	public TaskDto findTaskById(Long taskId)
	{
		try
		{
			log.info("getting task by id..");
			return mapper.map(taskRepo.findById(taskId), TaskDto.class);
		} catch (Exception ex)
		{
			log.error("error occured while getting task by id - " + ex.getLocalizedMessage());
			ex.printStackTrace();
		}
		return null;
	}

	public List<TaskDto> getAllTasks()
	{
		try
		{
			log.info("getting all tasks");
			List<TaskEntity> allTasks= taskRepo.findAll();
			List<TaskDto> allTask = new ArrayList<>();
			
			for(TaskEntity task:allTasks)
			{
				allTask.add(mapper.map(task, TaskDto.class));
			}
			
			return allTask;
		} 
		catch (Exception ex)
		{
			log.error("error occured while getting all tasks - "+ex.getLocalizedMessage());
			ex.printStackTrace();
		}
		return null;
	}
}
