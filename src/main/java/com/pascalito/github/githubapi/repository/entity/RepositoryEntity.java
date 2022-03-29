package com.pascalito.github.githubapi.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RepositoryEntity {

    @GeneratedValue
    @Id
    private Long id;

    private String name;

    @ManyToOne
    private UserEntity user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "repository", cascade = CascadeType.ALL)
    private List<LanguageEntity> languages;
}
