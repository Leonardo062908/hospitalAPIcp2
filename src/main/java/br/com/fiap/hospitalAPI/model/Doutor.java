package br.com.fiap.hospitalAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Entity
public class Doutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar em branco!")
    private String nome;

    @Column(unique = true)
    @Pattern(regexp = "\\d{4,6}", message = "CRM deve conter de 4 a 6 dígitos")
    private String crm;

    @Email(message = "O e-mail deve ser válido")
    private String email;

    //Relacionamento N:N com Paciente
    @ManyToMany(mappedBy = "doutores")
    private List<Paciente> pacientes;

    //Relacionamento N:N com Especialidade
    @ManyToMany
    @JoinTable(
            name = "doutor_especialidade",
            joinColumns = @JoinColumn(name = "doutor_id"),
            inverseJoinColumns = @JoinColumn(name = "especialidade_id")
    )
    private List<Especialidade> especialidades;

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

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public List<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }
}