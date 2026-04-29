package com.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.modal.Appointment;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDoctorsId(Long doctorId);

    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByAppointmentDate(LocalDateTime date);

    boolean existsByDoctorsIdAndAppointmentDateAndTimeSlot(
            Long doctorId, LocalDateTime appointmentDate, String timeSlot);
}