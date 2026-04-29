package com.quiz.service;

import com.quiz.exception.ResourceNotFoundException;
import com.quiz.modal.Doctors;
import com.quiz.repository.DoctorsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class DoctorsService {

    private final DoctorsRepository doctorRepository;

    public List<Doctors> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctors getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
    }

    public Doctors registerDoctor(Doctors doctor) {
        // Ensure ID is null for new doctor (let DB generate)
        doctor.setId(null);
        return doctorRepository.save(doctor);
    }

    public Doctors updateDoctor(Long id, Doctors updatedDoctor) {
        Doctors existing = getDoctorById(id);
        existing.setFullName(updatedDoctor.getFullName());
        existing.setSpecialization(updatedDoctor.getSpecialization());
        existing.setDepartment(updatedDoctor.getDepartment());
        existing.setAvailable(updatedDoctor.isAvailable());
        return doctorRepository.save(existing);
    }

    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Doctor not found with id: " + id);
        }
        doctorRepository.deleteById(id);
    }
}