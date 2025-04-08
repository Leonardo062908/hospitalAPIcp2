package br.com.fiap.hospitalAPI.mapper;

import br.com.fiap.hospitalAPI.dto.HospitalRequestDTO;
import br.com.fiap.hospitalAPI.dto.HospitalResponseDTO;
import br.com.fiap.hospitalAPI.model.Hospital;

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

    public static HospitalResponseDTO toResponseDTO(Hospital hospital) {
        HospitalResponseDTO dto = new HospitalResponseDTO();
        dto.setId(hospital.getId());
        dto.setNome(hospital.getNome());
        dto.setCnpj(hospital.getCnpj());
        dto.setEndereco(EnderecoMapper.toResponseDTO(hospital.getEndereco()));
        return dto;
    }
}
