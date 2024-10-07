package com.week3.assignment1.controller;

import com.week3.assignment1.entity.Patient;
import com.week3.assignment1.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String showAllPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patients"; // Thymeleaf template name
    }

    @GetMapping("/add")
    public String addPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "addPatient"; // Thymeleaf template name
    }

    @PostMapping("/add")
    public String addPatient(@ModelAttribute Patient patient) {
        patientService.addPatient(patient);
        return "redirect:/patients";
    }

    @GetMapping("/update/{id}")
    public String updatePatientForm(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.getPatientById(id));
        return "updatePatient"; // Thymeleaf template name
    }

    @PostMapping("/update/{id}")
    public String updatePatient(@PathVariable Long id, @ModelAttribute Patient patient) {
        patientService.updatePatient(id, patient);
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }

    @GetMapping("/{id}")
    public String getPatientById(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            model.addAttribute("patient", patient);
            return "viewPatient"; // Display a patient details page
        } else {
            model.addAttribute("errorMessage", "Patient not found");
            return "error"; // Error page if patient not found
        }
    }

    @GetMapping("/view")
    public String viewPatient(@RequestParam("id") Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "viewPatient"; // Thymeleaf template name
    }

    @GetMapping("/search")
    public String searchPatientByName(@RequestParam("name") String name, Model model) {
        List<Patient> patients = patientService.getPatientsByName(name);
        model.addAttribute("patients", patients);
        if (patients.isEmpty()) {
            model.addAttribute("errorMessage", "No patients found with name containing: " + name);
            return "viewPatient"; // Show error message
        } else if (patients.size() == 1) {
            return "redirect:/patients/" + patients.get(0).getId();  // If only one match, go to details directly
        }
        return "listPatients"; // Show a list of matching patients
    }

    @GetMapping("/view/{id}")
    public String viewPatientById(@PathVariable Long id, Model model) {
        List<Patient> patients = new ArrayList<>();
        Patient patient = patientService.getPatientById(id);

        if (patient != null) {
            patients.add(patient);
        }

        model.addAttribute("patients", patients);
        return "viewPatient";
    }


}
