package br.com.fiap.hospitalAPI.mapper;

import br.com.fiap.hospitalAPI.dto.EspecialidadeDTO;
import br.com.fiap.hospitalAPI.model.Doutor;
import br.com.fiap.hospitalAPI.model.Especialidade;

import java.util.stream.Collectors;

public class EspecialidadeMapper {

    public static EspecialidadeDTO toDTO(Especialidade especialidade) {
        EspecialidadeDTO dto = new EspecialidadeDTO();
        dto.setId(especialidade.getId());
        dto.setNome(especialidade.getNome());
        dto.setDescricao(especialidade.getDescricao());

        if (especialidade.getDoutores() != null) {
            dto.setDoutorIds(
                    especialidade.getDoutores().stream()
                            .map(Doutor::getId)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    public static Especialidade toEntity(EspecialidadeDTO dto) {
        Especialidade especialidade = new Especialidade();
        especialidade.setId(dto.getId());
        especialidade.setNome(dto.getNome());
        especialidade.setDescricao(dto.getDescricao());

        // Relacionamentos devem ser tratados no service
        especialidade.setDoutores(null);

        return especialidade;
    }
}
