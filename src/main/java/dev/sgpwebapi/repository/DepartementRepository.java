package dev.sgpwebapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.sgpwebapi.entite.Departement;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {

}
