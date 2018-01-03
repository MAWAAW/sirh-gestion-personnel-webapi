package dev.sgpwebapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.sgpwebapi.entite.Collaborateur;

public interface CollaborateurRepository extends JpaRepository<Collaborateur, Integer> {

	Collaborateur findByMatricule(String matricule);

}
