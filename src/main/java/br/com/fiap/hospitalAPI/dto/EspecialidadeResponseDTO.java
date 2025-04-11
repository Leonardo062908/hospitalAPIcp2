package br.com.fiap.hospitalAPI.dto;

import org.springframework.hateoas.Link;

import java.util.List;

public class EspecialidadeResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private List<HateoasDto> doutorIds;
    private Link link;


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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<HateoasDto> getDoutorIds() {
        return doutorIds;
    }

    public void setDoutorIds(List<HateoasDto> doutorIds) {
        this.doutorIds = doutorIds;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
