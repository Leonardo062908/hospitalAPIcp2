package br.com.fiap.hospitalAPI.mapper;

import br.com.fiap.hospitalAPI.dto.request.EnderecoRequestDTO;
import br.com.fiap.hospitalAPI.dto.response.EnderecoResponseDTO;
import br.com.fiap.hospitalAPI.model.Endereco;

public class EnderecoMapper {

    public static Endereco toEntity(EnderecoRequestDTO dto) {
        Endereco e = new Endereco();
        e.setLogradouro(dto.getLogradouro());
        e.setNumero(dto.getNumero());
        e.setComplemento(dto.getComplemento());
        e.setBairro(dto.getBairro());
        e.setCidade(dto.getCidade());
        e.setEstado(dto.getEstado());
        e.setCep(dto.getCep());
        return e;
    }

    public static EnderecoResponseDTO toResponseDTO(Endereco endereco) {
        EnderecoResponseDTO dto = new EnderecoResponseDTO();
        dto.setId(endereco.getId());
        dto.setLogradouro(endereco.getLogradouro());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());
        dto.setBairro(endereco.getBairro());
        dto.setCidade(endereco.getCidade());
        dto.setEstado(endereco.getEstado());
        dto.setCep(endereco.getCep());
        return dto;
    }
}