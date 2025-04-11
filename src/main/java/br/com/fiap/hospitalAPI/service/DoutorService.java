package br.com.fiap.hospitalAPI.service;

import br.com.fiap.hospitalAPI.dto.DoutorRequestDTO;
import br.com.fiap.hospitalAPI.dto.DoutorResponseDTO;
import br.com.fiap.hospitalAPI.mapper.DoutorMapper;
import br.com.fiap.hospitalAPI.model.Doutor;
import br.com.fiap.hospitalAPI.model.Paciente;
import br.com.fiap.hospitalAPI.repository.DoutorRepository;
import br.com.fiap.hospitalAPI.repository.HospitalRepository;
import br.com.fiap.hospitalAPI.repository.PacienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoutorService {

    @Autowired
    private DoutorRepository doutorRepository;
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public List<DoutorResponseDTO> listarTodos() {
        return doutorRepository.findAll()
                .stream()
                .map(DoutorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public DoutorResponseDTO buscarPorId(Long id) {
        Optional<Doutor> optional = doutorRepository.findById(id);
        return optional.map(DoutorMapper::toResponseDTO).orElse(null);
    }

    public DoutorResponseDTO criar(DoutorRequestDTO dto) {
        Doutor entity = DoutorMapper.toEntity(dto);
        if (dto.getHospitalId() != null) {
            hospitalRepository.findById(dto.getHospitalId()).ifPresent(entity::setHospital);
        }

        if (dto.getPacienteIds() != null) {
            List<Paciente> pacientes = pacienteRepository.findAllById(dto.getPacienteIds());
            entity.setPacientes(pacientes);
        }

        Doutor salvo = doutorRepository.save(entity);
        return DoutorMapper.toResponseDTO(salvo);
    }

    public DoutorResponseDTO atualizar(Long id, DoutorRequestDTO dto) {
        Optional<Doutor> optional = doutorRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }

        Doutor existente = optional.get();
        existente.setNome(dto.getNome());
        existente.setCrm(dto.getCrm());
        existente.setEmail(dto.getEmail());

        if (dto.getHospitalId() != null) {
            hospitalRepository.findById(dto.getHospitalId()).ifPresent(existente::setHospital);
        }

        if (dto.getPacienteIds() != null) {
            List<Paciente> pacientes = pacienteRepository.findAllById(dto.getPacienteIds());
            existente.setPacientes(pacientes);
        }

        Doutor atualizado = doutorRepository.save(existente);
        return DoutorMapper.toResponseDTO(atualizado);
    }

    public boolean deletar(Long id) {
        Optional<Doutor> optional = doutorRepository.findById(id);
        if (optional.isPresent()) {
            doutorRepository.delete(optional.get());
            return true;
        }
        return false;
    }
}
