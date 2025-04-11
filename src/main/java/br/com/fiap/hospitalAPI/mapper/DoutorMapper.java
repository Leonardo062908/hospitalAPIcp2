package br.com.fiap.hospitalAPI.mapper;

import br.com.fiap.hospitalAPI.controller.EspecialidadeController;
import br.com.fiap.hospitalAPI.controller.HospitalController;
import br.com.fiap.hospitalAPI.controller.PacienteController;
import br.com.fiap.hospitalAPI.dto.DoutorRequestDTO;
import br.com.fiap.hospitalAPI.dto.DoutorResponseDTO;
import br.com.fiap.hospitalAPI.dto.HateoasDto;
import br.com.fiap.hospitalAPI.model.Doutor;
import br.com.fiap.hospitalAPI.model.Especialidade;
import br.com.fiap.hospitalAPI.model.Paciente;

import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
                            .map(especialidade -> {
                                return new HateoasDto(especialidade.getId(), linkTo(methodOn(EspecialidadeController.class).buscarEspecialidadePorId(especialidade.getId())).withSelfRel());
                            })
                            .collect(Collectors.toList())
            );
        }

        if (doutor.getPacientes() != null) {
            dto.setPacienteIds(
                    doutor.getPacientes().stream()
                            .map(paciente -> {
                                return new HateoasDto(paciente.getId(), linkTo(methodOn(PacienteController.class).buscarPacientePorId(paciente.getId())).withSelfRel());
                            })
                            .collect(Collectors.toList())
            );
        }

        if (doutor.getHospital() != null) {
            Long idHospital = doutor.getHospital().getId();
            dto.setHospitalId(new HateoasDto(idHospital, linkTo(methodOn(HospitalController.class).buscarHospitalPorId(idHospital)).withSelfRel()));
        }

        return dto;
    }

    public static Doutor toEntity(DoutorRequestDTO dto) {
        Doutor doutor = new Doutor();
        doutor.setNome(dto.getNome());
        doutor.setCrm(dto.getCrm());
        doutor.setEmail(dto.getEmail());

        doutor.setEspecialidades(null);
        doutor.setPacientes(null);
        doutor.setHospital(null);

        return doutor;
    }
}
