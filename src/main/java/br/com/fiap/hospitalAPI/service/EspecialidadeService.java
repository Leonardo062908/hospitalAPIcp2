package br.com.fiap.hospitalAPI.service;

import br.com.fiap.hospitalAPI.dto.EspecialidadeRequestDTO;
import br.com.fiap.hospitalAPI.dto.EspecialidadeResponseDTO;
import br.com.fiap.hospitalAPI.mapper.EspecialidadeMapper;
import br.com.fiap.hospitalAPI.model.Doutor;
import br.com.fiap.hospitalAPI.model.Especialidade;
import br.com.fiap.hospitalAPI.repository.DoutorRepository;
import br.com.fiap.hospitalAPI.repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EspecialidadeService {

    private final EspecialidadeRepository especialidadeRepository;
    private final DoutorRepository doutorRepository;

    @Autowired
    public EspecialidadeService (EspecialidadeRepository especialidadeRepository, DoutorRepository doutorRepository) {
        this.especialidadeRepository = especialidadeRepository;
        this.doutorRepository = doutorRepository;
    }

    public List<EspecialidadeResponseDTO> listarTodos() {
        return especialidadeRepository.findAll()
                .stream()
                .map((Especialidade especialidade) -> EspecialidadeMapper.toResponseDTO(especialidade, true))
                .collect(Collectors.toList());
    }

    public EspecialidadeResponseDTO buscarPorId(Long id) {
        Optional<Especialidade> optional = especialidadeRepository.findById(id);
        return optional.map((Especialidade especialidade) -> EspecialidadeMapper.toResponseDTO(especialidade, false)).orElse(null);
    }

    public EspecialidadeResponseDTO criar(EspecialidadeRequestDTO dto) {
        Especialidade especialidade = EspecialidadeMapper.toEntity(dto);

        if (dto.getDoutorIds() != null) {
            List<Doutor> doutores = doutorRepository.findAllById(dto.getDoutorIds());
            especialidade.setDoutores(doutores);
        }

        Especialidade salvo = especialidadeRepository.save(especialidade);
        return EspecialidadeMapper.toResponseDTO(salvo, true);
    }

    public EspecialidadeResponseDTO atualizar(Long id, EspecialidadeRequestDTO dto) {
        Optional<Especialidade> optional = especialidadeRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }

        Especialidade existente = optional.get();
        existente.setNome(dto.getNome());
        existente.setDescricao(dto.getDescricao());

        if (dto.getDoutorIds() != null) {
            List<Doutor> doutores = doutorRepository.findAllById(dto.getDoutorIds());
            existente.setDoutores(doutores);
        }

        Especialidade atualizado = especialidadeRepository.save(existente);
        return EspecialidadeMapper.toResponseDTO(atualizado, true);
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
