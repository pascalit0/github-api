package com.pascalito.github.githubapi.controller.builder;

import com.pascalito.github.githubapi.controller.model.UserResultDTO;
import com.pascalito.github.githubapi.repository.entity.UserEntity;

import java.util.Map;

public interface UserResultBuilder {

    static UserResultDTO buildUserDTO(UserEntity user, Map<String, Long> langAmount) {
        return UserResultDTO.builder()
                .userId(user.getName())
                .languages(LanguageResultBuilder.buildResultLanguages(langAmount))
                .build();
    }
}
