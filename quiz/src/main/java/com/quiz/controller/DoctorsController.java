package com.quiz.controller;

import com.quiz.modal.Doctors;
import com.quiz.service.DoctorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorsController {

    private final DoctorsService doctorService;

    // GET /api/doctors OR /api/doctors?available=true
    @GetMapping
    public ResponseEntity<List<Doctors>> getAllDoctors(
            @RequestParam(required = false) Boolean available) {
        if (Boolean.TRUE.equals(available)) {
            return ResponseEntity.ok(doctorService.getAllDoctors());
        }
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    // GET /api/doctors/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Doctors> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    // POST /api/doctors
    @PostMapping
    public ResponseEntity<Doctors> createDoctor(@Validated @RequestBody Doctors doctors) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(doctorService.registerDoctor(doctors));
    }

    // PUT /api/doctors/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Doctors> updateDoctor(
            @PathVariable Long id,
            @Validated @RequestBody Doctors doctors) {
        return ResponseEntity.ok(doctorService.updateDoctor(id, doctors));
    }
}