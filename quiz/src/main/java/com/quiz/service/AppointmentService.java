package com.quiz.service;

import com.quiz.exception.ResourceNotFoundException;
import com.quiz.modal.Appointment;
import com.quiz.modal.Doctors;
import com.quiz.repository.AppointmentRepository;
import com.quiz.repository.DoctorsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorsRepository doctorRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment bookAppointment(Appointment appointment) {
        if (appointment.getDoctors() == null || appointment.getDoctors().getId() == null) {
            throw new IllegalArgumentException("Doctor ID is required to book an appointment");
        }
        Long doctorId = appointment.getDoctors().getId();
        Doctors doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + doctorId));
        appointment.setDoctors(doctor);
        return appointmentRepository.save(appointment);
    }

    public void cancelAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Appointment not found with id: " + id);
        }
        appointmentRepository.deleteById(id);
    }
}