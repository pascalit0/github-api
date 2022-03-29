package com.pascalito.github.githubapi.repository;

import com.pascalito.github.githubapi.repository.entity.LanguageEntity;
import org.springframework.data.repository.CrudRepository;

public interface LanguageRepository extends CrudRepository<LanguageEntity, Long> {
}
