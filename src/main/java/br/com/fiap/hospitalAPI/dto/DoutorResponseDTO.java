package br.com.fiap.hospitalAPI.dto;

import java.util.List;
import org.springframework.hateoas.Link;

public class DoutorResponseDTO {

    private Long id;
    private String nome;
    private String crm;
    private String email;
    private Link link;

    private List<HateoasDto> especialidadeIds;
    private List<HateoasDto> pacienteIds;
    private HateoasDto hospitalId;

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

    public List<HateoasDto> getEspecialidadeIds() {
        return especialidadeIds;
    }

    public void setEspecialidadeIds(List<HateoasDto> especialidadeIds) {
        this.especialidadeIds = especialidadeIds;
    }

    public List<HateoasDto> getPacienteIds() {
        return pacienteIds;
    }

    public void setPacienteIds(List<HateoasDto> pacienteIds) {
        this.pacienteIds = pacienteIds;
    }

    public HateoasDto getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(HateoasDto hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
