package br.com.fiap.hospitalAPI.repository;

import br.com.fiap.hospitalAPI.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
