package com.pascalito.github.githubapi.service;

import com.pascalito.github.githubapi.client.GitHubRestClient;
import com.pascalito.github.githubapi.client.model.LanguageDTO;
import com.pascalito.github.githubapi.client.model.RepoDTO;
import com.pascalito.github.githubapi.client.model.UserDTO;
import com.pascalito.github.githubapi.service.impl.GitHubImporterServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@Disabled
@SpringBootTest
class GitHubImporterServiceImplTest {

    @Autowired
    private GitHubImporterServiceImpl cut;

    @MockBean
    private GitHubRestClient gitHubRestClient;

    @Test
    void importUsersFromGitHub() {
        var fakeUser = new UserDTO("1", "pascalit0", "");
        var fakeRepo = new RepoDTO("1", "testrepo", "");
        var fakeLang = new LanguageDTO("Java");

        when(gitHubRestClient.getOrganizationUsers(anyString())).thenReturn(List.of(fakeUser));
        when(gitHubRestClient.getUserRepos(anyString())).thenReturn(List.of(fakeRepo));
        when(gitHubRestClient.getRepoLanguages(anyString(), anyString())).thenReturn(List.of(fakeLang));

        cut.importUsersFromGitHub();
    }
}