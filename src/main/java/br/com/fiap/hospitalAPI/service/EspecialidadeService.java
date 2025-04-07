package br.com.fiap.hospitalAPI.service;

import br.com.fiap.hospitalAPI.dto.EspecialidadeDTO;
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

    public List<EspecialidadeDTO> listarTodos() {
        return especialidadeRepository.findAll()
                .stream()
                .map(EspecialidadeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EspecialidadeDTO buscarPorId(Long id) {
        Optional<Especialidade> especialidade = especialidadeRepository.findById(id);
        return especialidade.map(EspecialidadeMapper::toDTO).orElse(null);
    }

    public EspecialidadeDTO criar(EspecialidadeDTO dto) {
        Especialidade entity = EspecialidadeMapper.toEntity(dto);
        Especialidade salvo = especialidadeRepository.save(entity);
        return EspecialidadeMapper.toDTO(salvo);
    }

    public EspecialidadeDTO atualizar(Long id, EspecialidadeDTO dto) {
        Optional<Especialidade> optional = especialidadeRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }

        Especialidade existente = optional.get();
        existente.setNome(dto.getNome());
        existente.setDescricao(dto.getDescricao());

        Especialidade atualizado = especialidadeRepository.save(existente);
        return EspecialidadeMapper.toDTO(atualizado);
    }

    public boolean deletar(Long id) {
        Optional<Especialidade> especialidade = especialidadeRepository.findById(id);
        if (especialidade.isPresent()) {
            especialidadeRepository.delete(especialidade.get());
            return true;
        }
        return false;
    }
}
