package br.com.fiap.hospitalAPI.mapper;

import br.com.fiap.hospitalAPI.controller.DoutorController;
import br.com.fiap.hospitalAPI.controller.HospitalController;
import br.com.fiap.hospitalAPI.controller.PacienteController;
import br.com.fiap.hospitalAPI.dto.HateoasDto;
import br.com.fiap.hospitalAPI.dto.PacienteRequestDTO;
import br.com.fiap.hospitalAPI.dto.PacienteResponseDTO;
import br.com.fiap.hospitalAPI.model.Doutor;
import br.com.fiap.hospitalAPI.model.Paciente;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class PacienteMapper {

    public static Paciente toEntity(PacienteRequestDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setNome(dto.getNome());
        paciente.setCpf(dto.getCpf());
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setDoencas(dto.getDoencas());
        paciente.setTelefone(dto.getTelefone());
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

        if (paciente.getHospital() != null) {
            Long idHospital = paciente.getHospital().getId();
            dto.setHospitalId(new HateoasDto(idHospital, linkTo(methodOn(HospitalController.class).buscarHospitalPorId(idHospital)).withSelfRel()));
        }

        List<HateoasDto> doutorIds = paciente.getDoutores() != null
                ? paciente.getDoutores().stream().map(doutor -> {
            return new HateoasDto(doutor.getId(), linkTo(methodOn(DoutorController.class).buscarDoutorPorId(doutor.getId())).withSelfRel());
        }).collect(Collectors.toList())
                : Collections.emptyList();

        dto.setDoutorIds(doutorIds);
        return dto;
    }
}
