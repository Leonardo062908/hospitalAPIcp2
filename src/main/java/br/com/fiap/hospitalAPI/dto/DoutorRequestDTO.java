package br.com.fiap.hospitalAPI.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class DoutorRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "CRM é obrigatório")
    @Pattern(regexp = "\\d{4,6}", message = "CRM deve conter de 4 a 6 dígitos numéricos")
    private String crm;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    // IDs das especialidades que o doutor possui
    private List<Long> especialidadeIds;

    // IDs dos pacientes atendidos (opcional)
    private List<Long> pacienteIds;

    // Getters e Setters
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
