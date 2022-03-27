package com.pascalito.github.githubapi.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

// NOTE: Integration Test. Use only during development of REST-Client and disable after as contents change
@SpringBootTest
class GitHubClientTest {

    @Autowired
    GitHubClient cut;


    @Test
    void getOrganizationUsers_OrganizationExists_Success() {
        var userDtos = cut.getOrganizationUsers("codecentric");
        assertEquals(30, userDtos.size());
    }

    @Test
    void getUser_UserExists_Success() {
        var userDto = cut.getUser("pascalit0");
        assertEquals("pascalit0", userDto.getLogin());
    }

    @Test
    void getUserRepos_ReposNumberMatches_Success() {
        var repoDTOs = cut.getUserRepos("pascalit0");
        assertEquals(3, repoDTOs.size());
    }

    @Test
    void getRepoLanguages_RepoHasMultipleLanguages_Success() throws JsonProcessingException {
        var languagesDTOs = cut.getRepoLanguages("pascalit0", "devops-tasks");
        assertEquals(3, languagesDTOs.size());
    }

    @Test
    void getRepoLanguages_RepoHasOneLanguage_Success() throws JsonProcessingException {
        var languagesDTOs = cut.getRepoLanguages("pascalit0", "beer-client");
        assertEquals(1, languagesDTOs.size());
    }

    @Test
    void getRepoLanguages_RepoHasNoLanguage_EmptyResult() throws JsonProcessingException {
        var languagesDTOs = cut.getRepoLanguages("pascalit0", "github-api");
        assertEquals(0, languagesDTOs.size());
    }
}