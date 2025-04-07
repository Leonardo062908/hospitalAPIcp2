package br.com.fiap.hospitalAPI.mapper;

import br.com.fiap.hospitalAPI.dto.DoutorRequestDTO;
import br.com.fiap.hospitalAPI.dto.DoutorResponseDTO;
import br.com.fiap.hospitalAPI.model.Doutor;
import br.com.fiap.hospitalAPI.model.Especialidade;
import br.com.fiap.hospitalAPI.model.Paciente;

import java.util.List;
import java.util.stream.Collectors;

public class DoutorMapper {

    // Converter de entidade para DTO de resposta
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

        return dto;
    }

    // Converter DTO de requisição para entidade
    public static Doutor toEntity(DoutorRequestDTO dto) {
        Doutor doutor = new Doutor();
        doutor.setNome(dto.getNome());
        doutor.setCrm(dto.getCrm());
        doutor.setEmail(dto.getEmail());

        // Relacionamentos com Paciente/Especialidade são montados no service
        doutor.setEspecialidades(null);
        doutor.setPacientes(null);

        return doutor;
    }
}
