package br.com.fiap.hospitalAPI.mapper;

import br.com.fiap.hospitalAPI.dto.EspecialidadeRequestDTO;
import br.com.fiap.hospitalAPI.dto.EspecialidadeResponseDTO;
import br.com.fiap.hospitalAPI.model.Especialidade;

public class EspecialidadeMapper {

    public static Especialidade toEntity(EspecialidadeRequestDTO dto) {
        Especialidade especialidade = new Especialidade();
        especialidade.setNome(dto.getNome());
        especialidade.setDescricao(dto.getDescricao());
        especialidade.setDoutores(null); // Relacionamentos tratados no service
        return especialidade;
    }

    public static EspecialidadeResponseDTO toResponseDTO(Especialidade especialidade) {
        EspecialidadeResponseDTO dto = new EspecialidadeResponseDTO();
        dto.setId(especialidade.getId());
        dto.setNome(especialidade.getNome());
        dto.setDescricao(especialidade.getDescricao());
        return dto;
    }
}
