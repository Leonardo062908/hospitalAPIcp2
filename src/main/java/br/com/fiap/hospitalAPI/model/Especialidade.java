package br.com.fiap.hospitalAPI.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Especialidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private String descricao;

    @ManyToMany(mappedBy = "especialidades")
    private List<Doutor> doutores;

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

    public List<Doutor> getDoutores() {
        return doutores;
    }

    public void setDoutores(List<Doutor> doutores) {
        this.doutores = doutores;
    }
}


