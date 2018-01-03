package dev.sgpwebapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sgpwebapi.entite.Departement;
import dev.sgpwebapi.repository.DepartementRepository;

@RestController
@RequestMapping("/api/departements")
public class DepartementController {
	
	@Autowired private DepartementRepository departementRepo;
	
	@GetMapping
	public List<Departement> listerDepartements() {
		return this.departementRepo.findAll();
	}
}