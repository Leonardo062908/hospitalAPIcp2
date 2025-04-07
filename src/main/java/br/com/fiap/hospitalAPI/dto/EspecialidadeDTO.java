package br.com.fiap.hospitalAPI.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class EspecialidadeDTO {

    private Long id;

    @NotBlank(message = "Nome da especialidade é obrigatório")
    private String nome;

    private String descricao;

    // Lista de IDs dos doutores que possuem essa especialidade (opcional)
    private List<Long> doutorIds;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Long> getDoutorIds() {
        return doutorIds;
    }

    public void setDoutorIds(List<Long> doutorIds) {
        this.doutorIds = doutorIds;
    }
}
