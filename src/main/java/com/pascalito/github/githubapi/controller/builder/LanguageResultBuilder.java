package com.pascalito.github.githubapi.controller.builder;

import com.pascalito.github.githubapi.controller.model.LanguageResultDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface LanguageResultBuilder {

    static List<LanguageResultDTO> buildResultLanguages(Map<String, Long> langCount) {
        var resultList = new ArrayList<LanguageResultDTO>();
        langCount.forEach((lang, projectCount) -> resultList.add(buildLanguageResultDTO(lang, projectCount)));
        return resultList;
    }

    static LanguageResultDTO buildLanguageResultDTO(String lang, Long projectCount) {
        return LanguageResultDTO.builder()
                .name(lang)
                .projectCount(projectCount)
                .build();
    }
}
