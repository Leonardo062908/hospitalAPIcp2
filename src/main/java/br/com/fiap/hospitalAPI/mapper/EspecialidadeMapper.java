package br.com.fiap.hospitalAPI.mapper;

import br.com.fiap.hospitalAPI.dto.EspecialidadeRequestDTO;
import br.com.fiap.hospitalAPI.dto.EspecialidadeResponseDTO;
import br.com.fiap.hospitalAPI.model.Especialidade;
import java.util.stream.Collectors;

public class EspecialidadeMapper {

    public static Especialidade toEntity(EspecialidadeRequestDTO dto) {
        Especialidade especialidade = new Especialidade();
        especialidade.setNome(dto.getNome());
        especialidade.setDescricao(dto.getDescricao());
        // TODO: Mapear doutores
        especialidade.setDoutores(null);
        return especialidade;
    }

    public static EspecialidadeResponseDTO toResponseDTO(Especialidade especialidade) {
        EspecialidadeResponseDTO dto = new EspecialidadeResponseDTO();
        dto.setId(especialidade.getId());
        dto.setNome(especialidade.getNome());
        dto.setDescricao(especialidade.getDescricao());
        if (especialidade.getDoutores() != null) {
            dto.setDoutorIds(especialidade.getDoutores().stream()
                    .map(doutor -> doutor.getId())
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}
