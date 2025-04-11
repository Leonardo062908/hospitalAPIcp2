package br.com.fiap.hospitalAPI.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class EspecialidadeRequestDTO {

    @NotBlank(message = "O nome da especialidade é obrigatório")
    private String nome;

    private String descricao;

    private List<Long> doutorIds;

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
