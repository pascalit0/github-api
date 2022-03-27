package com.pascalito.github.githubapi.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity {

    @GeneratedValue
    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<RepositoryEntity> repos;
}
