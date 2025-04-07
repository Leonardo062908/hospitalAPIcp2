package br.com.fiap.hospitalAPI.service;

import br.com.fiap.hospitalAPI.dto.DoutorDTO;
import br.com.fiap.hospitalAPI.mapper.DoutorMapper;
import br.com.fiap.hospitalAPI.model.Doutor;
import br.com.fiap.hospitalAPI.Repository.DoutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoutorService {

    @Autowired
    private DoutorRepository doutorRepository;

    public List<DoutorDTO> listarTodos() {
        return doutorRepository.findAll()
                .stream()
                .map(DoutorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DoutorDTO buscarPorId(Long id) {
        Optional<Doutor> optional = doutorRepository.findById(id);
        return optional.map(DoutorMapper::toDTO).orElse(null);
    }

    public DoutorDTO criar(DoutorDTO dto) {
        Doutor entity = DoutorMapper.toEntity(dto);
        Doutor salvo = doutorRepository.save(entity);
        return DoutorMapper.toDTO(salvo);
    }

    public DoutorDTO atualizar(Long id, DoutorDTO dto) {
        Optional<Doutor> optional = doutorRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }

        Doutor existente = optional.get();
        existente.setNome(dto.getNome());
        existente.setCrm(dto.getCrm());
        existente.setEmail(dto.getEmail());

        Doutor atualizado = doutorRepository.save(existente);
        return DoutorMapper.toDTO(atualizado);
    }

    public void deletar(Long id) {
        doutorRepository.deleteById(id);
    }
}
