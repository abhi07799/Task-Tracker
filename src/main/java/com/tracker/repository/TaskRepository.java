package com.tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tracker.entity.EmployeeEntity;
import com.tracker.entity.TaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long>
{
	List<TaskEntity> findAllByTaskAssignedTo(EmployeeEntity taskAssignedTo);
	
	List<TaskEntity> findByTaskTitle(String taskTitle);
}
