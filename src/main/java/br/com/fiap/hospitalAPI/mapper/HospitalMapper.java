package br.com.fiap.hospitalAPI.mapper;

import br.com.fiap.hospitalAPI.controller.EspecialidadeController;
import br.com.fiap.hospitalAPI.controller.HospitalController;
import br.com.fiap.hospitalAPI.dto.HospitalRequestDTO;
import br.com.fiap.hospitalAPI.dto.HospitalResponseDTO;
import br.com.fiap.hospitalAPI.model.Hospital;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class HospitalMapper {

    public static Hospital toEntity(HospitalRequestDTO dto) {
        Hospital hospital = new Hospital();
        hospital.setNome(dto.getNome());
        hospital.setCnpj(dto.getCnpj());
        hospital.setEndereco(EnderecoMapper.toEntity(dto.getEndereco()));
        hospital.setDoutores(null);
        hospital.setPacientes(null);
        return hospital;
    }

    public static HospitalResponseDTO toResponseDTO(Hospital hospital, boolean self) {
        HospitalResponseDTO dto = new HospitalResponseDTO();
        dto.setId(hospital.getId());
        dto.setNome(hospital.getNome());
        dto.setCnpj(hospital.getCnpj());
        dto.setEndereco(EnderecoMapper.toResponseDTO(hospital.getEndereco()));

        Link link;

        if (self) {
            link = linkTo(methodOn(HospitalController.class).buscarHospitalPorId(hospital.getId())).withSelfRel();
        } else {
            link = linkTo(methodOn(HospitalController.class).listarHospitais()).withRel("Lista de Hospitais");
        }

        dto.setLink(link);

        return dto;
    }
}
