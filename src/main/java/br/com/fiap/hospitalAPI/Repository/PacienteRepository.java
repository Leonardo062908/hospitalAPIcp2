package br.com.fiap.hospitalAPI.Repository;

import br.com.fiap.hospitalAPI.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}

