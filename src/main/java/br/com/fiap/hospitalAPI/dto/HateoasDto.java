package br.com.fiap.hospitalAPI.dto;

import org.springframework.hateoas.Link;

public class HateoasDto {
    private Long id;
    private Link link;

    public HateoasDto(Long id, Link link) {
        this.id = id;
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
