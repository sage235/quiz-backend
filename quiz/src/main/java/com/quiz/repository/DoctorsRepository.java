package com.quiz.repository;

import com.quiz.modal.Doctors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorsRepository extends JpaRepository<Doctors, Long> {

    List<Doctors> findByAvailableTrue();

    List<Doctors> findBySpecialization(String specialization);

    List<Doctors> findByDepartment(String department);
}