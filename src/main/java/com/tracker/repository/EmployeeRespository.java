package com.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tracker.entity.EmployeeEntity;

@Repository
public interface EmployeeRespository extends JpaRepository<EmployeeEntity, Long>
{

}
