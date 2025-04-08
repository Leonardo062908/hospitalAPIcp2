package br.com.fiap.hospitalAPI.service;

import br.com.fiap.hospitalAPI.dto.PacienteRequestDTO;
import br.com.fiap.hospitalAPI.dto.PacienteResponseDTO;
import br.com.fiap.hospitalAPI.mapper.PacienteMapper;
import br.com.fiap.hospitalAPI.model.Doutor;
import br.com.fiap.hospitalAPI.model.Hospital;
import br.com.fiap.hospitalAPI.model.Paciente;
import br.com.fiap.hospitalAPI.Repository.DoutorRepository;
import br.com.fiap.hospitalAPI.Repository.HospitalRepository;
import br.com.fiap.hospitalAPI.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private DoutorRepository doutorRepository;

    public List<PacienteResponseDTO> listarTodos() {
        return pacienteRepository.findAll()
                .stream()
                .map(PacienteMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public PacienteResponseDTO buscarPorId(Long id) {
        return pacienteRepository.findById(id)
                .map(PacienteMapper::toResponseDTO)
                .orElse(null);
    }

    public PacienteResponseDTO criar(PacienteRequestDTO dto) {
        Paciente paciente = PacienteMapper.toEntity(dto);

        if (dto.getHospitalId() != null) {
            hospitalRepository.findById(dto.getHospitalId()).ifPresent(paciente::setHospital);
        }

        if (dto.getDoutorIds() != null) {
            List<Doutor> doutores = doutorRepository.findAllById(dto.getDoutorIds());
            paciente.setDoutores(doutores);
        }

        return PacienteMapper.toResponseDTO(pacienteRepository.save(paciente));
    }

    public PacienteResponseDTO atualizar(Long id, PacienteRequestDTO dto) {
        Optional<Paciente> optional = pacienteRepository.findById(id);
        if (optional.isEmpty()) return null;

        Paciente paciente = optional.get();
        paciente.setNome(dto.getNome());
        paciente.setCpf(dto.getCpf());
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setDoencas(dto.getDoencas());
        paciente.setTelefone(dto.getTelefone());

        if (dto.getHospitalId() != null) {
            hospitalRepository.findById(dto.getHospitalId()).ifPresent(paciente::setHospital);
        }

        if (dto.getDoutorIds() != null) {
            List<Doutor> doutores = doutorRepository.findAllById(dto.getDoutorIds());
            paciente.setDoutores(doutores);
        }

        return PacienteMapper.toResponseDTO(pacienteRepository.save(paciente));
    }

    public boolean deletar(Long id) {
        Optional<Paciente> optional = pacienteRepository.findById(id);
        if (optional.isPresent()) {
            pacienteRepository.delete(optional.get());
            return true;
        }
        return false;
    }
}
