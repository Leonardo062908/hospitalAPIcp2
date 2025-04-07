package br.com.fiap.hospitalAPI.mapper;

import br.com.fiap.hospitalAPI.dto.DoutorDTO;
import br.com.fiap.hospitalAPI.model.Doutor;
import br.com.fiap.hospitalAPI.model.Especialidade;
import br.com.fiap.hospitalAPI.model.Paciente;

import java.util.List;
import java.util.stream.Collectors;

public class DoutorMapper {

    public static DoutorDTO toDTO(Doutor doutor) {
        DoutorDTO dto = new DoutorDTO();
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

    public static Doutor toEntity(DoutorDTO dto) {
        Doutor doutor = new Doutor();
        doutor.setId(dto.getId());
        doutor.setNome(dto.getNome());
        doutor.setCrm(dto.getCrm());
        doutor.setEmail(dto.getEmail());
        doutor.setEspecialidades(null);
        doutor.setPacientes(null);

        return doutor;
    }
}
