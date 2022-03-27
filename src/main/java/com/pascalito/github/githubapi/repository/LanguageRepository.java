package com.pascalito.github.githubapi.repository;

import com.pascalito.github.githubapi.repository.entity.LanguageEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LanguageRepository extends CrudRepository<LanguageEntity, Long> {

    Optional<LanguageEntity> findByName(String name);
}
