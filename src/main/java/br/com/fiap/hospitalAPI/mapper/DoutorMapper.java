package br.com.fiap.hospitalAPI.mapper;

import br.com.fiap.hospitalAPI.dto.DoutorRequestDTO;
import br.com.fiap.hospitalAPI.dto.DoutorResponseDTO;
import br.com.fiap.hospitalAPI.model.Doutor;
import br.com.fiap.hospitalAPI.model.Especialidade;
import br.com.fiap.hospitalAPI.model.Paciente;

import java.util.List;
import java.util.stream.Collectors;

public class DoutorMapper {

    public static DoutorResponseDTO toResponseDTO(Doutor doutor) {
        DoutorResponseDTO dto = new DoutorResponseDTO();
        dto.setId(doutor.getId());
        dto.setNome(doutor.getNome());
        dto.setCrm(doutor.getCrm());
        dto.setEmail(doutor.getEmail());

        if (doutor.getEspecialidades() != null) {
            dto.setEspecialidadeIds(
                    doutor.getEspecialidades().stream()
                            .map(Especialidade::getId)
                            .collect(Collectors.toList())
            );
        }

        if (doutor.getPacientes() != null) {
            dto.setPacienteIds(
                    doutor.getPacientes().stream()
                            .map(Paciente::getId)
                            .collect(Collectors.toList())
            );
        }

        if (doutor.getHospital() != null) {
            dto.setHospitalId(doutor.getHospital().getId());
        }

        return dto;
    }

    public static Doutor toEntity(DoutorRequestDTO dto) {
        Doutor doutor = new Doutor();
        doutor.setNome(dto.getNome());
        doutor.setCrm(dto.getCrm());
        doutor.setEmail(dto.getEmail());

        // TODO: Mapear especialidades, pacientes e hospital
        doutor.setEspecialidades(null);
        doutor.setPacientes(null); // mapeado no service
        doutor.setHospital(null); // mapeado no service

        return doutor;
    }
}
