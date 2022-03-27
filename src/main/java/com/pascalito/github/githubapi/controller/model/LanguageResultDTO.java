package com.pascalito.github.githubapi.controller.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LanguageResultDTO {
    private String name;
    private Long projectCount;
}
