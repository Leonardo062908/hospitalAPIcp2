package br.com.fiap.hospitalAPI.service;

import br.com.fiap.hospitalAPI.dto.HospitalRequestDTO;
import br.com.fiap.hospitalAPI.dto.HospitalResponseDTO;
import br.com.fiap.hospitalAPI.mapper.HospitalMapper;
import br.com.fiap.hospitalAPI.model.Hospital;
import br.com.fiap.hospitalAPI.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalService (HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public List<HospitalResponseDTO> listarTodos() {
        return hospitalRepository.findAll()
                .stream()
                .map(HospitalMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public HospitalResponseDTO buscarPorId(Long id) {
        Optional<Hospital> optional = hospitalRepository.findById(id);
        return optional.map(HospitalMapper::toResponseDTO).orElse(null);
    }

    public HospitalResponseDTO criar(HospitalRequestDTO dto) {
        Hospital hospital = HospitalMapper.toEntity(dto);
        Hospital salvo = hospitalRepository.save(hospital);
        return HospitalMapper.toResponseDTO(salvo);
    }

    public HospitalResponseDTO atualizar(Long id, HospitalRequestDTO dto) {
        Optional<Hospital> optional = hospitalRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }

        Hospital existente = optional.get();
        existente.setNome(dto.getNome());
        existente.setCnpj(dto.getCnpj());
        existente.setEndereco(HospitalMapper.toEntity(dto).getEndereco());

        Hospital atualizado = hospitalRepository.save(existente);
        return HospitalMapper.toResponseDTO(atualizado);
    }

    public boolean deletar(Long id) {
        Optional<Hospital> optional = hospitalRepository.findById(id);
        if (optional.isPresent()) {
            hospitalRepository.delete(optional.get());
            return true;
        }
        return false;
    }
}
