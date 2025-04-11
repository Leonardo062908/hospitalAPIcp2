package br.com.fiap.hospitalAPI.mapper;

import br.com.fiap.hospitalAPI.controller.DoutorController;
import br.com.fiap.hospitalAPI.dto.EspecialidadeRequestDTO;
import br.com.fiap.hospitalAPI.dto.EspecialidadeResponseDTO;
import br.com.fiap.hospitalAPI.dto.HateoasDto;
import br.com.fiap.hospitalAPI.model.Especialidade;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class EspecialidadeMapper {

    public static Especialidade toEntity(EspecialidadeRequestDTO dto) {
        Especialidade especialidade = new Especialidade();
        especialidade.setNome(dto.getNome());
        especialidade.setDescricao(dto.getDescricao());
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
                    .map(doutor -> {
                        return new HateoasDto(doutor.getId(), linkTo(methodOn(DoutorController.class).buscarDoutorPorId(doutor.getId())).withSelfRel());
                    })
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}
