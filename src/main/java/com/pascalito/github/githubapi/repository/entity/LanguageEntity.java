package com.pascalito.github.githubapi.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LanguageEntity {

    @GeneratedValue
    @Id
    private Long id;

    private String name;

    @ManyToOne
    private RepositoryEntity repository;
}
