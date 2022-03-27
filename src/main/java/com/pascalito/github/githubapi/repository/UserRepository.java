package com.pascalito.github.githubapi.repository;

import com.pascalito.github.githubapi.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Query("""
            SELECT ue 
            FROM UserEntity ue JOIN RepositoryEntity re JOIN LanguageEntity le 
            WHERE le.name = :lang 
            """
    )
    List<UserEntity> findByLanguage(@Param("lang") String language);

    Optional<UserEntity> findByName(String name);
}
