package com.pascalito.github.githubapi.controller.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserResultDTO {
    private String userId;
    private List<LanguageResultDTO> languages;
}
