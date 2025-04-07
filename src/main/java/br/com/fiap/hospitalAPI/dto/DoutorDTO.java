package br.com.fiap.hospitalAPI.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;

public class DoutorDTO {

    private Long id;

    @NotBlank(message = "O nome não pode estar em branco!")
    private String nome;

    @Pattern(regexp = "\\d{4,6}", message = "CRM deve conter 6 dígitos")
    private String crm;

    @Email(message = "O e-mail deve ser válido")
    private String email;

    private List<Long> especialidadeIds;

    private List<Long> pacienteIds;

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
