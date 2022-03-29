package com.pascalito.github.githubapi.repository;

import com.pascalito.github.githubapi.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Query("""
            SELECT ue 
            FROM UserEntity ue JOIN RepositoryEntity re JOIN LanguageEntity le 
            WHERE re.user = ue AND le.repository = re 
                AND le.name = :lang 
            """
    )
    List<UserEntity> findByLanguage(@Param("lang") String language);
    List<UserEntity> findAll();
}
