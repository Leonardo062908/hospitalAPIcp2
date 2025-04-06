package br.com.fiap.hospitalAPI.Repository;

import br.com.fiap.hospitalAPI.model.Doutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoutorRepository extends JpaRepository<Doutor, Long> {
}