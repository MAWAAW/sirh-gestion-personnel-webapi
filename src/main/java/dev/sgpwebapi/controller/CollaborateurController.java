package dev.sgpwebapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.sgpwebapi.entite.Banque;
import dev.sgpwebapi.entite.Collaborateur;
import dev.sgpwebapi.repository.CollaborateurRepository;

@RestController
@RequestMapping("/api/collaborateurs")
public class CollaborateurController {
	
	@Autowired private CollaborateurRepository collaborateurRepo;

	/** retourne la liste des collaborateurs au format JSON dont le département a l’identifiant ID_DEPARTEMENT. */
	@GetMapping()
	public List<Collaborateur> listerCollaborateurs(@RequestParam(value="departement",defaultValue="0") int idDepartement) {
		
		List<Collaborateur> collabs = new ArrayList();
		
		if(idDepartement == 0) {
			return this.collaborateurRepo.findAll();
		}
		
		for(Collaborateur c: this.collaborateurRepo.findAll()) {
			if(c.getDepartement().getId() == idDepartement) {
				collabs.add(c);
			}
		}
		
		return collabs;
	}


	/** retourne les informations d'un collaborateur */
	@GetMapping(value = "/{matricule}")
	public Collaborateur getCollaborateur(@PathVariable("matricule") String matricule) {
		Collaborateur collab = this.collaborateurRepo.findByMatricule(matricule);
		return collab;
	}
	
	/** modifie un collaborateur (données envoyées au format JSON). */
	@PutMapping(value = "/{matricule}")
	public Collaborateur putCollaborateur(@PathVariable("matricule") String matricule,
			@RequestBody Collaborateur collab) {
		
		Collaborateur currentCollab = this.collaborateurRepo.findByMatricule(matricule);
		if(currentCollab == null) {
			return null;
		}
		
		currentCollab.setNom(collab.getNom());
		currentCollab.setPrenom(collab.getPrenom());
		this.collaborateurRepo.save(currentCollab);
		
		return currentCollab;
	}
	
	/** récupère les coordonnées bancaires d’un collaborateur. */
	@GetMapping(value = "/{matricule}/banque")
	public Banque getBanqueOfCollaborateur(@PathVariable("matricule") String matricule) {
		
		Collaborateur collab = this.collaborateurRepo.findByMatricule(matricule);
		if(collab == null || collab.getBanque() == null) {
			return null;
		}
		
		return collab.getBanque();
	}
	
	/** modifie uniquement les coordonnées bancaires d’un collaborateur. */
	@PutMapping(value = "/{matricule}/banque")
	public Collaborateur putBanqueOfCollaborateur(@PathVariable("matricule") String matricule,
			@RequestBody Banque coordonneesBancaire) {
		
		Collaborateur collab = this.collaborateurRepo.findByMatricule(matricule);
		if(collab == null) {
			return null;
		}
		
		collab.getBanque().setNomBanque(coordonneesBancaire.getNomBanque());
		collab.getBanque().setIban(coordonneesBancaire.getIban());
		collab.getBanque().setBic(coordonneesBancaire.getBic());
		this.collaborateurRepo.save(collab);
		
		return collab;
	}
	
}
