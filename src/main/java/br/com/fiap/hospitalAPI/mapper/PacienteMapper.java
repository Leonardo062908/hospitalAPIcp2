package br.com.fiap.hospitalAPI.mapper;

import br.com.fiap.hospitalAPI.dto.PacienteRequestDTO;
import br.com.fiap.hospitalAPI.dto.PacienteResponseDTO;
import br.com.fiap.hospitalAPI.model.Doutor;
import br.com.fiap.hospitalAPI.model.Paciente;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PacienteMapper {

    public static Paciente toEntity(PacienteRequestDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setNome(dto.getNome());
        paciente.setCpf(dto.getCpf());
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setDoencas(dto.getDoencas());
        paciente.setTelefone(dto.getTelefone());

        // TODO: Mapear doutores e hospital
        paciente.setHospital(null);
        paciente.setDoutores(null);
        return paciente;
    }

    public static PacienteResponseDTO toResponseDTO(Paciente paciente) {
        PacienteResponseDTO dto = new PacienteResponseDTO();
        dto.setId(paciente.getId());
        dto.setNome(paciente.getNome());
        dto.setCpf(paciente.getCpf());
        dto.setDataNascimento(paciente.getDataNascimento());
        dto.setDoencas(paciente.getDoencas());
        dto.setTelefone(paciente.getTelefone());

        dto.setHospitalId(paciente.getHospital() != null ? paciente.getHospital().getId() : null);

        List<Long> doutorIds = paciente.getDoutores() != null
                ? paciente.getDoutores().stream().map(Doutor::getId).collect(Collectors.toList())
                : Collections.emptyList();

        dto.setDoutorIds(doutorIds);
        return dto;
    }
}
