package br.com.fiap.hospitalAPI.service;

import br.com.fiap.hospitalAPI.dto.EnderecoRequestDTO;
import br.com.fiap.hospitalAPI.dto.EnderecoResponseDTO;
import br.com.fiap.hospitalAPI.mapper.EnderecoMapper;
import br.com.fiap.hospitalAPI.model.Endereco;
import br.com.fiap.hospitalAPI.Repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<EnderecoResponseDTO> listarTodos() {
        return enderecoRepository.findAll()
                .stream()
                .map(EnderecoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public EnderecoResponseDTO buscarPorId(Long id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        return endereco.map(EnderecoMapper::toResponseDTO).orElse(null);
    }

    public EnderecoResponseDTO criar(EnderecoRequestDTO dto) {
        Endereco endereco = EnderecoMapper.toEntity(dto);
        Endereco salvo = enderecoRepository.save(endereco);
        return EnderecoMapper.toResponseDTO(salvo);
    }

    public EnderecoResponseDTO atualizar(Long id, EnderecoRequestDTO dto) {
        Optional<Endereco> optional = enderecoRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }

        Endereco existente = optional.get();
        existente.setLogradouro(dto.getLogradouro());
        existente.setNumero(dto.getNumero());
        existente.setComplemento(dto.getComplemento());
        existente.setBairro(dto.getBairro());
        existente.setCidade(dto.getCidade());
        existente.setEstado(dto.getEstado());
        existente.setCep(dto.getCep());

        Endereco atualizado = enderecoRepository.save(existente);
        return EnderecoMapper.toResponseDTO(atualizado);
    }

    public boolean deletar(Long id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        if (endereco.isPresent()) {
            enderecoRepository.delete(endereco.get());
            return true;
        }
        return false;
    }
}

