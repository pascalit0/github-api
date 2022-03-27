package com.pascalito.github.githubapi.repository;

import com.pascalito.github.githubapi.repository.entity.RepositoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RepoRepository extends CrudRepository<RepositoryEntity, Long> {

    Optional<RepositoryEntity> findByName(String name);
}
