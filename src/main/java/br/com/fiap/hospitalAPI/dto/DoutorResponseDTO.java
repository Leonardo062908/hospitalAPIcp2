package br.com.fiap.hospitalAPI.dto.response;

import java.util.List;

public class DoutorResponseDTO {

    private Long id;
    private String nome;
    private String crm;
    private String email;

    private List<Long> especialidadeIds;
    private List<Long> pacienteIds;

    // Getters e Setters
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

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Long> getEspecialidadeIds() {
        return especialidadeIds;
    }

    public void setEspecialidadeIds(List<Long> especialidadeIds) {
        this.especialidadeIds = especialidadeIds;
    }

    public List<Long> getPacienteIds() {
        return pacienteIds;
    }

    public void setPacienteIds(List<Long> pacienteIds) {
        this.pacienteIds = pacienteIds;
    }
}
