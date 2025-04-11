package br.com.fiap.hospitalAPI.dto;

import java.time.LocalDate;
import java.util.List;

public class PacienteResponseDTO {

    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String doencas;
    private String telefone;
    private HateoasDto hospitalId;
    private List<HateoasDto> doutorIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDoencas() {
        return doencas;
    }

    public void setDoencas(String doencas) {
        this.doencas = doencas;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public HateoasDto getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(HateoasDto hospitalId) {
        this.hospitalId = hospitalId;
    }

    public List<HateoasDto> getDoutorIds() {
        return doutorIds;
    }

    public void setDoutorIds(List<HateoasDto> doutorIds) {
        this.doutorIds = doutorIds;
    }
}
