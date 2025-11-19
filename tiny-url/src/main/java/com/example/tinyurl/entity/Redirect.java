package com.example.tinyurl.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import org.hibernate.annotations.NaturalId;

@Entity
public class Redirect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    @JsonIgnore
    private Long id;

    @NaturalId
    @Column(unique = true, nullable = false)
    private String alias;

    @Column(nullable = false)
    private String url;


    public Redirect() {
    }

    public Redirect(final String alias, final String url) {
        this.alias = alias;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Redirect{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
