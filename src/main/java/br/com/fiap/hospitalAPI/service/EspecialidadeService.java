package br.com.fiap.hospitalAPI.service;

import br.com.fiap.hospitalAPI.dto.EspecialidadeRequestDTO;
import br.com.fiap.hospitalAPI.dto.EspecialidadeResponseDTO;
import br.com.fiap.hospitalAPI.mapper.EspecialidadeMapper;
import br.com.fiap.hospitalAPI.model.Especialidade;
import br.com.fiap.hospitalAPI.Repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EspecialidadeService {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public List<EspecialidadeResponseDTO> listarTodos() {
        return especialidadeRepository.findAll()
                .stream()
                .map(EspecialidadeMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public EspecialidadeResponseDTO buscarPorId(Long id) {
        Optional<Especialidade> optional = especialidadeRepository.findById(id);
        return optional.map(EspecialidadeMapper::toResponseDTO).orElse(null);
    }

    public EspecialidadeResponseDTO criar(EspecialidadeRequestDTO dto) {
        Especialidade especialidade = EspecialidadeMapper.toEntity(dto);
        Especialidade salvo = especialidadeRepository.save(especialidade);
        return EspecialidadeMapper.toResponseDTO(salvo);
    }

    public EspecialidadeResponseDTO atualizar(Long id, EspecialidadeRequestDTO dto) {
        Optional<Especialidade> optional = especialidadeRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }

        Especialidade existente = optional.get();
        existente.setNome(dto.getNome());
        existente.setDescricao(dto.getDescricao());

        Especialidade atualizado = especialidadeRepository.save(existente);
        return EspecialidadeMapper.toResponseDTO(atualizado);
    }

    public boolean deletar(Long id) {
        Optional<Especialidade> optional = especialidadeRepository.findById(id);
        if (optional.isPresent()) {
            especialidadeRepository.delete(optional.get());
            return true;
        }
        return false;
    }
}
